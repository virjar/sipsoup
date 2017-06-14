package com.virjar.sipsoup;

import org.jsoup.parser.TokenQueue;

/**
 * Created by virjar on 17/6/14.
 */
public class ConsumeToTest {
    public static void main(String[] args) {
        String testStr = "\"sdfbsdfds,s\"dfs,d";
        TokenQueue tokenQueue = new TokenQueue(testStr);
        System.out.println(tokenQueue.consumeTo(","));
    }
}
