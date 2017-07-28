package com.virjar.sipsoup.parse.expression.token.handler;

import org.jsoup.nodes.Element;

import com.google.common.collect.Lists;
import com.virjar.sipsoup.exception.XpathSyntaxErrorException;
import com.virjar.sipsoup.model.SIPNode;
import com.virjar.sipsoup.model.XpathEvaluator;
import com.virjar.sipsoup.parse.XpathParser;
import com.virjar.sipsoup.parse.expression.SyntaxNode;
import com.virjar.sipsoup.parse.expression.token.Token;
import com.virjar.sipsoup.parse.expression.token.TokenHandler;

/**
 * Created by virjar on 17/6/12.
 */
public class XpathHandler implements TokenHandler {
    @Override
    public SyntaxNode parseToken(String tokenStr) throws XpathSyntaxErrorException {
        final XpathEvaluator xpathEvaluator = new XpathParser(tokenStr).parse();
        return new SyntaxNode() {
            @Override
            public Object calc(Element element) {
                return xpathEvaluator.evaluate(element);
            }

            @Override
            public Class judeResultType() {
                return String.class;
            }
        };
    }

    @Override
    public String typeName() {
        return Token.XPATH;
    }
}
