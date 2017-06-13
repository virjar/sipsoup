package com.virjar.sipsoup.function.filter;

import java.util.List;

import org.jsoup.nodes.Element;

import com.google.common.base.Preconditions;
import com.virjar.sipsoup.parse.expression.SyntaxNode;

/**
 * Created by virjar on 17/6/13.
 */
public class ContainsFunction implements FilterFunction {
    @Override
    public Object call(Element element, List<SyntaxNode> params) {
        Preconditions.checkArgument(params.size() > 2, "contains need 2 params");
        return params.get(0).calc(element).toString().contains(params.get(1).toString());
    }

    @Override
    public String getName() {
        return "contains";
    }
}
