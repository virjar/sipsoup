package com.virjar.sipsoup.function.filter;

import java.util.List;

import org.jsoup.nodes.Element;

import com.google.common.base.Preconditions;
import com.virjar.sipsoup.exception.EvaluateException;
import com.virjar.sipsoup.parse.expression.SyntaxNode;

/**
 * Created by virjar on 17/6/15.
 */
public class SubstringFunction extends AbStractStringFunction {
    @Override
    public Object call(Element element, List<SyntaxNode> params) {
        Preconditions.checkArgument(params.size() >= 2, getName() + " must have parameter at lest 2");
        String string = firstParamToString(element, params);
        Object index = params.get(1).calc(element);
        if (!(index instanceof Integer)) {
            throw new EvaluateException(getName() + " index must be a integer :" + index);
        }
        int end = -1;
        if (params.size() > 2) {
            Object endObj = params.get(2).calc(element);
            if (!(endObj instanceof Integer)) {
                throw new EvaluateException(getName() + "end index must be a integer :" + index);
            }
            end = (int) endObj;
        }

        return end < 0 ? string.substring((int) index) : string.substring((int) index, end);
    }

    @Override
    public String getName() {
        return "substring";
    }
}
