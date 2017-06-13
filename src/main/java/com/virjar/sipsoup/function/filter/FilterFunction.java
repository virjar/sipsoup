package com.virjar.sipsoup.function.filter;

import java.util.List;

import org.jsoup.nodes.Element;

import com.virjar.sipsoup.function.NameAware;
import com.virjar.sipsoup.parse.expression.SyntaxNode;

/**
 * Created by virjar on 17/6/6.
 * 
 * @since 0.0.1
 * @author virjar 谓语过滤方法,基于这个接口实现
 */
public interface FilterFunction extends NameAware {
    Object call(Element element, List<SyntaxNode> params);
}
