package com.virjar.sipsoup;

import java.io.IOException;
import java.util.List;

import cn.hutool.core.io.FileUtil;
import com.virjar.sipsoup.model.XpathEvaluator;
import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;

import com.virjar.dungproxy.client.httpclient.HttpInvoker;
import com.virjar.sipsoup.parse.XpathParser;

/**
 * Created by virjar on 17/6/13.
 */
public class AllTextTest {
    public static void main(String[] args) throws IOException {
         String s = IOUtils.toString(AllTextTest.class.getResourceAsStream("/19-vaneShopAge.html"));
//         String s = IOUtils.toString(AllTextTest.class.getResourceAsStream("/18.html"));
//        String s = FileUtil.readString("D:\\19-vaneShopAge.html", "UTF-8");
//        String s = FileUtil.readString("D:\\18.html", "UTF-8");
        //List<String> strings = XpathParser.compileNoError("/css('.jcuo1')::self()/preceding-sibling::allText()")
         //       .evaluateToString(Jsoup.parse(HttpInvoker.get("http://news.mydrivers.com/1/537/537851.htm")));
        //css('.ad-thumb-list .inner')
        XpathEvaluator xpathEvaluator = XpathParser.compileNoError("//table[@class='el-table__body']/tbody/tr[5]/*[contains(@class,'el-table_1_column_4')]/div[@class='cell']/text()");

        List<String> strings = XpathParser.compileNoError("//table[@class='el-table__body']/tbody/tr[5]/*[contains(@class,'el-table_1_column_4')]/div[@class='cell']/text()")
                .evaluateToString(Jsoup.parse(s));
        for (String str : strings) {
            System.out.println(str);
        }
    }
}
