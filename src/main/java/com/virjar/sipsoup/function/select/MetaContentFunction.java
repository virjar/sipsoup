package com.virjar.sipsoup.function.select;

import com.virjar.sipsoup.model.SIPNode;
import com.virjar.sipsoup.model.XpathNode;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by virjar on 17/6/6.
 * 
 * @author Clive.hua
 * @since 0.0.1 获取meta中content属性值。方便获取关键字、描述等信息。
 * 如：xpath:  //meta[@name='description']/metaContent()
 *     xpath:  //meta[@name='keywords']/metaContent()
 */
public class MetaContentFunction implements SelectFunction {
    @Override
    public List<SIPNode> call(XpathNode.ScopeEm scopeEm, Elements elements, List<String> args) {
        List<SIPNode> res = new LinkedList<SIPNode>();
        if (elements != null && elements.size() > 0) {
            for (Element e : elements) {
                res.add(SIPNode.t(e.attr("content")));
            }
        }
        return res;
    }

    @Override
    public String getName() {
        return "metaContent";
    }
}
