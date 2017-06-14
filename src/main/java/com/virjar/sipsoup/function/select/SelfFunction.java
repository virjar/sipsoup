package com.virjar.sipsoup.function.select;

import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.virjar.sipsoup.model.SIPNode;
import com.virjar.sipsoup.model.XpathNode;

/**
 * Created by virjar on 17/6/14.
 */
public class SelfFunction implements SelectFunction {
    @Override
    public List<SIPNode> call(XpathNode.ScopeEm scopeEm, Elements elements, List<String> args) {
        return Lists.transform(elements, new Function<Element, SIPNode>() {
            @Override
            public SIPNode apply(Element input) {
                return SIPNode.e(input);
            }
        });
    }

    @Override
    public String getName() {
        return "self";
    }
}
