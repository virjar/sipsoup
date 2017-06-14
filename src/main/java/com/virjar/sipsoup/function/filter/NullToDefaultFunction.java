package com.virjar.sipsoup.function.filter;

import java.util.List;

import org.jsoup.nodes.Element;

import com.google.common.base.Preconditions;
import com.virjar.sipsoup.parse.expression.SyntaxNode;

/**
 * Created by virjar on 17/6/14.
 * 
 * @author virjar
 * @since 0.0.1 "nullToDefault(myInteger(),0)" this function return "0",if myInteger() return null,or return myInteger()
 *        answer if answer is not null
 */
public class NullToDefaultFunction implements FilterFunction {
    @Override
    public Object call(Element element, List<SyntaxNode> params) {
        Preconditions.checkArgument(params.size() > 2, getName() + " must have 2 parameter");
        Object calc = params.get(0).calc(element);
        if (calc != null) {
            return calc;
        }
        return params.get(1).calc(element);
    }

    @Override
    public String getName() {
        return "nullToDefault";
    }
}
