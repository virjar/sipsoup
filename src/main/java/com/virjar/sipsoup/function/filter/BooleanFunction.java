package com.virjar.sipsoup.function.filter;

import java.util.List;

import org.apache.commons.lang3.BooleanUtils;
import org.jsoup.nodes.Element;

import com.virjar.sipsoup.parse.expression.SyntaxNode;

/**
 * Created by virjar on 17/6/15.
 */
public class BooleanFunction implements FilterFunction {
    @Override
    public Object call(Element element, List<SyntaxNode> params) {
        if (params.size() == 0) {
            return false;
        }
        Object calc = params.get(0).calc(element);
        if (calc == null) {
            return false;
        }
        if (calc instanceof Boolean) {
            return calc;
        }
        if (calc instanceof String) {
            return BooleanUtils.toBoolean(calc.toString());
        }
        if (calc instanceof Integer) {
            return calc != 0;
        }
        if (calc instanceof Number) {
            return ((Number) calc).doubleValue() > 0D;
        }
        return false;
    }

    @Override
    public String getName() {
        return "boolean";
    }
}
