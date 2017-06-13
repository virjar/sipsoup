package com.virjar.sipsoup.parse.expression.token.consumer;

import com.virjar.sipsoup.parse.TokenQueue;
import com.virjar.sipsoup.parse.expression.token.Token;
import com.virjar.sipsoup.parse.expression.token.TokenConsumer;

/**
 * Created by virjar on 17/6/12.
 */
public class ExpressionConsumer implements TokenConsumer {
    @Override
    public String consume(TokenQueue tokenQueue) {
        // 当前遇到的串是一个括号
        if (tokenQueue.matches("(")) {
            // 括号优先级,单独处理,不在逆波兰式内部处理括号问题了,这样逻辑简单一些,而且也浪费不了太大的计算消耗
            return tokenQueue.chompBalanced('(', ')');
        }
        return null;
    }

    @Override
    public int order() {
        return 0;
    }

    @Override
    public String tokenType() {
        return Token.EXPRESSION;
    }
}
