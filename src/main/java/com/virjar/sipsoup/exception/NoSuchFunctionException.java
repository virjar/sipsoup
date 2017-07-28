package com.virjar.sipsoup.exception;

/**
 * @author github.com/zhegexiaohuozi [seimimaster@gmail.com] Date: 14-3-16
 */
public class NoSuchFunctionException extends XpathSyntaxErrorException {
    public NoSuchFunctionException(int errorPos, String msg) {
        super(errorPos, msg);
    }
}
