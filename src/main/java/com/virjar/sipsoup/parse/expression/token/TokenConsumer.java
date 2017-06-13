package com.virjar.sipsoup.parse.expression.token;

import com.virjar.sipsoup.parse.TokenQueue;

/**
 * Created by virjar on 17/6/12.<br/>
 * 消费一个token
 */
public interface TokenConsumer {
    String consume(TokenQueue tokenQueue);

    int order();

    String tokenType();
}
