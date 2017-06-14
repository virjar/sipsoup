package com.virjar.sipsoup.function.filter;

import java.util.List;

import org.jsoup.nodes.Element;

import com.virjar.sipsoup.parse.expression.SyntaxNode;

/**
 * Created by virjar on 17/6/15.
 */
public class TryExeptionFunction implements FilterFunction {
    @Override
    public Object call(Element element, List<SyntaxNode> params) {
        if (params.size() == 0) {
            return true;
        }
        try {
            return params.get(0).calc(element);
        } catch (Exception e) {
            if (params.size() > 1) {
                return params.get(1).calc(element);
            } else {
                return false;
            }
        }
    }

    @Override
    public String getName() {
        return "try";
    }
}
