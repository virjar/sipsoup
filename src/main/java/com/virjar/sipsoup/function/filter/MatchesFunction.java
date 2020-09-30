package com.virjar.sipsoup.function.filter;

import java.util.List;

import org.jsoup.nodes.Element;

import com.google.common.base.Preconditions;
import com.virjar.sipsoup.exception.EvaluateException;
import com.virjar.sipsoup.parse.expression.SyntaxNode;

/**
 * Created by virjar on 17/6/15.
 */
public class MatchesFunction extends AbstractStringFunction {
    @Override
    public Object call(Element element, List<SyntaxNode> params) {
        Preconditions.checkArgument(params.size() >= 2, getName() + " must has 2 parameters");
        Object calc = params.get(1).calc(element);
        if (!(calc instanceof String)) {
            throw new EvaluateException(getName() + " second parameter is not a string:" + calc);
        }
        return firstParamToString(element, params).matches(calc.toString());
    }

    @Override
    public String getName() {
        return "matches";
    }
}
