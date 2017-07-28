package com.virjar.sipsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;

/**
 * Created by virjar on 17/7/29.
 */
public class JsoupParseTest {
    public static void main(String[] args) {
        Document test = Jsoup.parse("test");
        System.out.println(test);

        //没用
        boolean test1 = Jsoup.isValid("test", Whitelist.none());
        System.out.println(test1);

        Document document = Jsoup.parse(null);
        System.out.println(document);
    }
}
