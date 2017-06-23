package com.virjar.sipsoup.model;

import org.jsoup.nodes.Element;

import com.google.common.collect.Lists;
import com.virjar.sipsoup.function.FunctionEnv;
import com.virjar.sipsoup.parse.expression.SyntaxNode;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * xpath语法节点的谓语部分，即要满足的限定条件
 * 
 * @author virjar
 * @since 0.0.1
 */
@Slf4j
public class Predicate {

    private SyntaxNode syntaxNode;
    @Getter
    private String predicateStr;

    public Predicate(String predicateStr, SyntaxNode syntaxNode) {
        this.predicateStr = predicateStr;
        this.syntaxNode = syntaxNode;
    }

    boolean isValid(Element element) {
        return (Boolean) FunctionEnv.getFilterFunction("sipSoupPredictJudge").call(element,
                Lists.newArrayList(syntaxNode));
    }
}
