package com.virjar.sipsoup;

import java.io.IOException;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;

import com.virjar.sipsoup.parse.XpathParser;

/**
 * Created by virjar on 17/6/13.
 */
public class AllTextTest {
    public static void main(String[] args) throws IOException {
        String s = IOUtils.toString(AllTextTest.class.getResourceAsStream("/html难.html"));
        List<String> strings = XpathParser.compileNoError("//p/allText()").evaluateToString(Jsoup.parse(s));
        for (String str : strings) {
            System.out.println(str);
        }
    }
}
