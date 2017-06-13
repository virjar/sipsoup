package com.virjar.sipsoup.function.select;

import java.util.List;

import org.jsoup.select.Elements;

import com.virjar.sipsoup.function.NameAware;
import com.virjar.sipsoup.model.JXNode;
import com.virjar.sipsoup.model.XpathNode;

/**
 * Created by virjar on 17/6/6.
 */
public interface SelectFunction extends NameAware {
    List<JXNode> call(XpathNode.ScopeEm scopeEm, Elements elements, List<String> args);

}
