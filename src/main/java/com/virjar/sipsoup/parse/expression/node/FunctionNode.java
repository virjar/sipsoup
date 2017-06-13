package com.virjar.sipsoup.parse.expression.node;

import java.util.List;

import org.jsoup.nodes.Element;

import com.virjar.sipsoup.function.filter.FilterFunction;
import com.virjar.sipsoup.parse.expression.SyntaxNode;

/**
 * Created by virjar on 17/6/10.
 */
public class FunctionNode implements SyntaxNode {
    private FilterFunction filterFunction;
    private List<SyntaxNode> filterFunctionParams;

    public FunctionNode(FilterFunction filterFunction, List<SyntaxNode> filterFunctionParams) {
        this.filterFunction = filterFunction;
        this.filterFunctionParams = filterFunctionParams;
    }

    @Override
    public Object calc(Element element) {
        return filterFunction.call(element, filterFunctionParams);
    }

    @Override
    public Class judeResultType() {
        return null;// TODO
    }
}
