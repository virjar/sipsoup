package com.virjar.sipsoup.parse;

import java.util.concurrent.ExecutionException;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.virjar.sipsoup.exception.XpathSyntaxErrorException;
import com.virjar.sipsoup.model.XpathEvaluator;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by virjar on 17/6/9.
 */
@Slf4j
public class XpathParser {
    private static volatile boolean cacheEnabled = true;
    private static LoadingCache<String, XpathEvaluateHolder> cache;
    @Getter
    private String xpathStr;
    private TokenQueue tokenQueue;

    public XpathParser(String xpathStr) {
        this.xpathStr = xpathStr;
        tokenQueue = new TokenQueue(xpathStr);
    }

    public static XpathEvaluator compile(String xpathStr) throws XpathSyntaxErrorException {
        if (cacheEnabled) {
            return fromCache(xpathStr);
        }
        return new XpathParser(xpathStr).parse();
    }

    /**
     * no error代表调用放明确知道xpath没有语法错误,主动放弃检查,是一个方便的方法,但是如果表达式确实有语法错误,本方法跑出非法状态异常
     * 
     * @param xpathStr xpath表达式
     * @return 由模型描述的xpath抽取器
     */
    public static XpathEvaluator compileNoError(String xpathStr) {
        try {
            return compile(xpathStr);
        } catch (XpathSyntaxErrorException e) {
            throw new IllegalStateException(e);
        }
    }

    public XpathEvaluator parse() throws XpathSyntaxErrorException {
        XpathStateMachine xpathStateMachine = new XpathStateMachine(tokenQueue);
        while (xpathStateMachine.getState() != XpathStateMachine.BuilderState.END) {
            xpathStateMachine.getState().parse(xpathStateMachine);
        }
        return xpathStateMachine.getEvaluator();
    }

    private static XpathEvaluator fromCache(String xpathStr) throws XpathSyntaxErrorException {
        if (cache == null) {
            synchronized (XpathParser.class) {
                if (cache == null) {
                    cache = CacheBuilder.newBuilder().build(new CacheLoader<String, XpathEvaluateHolder>() {
                        @Override
                        public XpathEvaluateHolder load(String key) throws Exception {
                            try {
                                return XpathEvaluateHolder.from(new XpathParser(key).parse());
                            } catch (XpathSyntaxErrorException e) {
                                return XpathEvaluateHolder.e(e);
                            }
                        }
                    });
                }
            }
        }

        try {
            XpathEvaluateHolder xpathEvaluatorOptional = cache.get(xpathStr);
            if (xpathEvaluatorOptional.getXpathEvaluator() != null) {
                return xpathEvaluatorOptional.getXpathEvaluator();
            }
            throw xpathEvaluatorOptional.getXpathSyntaxErrorException();
        } catch (ExecutionException e) {// 这个异常不会发生
            log.error("error when compile xpath", e);
            return new XpathParser(xpathStr).parse();
        }
    }

    /**
     * 开启cache,可能两个结果,要么成功编译xpath语法树,要么xpath不合法报错 ,所以错误信息也需要缓存,否则错误会因为不能命中缓存而多次触发缓存建立逻辑,这个思路从optional改造而来
     */
    private static class XpathEvaluateHolder {
        @Getter
        private XpathEvaluator xpathEvaluator;
        @Getter
        private XpathSyntaxErrorException xpathSyntaxErrorException;

        static XpathEvaluateHolder e(XpathSyntaxErrorException xpathSyntaxErrorException) {
            return new XpathEvaluateHolder(null, xpathSyntaxErrorException);
        }

        static XpathEvaluateHolder from(XpathEvaluator xpathEvaluator) {
            return new XpathEvaluateHolder(xpathEvaluator, null);
        }

        private XpathEvaluateHolder(XpathEvaluator xpathEvaluator,
                XpathSyntaxErrorException xpathSyntaxErrorException) {
            this.xpathEvaluator = xpathEvaluator;
            this.xpathSyntaxErrorException = xpathSyntaxErrorException;
        }
    }

    public static void setCacheEnabled(boolean cacheEnabled) {
        XpathParser.cacheEnabled = cacheEnabled;
        if (!cacheEnabled && cache != null) {
            cache.invalidateAll();
        }
    }
}
