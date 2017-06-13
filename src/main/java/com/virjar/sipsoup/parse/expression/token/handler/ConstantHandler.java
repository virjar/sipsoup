package com.virjar.sipsoup.parse.expression.token.handler;

import org.jsoup.nodes.Element;

import com.virjar.sipsoup.parse.expression.SyntaxNode;
import com.virjar.sipsoup.parse.expression.token.Token;
import com.virjar.sipsoup.parse.expression.token.TokenHandler;

/**
 * Created by virjar on 17/6/12.
 */
public class ConstantHandler implements TokenHandler {
    @Override
    public SyntaxNode parseToken(final String tokenStr) {
        return new SyntaxNode() {
            @Override
            public Object calc(Element element) {
                return tokenStr;
            }

            @Override
            public Class judeResultType() {
                return String.class;
            }
        };
    }

    @Override
    public String typeName() {
        return Token.CONSTANT;
    }
}
