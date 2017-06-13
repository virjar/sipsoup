package com.virjar.sipsoup.parse.expression.token;

import com.virjar.sipsoup.exception.XpathSyntaxErrorException;
import com.virjar.sipsoup.parse.expression.SyntaxNode;

/**
 * Created by virjar on 17/6/12.
 */
public interface TokenHandler {
    SyntaxNode parseToken(String tokenStr) throws XpathSyntaxErrorException;

    String typeName();
}
