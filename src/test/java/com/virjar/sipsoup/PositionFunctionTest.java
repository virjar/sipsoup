package com.virjar.sipsoup;

import java.io.IOException;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;

import com.virjar.sipsoup.parse.XpathParser;

/**
 * Created by virjar on 17/6/13.
 */
public class PositionFunctionTest {
    public static void main(String[] args) throws IOException {
        String s = IOUtils.toString(AllTextTest.class.getResourceAsStream("/道重沙由美.html"));
        // 取所有偶数行的链接
        List<String> strings = XpathParser
                .compileNoError("//css('.ad-thumb-list .inner')::self()/parent::li[(position()+8)   %2=0]//a/@href")
                .evaluateToString(Jsoup.parse(s));
        for (String str : strings) {
            System.out.println(str);
        }
        System.out.println("总记录:" + strings.size());

        // 取所有行的数据
        strings = XpathParser.compileNoError("//css('.ad-thumb-list .inner')::a/@href")
                .evaluateToString(Jsoup.parse(s));
        for (String str : strings) {
            System.out.println(str);
        }
        System.out.println("总记录:" + strings.size());
    }
}
