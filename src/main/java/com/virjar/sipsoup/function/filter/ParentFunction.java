package com.virjar.sipsoup.function.filter;

import java.util.List;

import org.jsoup.nodes.Element;

import com.virjar.sipsoup.parse.expression.SyntaxNode;
import com.virjar.sipsoup.util.XpathUtil;

/**
 * Created by virjar on 17/6/15.
 */
public class ParentFunction implements FilterFunction {
    @Override
    public Object call(Element element, List<SyntaxNode> params) {
        int index = 1;
        Integer integer = XpathUtil.firstParamToInt(params, element, getName());
        if (integer != null) {
            index = integer;
        }
        for (int i = 0; i < index; i++) {
            element = element.parent();
        }
        return element;
    }

    @Override
    public String getName() {
        return "parent";
    }
}
