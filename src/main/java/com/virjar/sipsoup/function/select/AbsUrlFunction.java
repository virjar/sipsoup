package com.virjar.sipsoup.function.select;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Element;

import com.virjar.sipsoup.model.SIPNode;

/**
 * Created by virjar on 17/6/16.
 */
public class AbsUrlFunction extends AttrBaseFunction {

    @Override
    public void handle(boolean allAttr, String attrKey, Element element, List<SIPNode> ret) {
        if (allAttr) {
            for (Attribute attribute : element.attributes()) {
                ret.add(SIPNode.t(element.absUrl(attribute.getKey())));
            }
        } else {
            String value = element.absUrl(attrKey);
            if (StringUtils.isNotBlank(value)) {
                ret.add(SIPNode.t(value));
            }
        }
    }

    @Override
    public String getName() {
        return "absUrl";
    }
}
