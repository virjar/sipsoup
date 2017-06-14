package com.virjar.sipsoup.function.filter;

import java.util.List;

import org.jsoup.nodes.Element;

import com.virjar.sipsoup.parse.expression.SyntaxNode;
import com.virjar.sipsoup.util.XpathUtil;

/**
 * Created by virjar on 17/6/15.
 */
public class RootFunction implements FilterFunction {
    @Override
    public Object call(Element element, List<SyntaxNode> params) {
        if (params.size() > 0) {
            Object calc = params.get(0).calc(element);
            if (calc instanceof Element) {
                return XpathUtil.root((Element) calc);
            }
        }
        return XpathUtil.root(element);
    }

    @Override
    public String getName() {
        return "root";
    }
}
