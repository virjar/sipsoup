package com.virjar.sipsoup.function.axis;

import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by virjar on 17/6/6.
 * 
 * @author virjar
 * @since 0.0.1 全部祖先节点和自身节点
 */
public class AncestorOrSelfFunction implements AxisFunction {
    @Override
    public Elements call(Element e, List<String> args) {
        Elements rs = e.parents();
        rs.add(e);
        return rs;
    }

    @Override
    public String getName() {
        return "ancestorOrSelf";
    }
}
