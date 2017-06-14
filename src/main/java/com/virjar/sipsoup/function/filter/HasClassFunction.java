package com.virjar.sipsoup.function.filter;

import java.util.List;

import org.jsoup.nodes.Element;

import com.google.common.base.Preconditions;
import com.virjar.sipsoup.exception.EvaluateException;
import com.virjar.sipsoup.parse.expression.SyntaxNode;

/**
 * Created by virjar on 17/6/14.
 */
public class HasClassFunction implements FilterFunction {
    @Override
    public Object call(Element element, List<SyntaxNode> params) {
        Preconditions.checkArgument(params.size() > 0, "hasClass must have a parameter");
        Object calc = params.get(0).calc(element);
        if (!(calc instanceof String)) {
            throw new EvaluateException("hasClass params must a String ,now is " + calc);
        }
        return element.hasClass(calc.toString().trim());
    }

    @Override
    public String getName() {
        return "hasClass";
    }
}
