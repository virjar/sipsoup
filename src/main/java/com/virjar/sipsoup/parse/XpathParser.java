package com.virjar.sipsoup.parse;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
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
    //抄的fastJson,默认1024个模型自动缓存
    private static final int  CACHE_SIZE = 1024;
    private static ConcurrentMap<String, XpathEvaluator> cache  = new ConcurrentHashMap<>(128, 0.75f, 1);

    @Getter
    private String xpathStr;
    private TokenQueue tokenQueue;

    public XpathParser(String xpathStr) {
        this.xpathStr = xpathStr;
        tokenQueue = new TokenQueue(xpathStr);
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
            throw new IllegalStateException("parse xpath \"" + xpathStr + "\" failed", e);
        }
    }

    public XpathEvaluator parse() throws XpathSyntaxErrorException {
        XpathStateMachine xpathStateMachine = new XpathStateMachine(tokenQueue);
        while (xpathStateMachine.getState() != XpathStateMachine.BuilderState.END) {
            xpathStateMachine.getState().parse(xpathStateMachine);
        }
        return xpathStateMachine.getEvaluator();
    }

    public static XpathEvaluator compile(String xpathStr) throws XpathSyntaxErrorException {
        if (xpathStr == null) {
            throw new XpathSyntaxErrorException(0,"xpathStr can not be null");
        }
        XpathEvaluator xpathEvaluator = cache.get(xpathStr);
        if(xpathEvaluator == null){
            xpathEvaluator = new XpathParser(xpathStr).parse();
            if (cache.size() < CACHE_SIZE) {
                cache.putIfAbsent(xpathStr, xpathEvaluator);
                xpathEvaluator = cache.get(xpathStr);
            }
        }
       return xpathEvaluator;
    }

}
