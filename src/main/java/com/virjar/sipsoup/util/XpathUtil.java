package com.virjar.sipsoup.util;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.virjar.sipsoup.exception.EvaluateException;
import com.virjar.sipsoup.exception.FinalTypeNotSameException;
import com.virjar.sipsoup.model.SIPNode;
import com.virjar.sipsoup.model.XpathEvaluator;
import com.virjar.sipsoup.parse.expression.SyntaxNode;

/**
 * @author virjar
 */
public class XpathUtil {


    /**
     * 获取同名元素在同胞中的index
     * 
     * @param e
     * @return
     */
    public static int getElIndexInSameTags(Element e) {
        Elements chs = e.parent().children();
        int index = 1;
        for (Element cur : chs) {
            if (e.tagName().equals(cur.tagName())) {
                if (e.equals(cur)) {
                    return index;
                } else {
                    index ++;
                }
            }
        }
        return index;
    }

    /**
     * 获取同胞中同名元素的数量
     * 
     * @param e
     * @return
     */
    public static int sameTagElNums(Element e) {
        Elements els = e.parent().getElementsByTag(e.tagName());
        return els.size();
    }

    public static void checkSameResultType(Collection<XpathEvaluator> xpathEvaluators)
            throws FinalTypeNotSameException {
        SIPNode.NodeType nodeType = null;
        for (XpathEvaluator xpathEvaluator : xpathEvaluators) {
            if (nodeType == null) {
                nodeType = xpathEvaluator.judeNodeType();
            } else if (nodeType != xpathEvaluator.judeNodeType()) {
                throw new FinalTypeNotSameException();
            }
        }
    }

    public static String toPlainString(Object obj) {
        if (obj == null) {
            return "null";
        }
        return obj.toString();
    }

    public static Element root(Element el) {
        while (el.parent() == null) {
            el = el.parent();
        }
        return el;
    }

    public static BigDecimal toBigDecimal(Number number) {
        if (number instanceof BigDecimal) {
            return (BigDecimal) number;
        }
        if (number instanceof Integer) {
            return new BigDecimal(number.intValue());
        }
        if (number instanceof Double || number instanceof Float) {// BigDecimal float 也是转double
            return new BigDecimal(number.doubleValue());
        }
        if (number instanceof Long) {
            return BigDecimal.valueOf(number.longValue());
        }
        return new BigDecimal(number.toString());
    }

    public static Integer firstParamToInt(List<SyntaxNode> params, Element element, String functionName) {
        if (params.size() > 0) {
            Object calc = params.get(0).calc(element);
            if (calc != null && !(calc instanceof Integer)) {
                throw new EvaluateException(functionName + " parameter must be integer");
            } else {
                if (calc != null) {
                    return (Integer) calc;
                }
            }
        }
        return null;
    }

    public static List<Element> transformToElement(List<SIPNode> SIPNodes) {
        return Lists.newLinkedList(Iterables.transform(Iterables.filter(SIPNodes, new Predicate<SIPNode>() {
            @Override
            public boolean apply(SIPNode input) {
                return input.getElement() != null;
            }
        }), new Function<SIPNode, Element>() {
            @Override
            public Element apply(SIPNode input) {
                return input.getElement();
            }
        }));
    }

    public static List<String> transformToString(List<SIPNode> SIPNodes) {
        return Lists.newLinkedList(Iterables.transform(Iterables.filter(SIPNodes, new Predicate<SIPNode>() {
            @Override
            public boolean apply(SIPNode input) {
                return input.isText();
            }
        }), new Function<SIPNode, String>() {
            @Override
            public String apply(SIPNode input) {
                return input.getTextVal();
            }
        }));
    }
}
