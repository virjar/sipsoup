package com.virjar.sipsoup;

import com.virjar.sipsoup.exception.XpathSyntaxErrorException;
import com.virjar.sipsoup.function.FunctionEnv;
import com.virjar.sipsoup.function.filter.FilterFunction;
import com.virjar.sipsoup.parse.TokenQueue;
import com.virjar.sipsoup.parse.expression.ExpressionParser;
import com.virjar.sipsoup.parse.expression.SyntaxNode;
import org.jsoup.nodes.Element;

import java.util.List;

/**
 * Created by virjar on 2017/8/16.
 */
public class CalculatorTest {
    public static void main(String[] args) throws XpathSyntaxErrorException {
        FunctionEnv.registFilterFunction(new FilterFunction() {
            @Override
            public Object call(Element element, List<SyntaxNode> params) {
                return 10;
            }

            @Override
            public String getName() {
                return "ten";
            }
        });
        String expression = "100 - 5*ten()";

        SyntaxNode syntaxNode = new ExpressionParser(new TokenQueue(expression)).parse();
        Object calc = syntaxNode.calc(null);
        System.out.println(calc);
    }
}
