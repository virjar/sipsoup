package com.virjar.sipsoup.function.filter;

import java.util.Iterator;
import java.util.List;

import org.jsoup.nodes.Element;

import com.virjar.sipsoup.parse.expression.SyntaxNode;

/**
 * Created by virjar on 17/6/14.
 */
public class ConcatFunction implements FilterFunction {
    @Override
    public Object call(Element element, List<SyntaxNode> params) {
        if (params.size() == 0) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        Iterator<SyntaxNode> iterator = params.iterator();
        stringBuilder.append(iterator.next().calc(element));
        while (iterator.hasNext()) {
            stringBuilder.append(" ").append(iterator.next().calc(element));
        }
        return stringBuilder.toString();
    }

    @Override
    public String getName() {
        return "concat";
    }
}
