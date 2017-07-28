package com.virjar.sipsoup.exception;

/**
 * 使用不存在的轴语法则抛出此异常
 * 
 * @author github.com/zhegexiaohuozi [seimimaster@gmail.com] Date: 14-3-15
 */
public class NoSuchAxisException extends XpathSyntaxErrorException {
    public NoSuchAxisException(int errorPos, String msg) {
        super(errorPos, msg);
    }
}
