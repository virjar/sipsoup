package com.virjar.sipsoup.function.filter;

import java.util.List;

import org.jsoup.nodes.Element;

import com.virjar.sipsoup.parse.expression.SyntaxNode;

/**
 * Created by virjar on 17/6/6.
 * 
 * @since 0.0.1
 * @author virjar 获取元素下面的全部文本
 */
public class AllTextFunction implements FilterFunction {
    @Override
    public Object call(Element element, List<SyntaxNode> params) {
        return element.text();
    }

    @Override
    public String getName() {
        return "allText";
    }
}
