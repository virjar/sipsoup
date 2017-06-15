package com.virjar.sipsoup.util;

/*
 * Copyright 2014 Wang Haomiao<et.tw@163.com> Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and limitations under the
 * License.
 */

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.virjar.sipsoup.exception.EvaluateException;
import com.virjar.sipsoup.exception.FinalTypeNotSameException;
import com.virjar.sipsoup.model.SIPNode;
import com.virjar.sipsoup.model.XpathEvaluator;
import com.virjar.sipsoup.parse.expression.SyntaxNode;

/**
 * @author: github.com/zhegexiaohuozi [seimimaster@gmail.com] Date: 14-3-15
 */
public class XpathUtil {
    public static String getJMethodNameFromStr(String str) {
        if (str.contains("-")) {
            String[] pies = str.split("-");
            StringBuilder sb = new StringBuilder(pies[0]);
            for (int i = 1; i < pies.length; i++) {
                sb.append(pies[i].substring(0, 1).toUpperCase()).append(pies[i].substring(1));
            }
            return sb.toString();
        }
        return str;
    }

    /**
     * 获取同名元素在同胞中的index
     * 
     * @param e
     * @return
     */
    public static int getElIndexInSameTags(Element e) {
        Elements chs = e.parent().children();
        int index = 1;
        for (int i = 0; i < chs.size(); i++) {
            Element cur = chs.get(i);
            if (e.tagName().equals(cur.tagName())) {
                if (e.equals(cur)) {
                    break;
                } else {
                    index += 1;
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
            return new BigDecimal(number.longValue());
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
}
