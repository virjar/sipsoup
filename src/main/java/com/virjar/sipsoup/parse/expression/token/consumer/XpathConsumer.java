package com.virjar.sipsoup.parse.expression.token.consumer;

import com.virjar.sipsoup.parse.TokenQueue;
import com.virjar.sipsoup.parse.expression.token.Token;
import com.virjar.sipsoup.parse.expression.token.TokenConsumer;

/**
 * Created by virjar on 17/6/12.
 */
public class XpathConsumer implements TokenConsumer {
    @Override
    public String consume(TokenQueue tokenQueue) {
        // xpath子串
        if (tokenQueue.matches("`")) {
            return tokenQueue.chompBalanced('`', '`');
        }
        return null;
    }

    @Override
    public int order() {
        return 20;
    }

    @Override
    public String tokenType() {
        return Token.XPATH;
    }
}
