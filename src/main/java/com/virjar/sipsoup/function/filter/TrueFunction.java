package com.virjar.sipsoup.function.filter;

import java.util.List;

import org.jsoup.nodes.Element;

import com.virjar.sipsoup.parse.expression.SyntaxNode;

/**
 * Created by virjar on 17/6/15.
 */
public class TrueFunction implements FilterFunction {
    @Override
    public Object call(Element element, List<SyntaxNode> params) {
        return true;
    }

    @Override
    public String getName() {
        return "true";
    }
}
