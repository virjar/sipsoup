package com.virjar.sipsoup.parse.expression.token.handler;

import org.jsoup.nodes.Element;

import com.virjar.sipsoup.parse.expression.SyntaxNode;
import com.virjar.sipsoup.parse.expression.token.Token;
import com.virjar.sipsoup.parse.expression.token.TokenHandler;

/**
 * Created by virjar on 17/6/12.
 */
public class AttributeHandler implements TokenHandler {
    @Override
    public SyntaxNode parseToken(final String tokenStr) {
        return new SyntaxNode() {
            @Override
            public Object calc(Element element) {
                return element.attr(tokenStr);
            }

            @Override
            public Class judeResultType() {
                return String.class;
            }
        };
    }

    @Override
    public String typeName() {
        return Token.ATTRIBUTE_ACTION;
    }
}
