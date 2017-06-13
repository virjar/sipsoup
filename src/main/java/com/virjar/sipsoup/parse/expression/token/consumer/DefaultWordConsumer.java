package com.virjar.sipsoup.parse.expression.token.consumer;

import com.virjar.sipsoup.parse.TokenQueue;
import com.virjar.sipsoup.parse.expression.token.Token;
import com.virjar.sipsoup.parse.expression.token.TokenConsumer;

/**
 * Created by virjar on 17/6/12.
 */
public class DefaultWordConsumer implements TokenConsumer {
    @Override
    public String consume(TokenQueue tokenQueue) {
        if (tokenQueue.matchesWord()) {
            return tokenQueue.consumeWord();
        }
        return null;
    }

    @Override
    public int order() {
        return 90;
    }

    @Override
    public String tokenType() {
        return Token.CONSTANT;
    }
}
