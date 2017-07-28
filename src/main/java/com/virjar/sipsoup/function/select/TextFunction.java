package com.virjar.sipsoup.function.select;

import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.common.collect.Lists;
import com.virjar.sipsoup.model.SIPNode;
import com.virjar.sipsoup.model.XpathNode;

/**
 * Created by virjar on 17/6/6.
 */
public class TextFunction implements SelectFunction {
    /**
     * 只获取节点自身的子文本
     *
     */
    @Override
    public List<SIPNode> call(XpathNode.ScopeEm scopeEm, Elements elements, List<String> args) {
        List<SIPNode> res = Lists.newLinkedList();
        if (elements != null && elements.size() > 0) {
            for (Element e : elements) {
                if (e.nodeName().equals("script")) {
                    res.add(SIPNode.t(e.data()));
                } else {
                    res.add(SIPNode.t(e.ownText()));
                }
            }
        }
        return res;
    }

    @Override
    public String getName() {
        return "text";
    }
}
