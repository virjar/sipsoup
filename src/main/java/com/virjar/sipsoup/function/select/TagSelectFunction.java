package com.virjar.sipsoup.function.select;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.virjar.sipsoup.model.SIPNode;
import com.virjar.sipsoup.model.XpathNode;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

/**
 * Created by virjar on 17/6/11.
 */
public class TagSelectFunction implements SelectFunction {
    @Override
    public List<SIPNode> call(XpathNode.ScopeEm scopeEm, Elements elements, List<String> args) {
        String tagName = args.get(0);
        List<Element> temp = Lists.newLinkedList();

        if (scopeEm == XpathNode.ScopeEm.RECURSIVE || scopeEm == XpathNode.ScopeEm.CURREC) {// 递归模式
            if ("*".equals(tagName)) {
                for (Element element : elements) {
                    temp.addAll(element.getAllElements());
                }
            } else {
                temp.addAll(elements.select(tagName));
            }
            if (scopeEm == XpathNode.ScopeEm.RECURSIVE) {
                //向下递归,不应该包含自身
                temp.removeAll(elements);
            }
            return Lists.transform(temp, new Function<Element, SIPNode>() {
                @Override
                public SIPNode apply(Element input) {
                    return SIPNode.e(input);
                }
            });
        }

        // 直接子代查找
        if ("*".equals(tagName)) {
            for (Element element : elements) {
                temp.addAll(element.children());
            }
        } else {
            for (Element element : elements) {
                for (Element child : element.children()) {
                    if (StringUtils.equals(child.tagName(), tagName)) {
                        temp.add(child);
                    }
                }
            }
        }
        return Lists.transform(temp, new Function<Element, SIPNode>() {
            @Override
            public SIPNode apply(Element input) {
                return SIPNode.e(input);
            }
        });
    }

    @Override
    public String getName() {
        return "tag";
    }
}
