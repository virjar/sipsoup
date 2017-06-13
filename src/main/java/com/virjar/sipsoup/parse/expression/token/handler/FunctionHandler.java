package com.virjar.sipsoup.parse.expression.token.handler;

import com.virjar.sipsoup.exception.XpathSyntaxErrorException;
import com.virjar.sipsoup.parse.TokenQueue;
import com.virjar.sipsoup.parse.expression.FunctionParser;
import com.virjar.sipsoup.parse.expression.SyntaxNode;
import com.virjar.sipsoup.parse.expression.token.Token;
import com.virjar.sipsoup.parse.expression.token.TokenHandler;

/**
 * Created by virjar on 17/6/12.
 */
public class FunctionHandler implements TokenHandler {
    @Override
    public SyntaxNode parseToken(String tokenStr) throws XpathSyntaxErrorException {
        return new FunctionParser(new TokenQueue(tokenStr)).parse();
    }

    @Override
    public String typeName() {
        return Token.FUNCTION;
    }
}
