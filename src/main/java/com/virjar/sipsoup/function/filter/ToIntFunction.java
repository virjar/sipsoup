package com.virjar.sipsoup.function.filter;

import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
import org.jsoup.nodes.Element;

import com.google.common.base.Preconditions;
import com.virjar.sipsoup.parse.expression.SyntaxNode;

/**
 * Created by virjar on 17/6/14.
 */
public class ToIntFunction implements FilterFunction {
    @Override
    public Object call(Element element, List<SyntaxNode> params) {
        Preconditions.checkArgument(params.size() > 0, getName() + " at last has one parameter");
        Object calc = params.get(0).calc(element);
        if (calc instanceof Integer) {
            return calc;
        }
        if (calc == null) {
            return null;
        }

        if (params.size() > 1) {
            Object defaultValue = params.get(1).calc(element);

            Preconditions.checkArgument(defaultValue != null && defaultValue instanceof Integer,
                    getName() + " parameter 2 must to be a integer now is:" + defaultValue);
            return NumberUtils.toInt(calc.toString(), (Integer) defaultValue);
        }
        return NumberUtils.toInt(calc.toString());
    }

    @Override
    public String getName() {
        return "toInt";
    }
}
