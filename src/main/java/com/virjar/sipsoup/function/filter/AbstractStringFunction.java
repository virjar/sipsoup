package com.virjar.sipsoup.function.filter;

import java.util.List;

import org.jsoup.nodes.Element;

import com.google.common.base.Preconditions;
import com.virjar.sipsoup.exception.EvaluateException;
import com.virjar.sipsoup.parse.expression.SyntaxNode;

/**
 * Created by virjar on 17/6/15.
 */
public abstract class AbstractStringFunction implements FilterFunction {
    protected String firstParamToString(Element element, List<SyntaxNode> params) {
        Preconditions.checkArgument(params.size() > 0, getName() + " must have parameter at lest 1");
        Object string = params.get(0).calc(element);
        if (!(string instanceof String)) {
            throw new EvaluateException(getName() + " first parameter is not a string :" + string);
        }
        return string.toString();
    }
}
