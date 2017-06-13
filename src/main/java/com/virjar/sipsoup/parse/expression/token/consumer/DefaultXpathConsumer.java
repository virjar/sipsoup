package com.virjar.sipsoup.parse.expression.token.consumer;

import org.apache.commons.lang3.StringUtils;

import com.virjar.sipsoup.exception.XpathSyntaxErrorException;
import com.virjar.sipsoup.parse.TokenQueue;
import com.virjar.sipsoup.parse.XpathParser;
import com.virjar.sipsoup.parse.expression.token.Token;
import com.virjar.sipsoup.parse.expression.token.TokenConsumer;

/**
 * Created by virjar on 17/6/12.
 */
public class DefaultXpathConsumer implements TokenConsumer {
    @Override
    public String consume(TokenQueue tokenQueue) {
        String s = tokenQueue.tryConsumeTo(" ");
        if (StringUtils.isEmpty(s)) {
            return null;
        }
        try {
            XpathParser.compile(s);
            return tokenQueue.consumeTo(" ");
        } catch (XpathSyntaxErrorException e) {
            return null;
        }
    }

    @Override
    public int order() {
        return 80;
    }

    @Override
    public String tokenType() {
        return Token.XPATH;
    }
}
