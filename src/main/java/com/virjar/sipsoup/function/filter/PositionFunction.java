package com.virjar.sipsoup.function.filter;

import java.util.List;

import org.jsoup.nodes.Element;

import com.virjar.sipsoup.parse.expression.SyntaxNode;
import com.virjar.sipsoup.util.XpathUtil;

/**
 * Created by virjar on 17/6/6.
 * 
 * @author virjar
 * @since 0.0.1 返回一个元素在同名兄弟节点中的位置
 */
public class PositionFunction implements FilterFunction {
    @Override
    public Object call(Element element, List<SyntaxNode> params) {
        if (params.size() > 0) {
            Object calc = params.get(0).calc(element);
            if (calc instanceof Element) {
                return XpathUtil.getElIndexInSameTags((Element) calc);
            }
        }
        return XpathUtil.getElIndexInSameTags(element);
    }

    @Override
    public String getName() {
        return "position";
    }
}
