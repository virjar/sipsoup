package com.virjar.sipsoup.function.filter;

import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
import org.jsoup.nodes.Element;

import com.google.common.base.Preconditions;
import com.virjar.sipsoup.parse.expression.SyntaxNode;

/**
 * Created by virjar on 17/6/14.
 */
public class ToDoubleFunction implements FilterFunction {
    @Override
    public Object call(Element element, List<SyntaxNode> params) {
        Preconditions.checkArgument(params.size() > 0, getName() + " at last has one parameter");
        Object calc = params.get(0).calc(element);
        if (calc instanceof Double) {
            return calc;
        }
        if (calc == null) {
            return null;
        }

        if (params.size() > 1) {
            Object defaultValue = params.get(1).calc(element);

            Preconditions.checkArgument(defaultValue != null && defaultValue instanceof Double,
                    getName() + " parameter 2 must to be a Double now is:" + defaultValue);
            return NumberUtils.toDouble(calc.toString(), (Double) defaultValue);
        }
        return NumberUtils.toDouble(calc.toString());
    }

    @Override
    public String getName() {
        return "toDouble";
    }
}
