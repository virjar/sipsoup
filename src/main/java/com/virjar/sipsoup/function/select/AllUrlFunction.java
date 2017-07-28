package com.virjar.sipsoup.function.select;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import org.jsoup.select.Collector;
import org.jsoup.select.Elements;
import org.jsoup.select.Evaluator;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.virjar.sipsoup.model.SIPNode;
import com.virjar.sipsoup.model.XpathNode;

/**
 * Created by virjar on 17/7/28.<br/>
 * 抽取文本中的所有URL,同时解决相对路径的问题,请注意Jsoup的解析入口,需要传递文档URL才行
 * 
 * @author virjar
 * @since 1.4
 */
public class AllUrlFunction implements SelectFunction {
    private static Map<String, String> urlAttrkey = Maps.newHashMap();
    static {
        urlAttrkey.put("a", "href");
        urlAttrkey.put("link", "href");
        urlAttrkey.put("script", "src");
        urlAttrkey.put("img", "src");
        urlAttrkey.put("iframe", "src");
    }

    @Override
    public List<SIPNode> call(XpathNode.ScopeEm scopeEm, Elements elements, List<String> args) {
        List<Element> candidates = Lists.newLinkedList();
        for (Element el : elements) {
            candidates.addAll(Collector.collect(urlContainerEvaluator, el));
        }
        List<SIPNode> ret = Lists.newLinkedList();
        for (Element el : candidates) {
            String url = el.absUrl(urlAttrkey.get(el.tagName()));
            if (StringUtils.isBlank(url)) {
                continue;
            }
            try {
                new URI(url);
            } catch (URISyntaxException x) {
                continue;
            }
            ret.add(SIPNode.t(url));
        }
        return ret;
    }

    @Override
    public String getName() {
        return "allUrl";
    }

    private static URLContainerEvaluator urlContainerEvaluator = new URLContainerEvaluator();

    private static class URLContainerEvaluator extends Evaluator {
        private static Set<String> urlContainerTags = Sets.newHashSet();
        static {
            urlContainerTags.add("a");// 超链接
            urlContainerTags.add("script");// js 标签
            urlContainerTags.add("link");// css样式链接
            urlContainerTags.add("img");// 图片
            urlContainerTags.add("iframe");// 框架集
        }

        @Override
        public boolean matches(Element root, Element element) {
            return urlContainerTags.contains(element.tagName());
        }
    }
}
