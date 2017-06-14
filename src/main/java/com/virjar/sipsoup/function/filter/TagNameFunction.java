package com.virjar.sipsoup.function.filter;

import java.util.List;

import org.jsoup.nodes.Element;

import com.virjar.sipsoup.parse.expression.SyntaxNode;

/**
 * Created by virjar on 17/6/14.
 * 
 * @since 0.0.1
 * @author virjar 获取当前节点的节点名称
 */
public class TagNameFunction implements FilterFunction {
    @Override
    public Object call(Element element, List<SyntaxNode> params) {
        return element.tagName();
    }

    @Override
    public String getName() {
        return "tagName";
    }
}
