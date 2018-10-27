package com.virjar.sipsoup;

import com.alibaba.fastjson.JSONObject;
import com.virjar.sipsoup.exception.XpathSyntaxErrorException;
import com.virjar.sipsoup.model.SipNodes;
import com.virjar.sipsoup.model.XpathEvaluator;
import com.virjar.sipsoup.parse.XpathParser;
import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;

import java.io.IOException;

/**
 * Created by virjar on 2018/10/27.
 */
public class ParentTest {
    public static void main(String[] args) throws XpathSyntaxErrorException, IOException {
        XpathEvaluator xpathEvaluator = XpathParser.compile(
                "//div[@class='data-card-area']//div[@class='card-detail']//div[contains(allText(),'售后处理时长')]/..//span[@class='b_1_score']/text()");
        String s = IOUtils.toString(ParentTest.class.getResourceAsStream("/parent_test.html"));
        SipNodes evaluate = xpathEvaluator.evaluate(Jsoup.parse(s));
        System.out.println(JSONObject.toJSONString(evaluate));
    }
}
