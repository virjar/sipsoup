package com.virjar.sipsoup.function.select;

import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.common.collect.Lists;
import com.virjar.sipsoup.model.SIPNode;
import com.virjar.sipsoup.model.XpathNode;

/**
 * Created by virjar on 17/6/6.
 * 
 * @since 0.0.1
 * @author virjar 获取全部节点的内部的html
 */
public class HtmlFunction implements SelectFunction {

    @Override
    public List<SIPNode> call(XpathNode.ScopeEm scopeEm, Elements elements, List<String> args) {
        List<SIPNode> res = Lists.newLinkedList();
        if (elements != null && elements.size() > 0) {
            for (Element e : elements) {
                res.add(SIPNode.t(e.html()));
            }
        }
        return res;
    }

    @Override
    public String getName() {
        return "html";
    }
}
