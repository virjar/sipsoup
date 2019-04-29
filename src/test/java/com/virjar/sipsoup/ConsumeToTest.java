package com.virjar.sipsoup;

import org.jsoup.parser.TokenQueue;

/**
 * Created by virjar on 17/6/14.
 */
public class ConsumeToTest {
    public static void main(String[] args) {
        String testStr = "//table[@class='el-table__body']/tbody/*[@class='el-table__row'][5]/*[@class='el-table_1_column_4')]/div[@class='cell']/text()";
        TokenQueue tokenQueue = new TokenQueue(testStr);
        System.out.println(tokenQueue.consumeTo(","));
    }
}
