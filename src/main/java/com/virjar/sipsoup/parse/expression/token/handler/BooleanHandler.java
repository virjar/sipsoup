package com.virjar.sipsoup.parse.expression.token.handler;

import org.apache.commons.lang3.BooleanUtils;

import com.google.common.collect.Lists;
import com.virjar.sipsoup.exception.XpathSyntaxErrorException;
import com.virjar.sipsoup.function.FunctionEnv;
import com.virjar.sipsoup.parse.expression.SyntaxNode;
import com.virjar.sipsoup.parse.expression.node.FunctionNode;
import com.virjar.sipsoup.parse.expression.token.Token;
import com.virjar.sipsoup.parse.expression.token.TokenHandler;

/**
 * Created by virjar on 17/6/12.
 */
public class BooleanHandler implements TokenHandler {
    @Override
    public SyntaxNode parseToken(final String tokenStr) throws XpathSyntaxErrorException {
        return new FunctionNode(FunctionEnv.getFilterFunction(BooleanUtils.toBoolean(tokenStr) ? "true" : "false"),
                Lists.<SyntaxNode> newLinkedList());
    }

    @Override
    public String typeName() {
        return Token.BOOLEAN;
    }
}
