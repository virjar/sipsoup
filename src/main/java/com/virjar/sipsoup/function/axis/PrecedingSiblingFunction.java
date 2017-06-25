package com.virjar.sipsoup.function.axis;

import java.util.LinkedList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.common.collect.Lists;

/**
 * Created by virjar on 17/6/6.
 * 
 * @author virjar
 * @since 0.0.1 节点前面的全部同胞节点，preceding-sibling
 */
public class PrecedingSiblingFunction implements AxisFunction {
    @Override
    public Elements call(Element e, List<String> args) {
        Element tmp = e.previousElementSibling();
        LinkedList<Element> tempList = Lists.newLinkedList();
        while (tmp != null) {
            tempList.addFirst(tmp);
            tmp = tmp.previousElementSibling();
        }
        return new Elements(tempList);
    }

    @Override
    public String getName() {
        return "preceding-sibling";
    }
}
