package com.virjar.sipsoup.parse.expression.token.consumer;

import com.virjar.sipsoup.parse.TokenQueue;
import com.virjar.sipsoup.parse.expression.token.Token;
import com.virjar.sipsoup.parse.expression.token.TokenConsumer;

/**
 * Created by virjar on 17/6/12.
 */
public class AttributeActionConsumer implements TokenConsumer {
    @Override
    public String consume(TokenQueue tokenQueue) {
        if (tokenQueue.matches("@")) {
            tokenQueue.advance();
            return tokenQueue.consumeAttributeKey();
        }
        return null;
    }

    @Override
    public int order() {
        return 10;
    }

    @Override
    public String tokenType() {
        return Token.ATTRIBUTE_ACTION;
    }
}
