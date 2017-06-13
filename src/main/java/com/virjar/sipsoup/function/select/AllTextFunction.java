package com.virjar.sipsoup.function.select;

import java.util.LinkedList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.virjar.sipsoup.model.SIPNode;
import com.virjar.sipsoup.model.XpathNode;

/**
 * Created by virjar on 17/6/6.
 * 
 * @since 0.0.1
 * @author virjar 递归获取节点内全部的纯文本
 */
public class AllTextFunction implements SelectFunction {
    @Override
    public List<SIPNode> call(XpathNode.ScopeEm scopeEm, Elements elements, List<String> args) {
        List<SIPNode> res = new LinkedList<SIPNode>();
        if (elements != null && elements.size() > 0) {
            for (Element e : elements) {
                res.add(SIPNode.t(e.text()));
            }
        }
        return res;
    }

    @Override
    public String getName() {
        return "allText";
    }
}
