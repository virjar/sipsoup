package com.virjar.sipsoup;

import java.util.List;

import org.jsoup.nodes.Element;

import com.virjar.sipsoup.exception.XpathSyntaxErrorException;
import com.virjar.sipsoup.function.FunctionEnv;
import com.virjar.sipsoup.function.filter.FilterFunction;
import com.virjar.sipsoup.parse.XpathParser;
import com.virjar.sipsoup.parse.expression.SyntaxNode;

/**
 * Created by virjar on 17/6/12.
 */
public class RegisterNewFunctionTest {
    public static void main(String[] args) throws XpathSyntaxErrorException {
        try {
            XpathParser.compile("//a[text(text(text('1234',test())))='公司简介']");

        } catch (XpathSyntaxErrorException e) {
            System.out.println("语法错误:" + e.getMessage());
            e.printStackTrace();
        }

        FunctionEnv.registFilterFunction(new FilterFunction() {
            @Override
            public Object call(Element element, List<SyntaxNode> params) {
                return "我的返回值永远是这个文本";
            }

            @Override
            public String getName() {
                return "test";
            }
        });

        XpathParser.compile("//a[text(text(text('1234',test())))='公司简介']");
        System.out.println("编译成功");

    }
}