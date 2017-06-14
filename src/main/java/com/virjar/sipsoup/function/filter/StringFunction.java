package com.virjar.sipsoup.function.filter;

import java.util.List;

import org.jsoup.nodes.Element;

import com.google.common.base.Preconditions;
import com.virjar.sipsoup.parse.expression.SyntaxNode;

/**
 * Created by virjar on 17/6/14.
 */
public class StringFunction implements FilterFunction {
    @Override
    public Object call(Element element, List<SyntaxNode> params) {
        Preconditions.checkArgument(params.size() > 0, getName() + " must has one parameter");
        Object calc = params.get(0).calc(element);
        if (calc == null) {
            return "null";
        }
        return calc.toString();
    }

    @Override
    public String getName() {
        return "string";
    }
}
