package com.virjar.sipsoup;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
import org.jsoup.nodes.Element;

import com.virjar.sipsoup.exception.XpathSyntaxErrorException;
import com.virjar.sipsoup.function.FunctionEnv;
import com.virjar.sipsoup.function.filter.FilterFunction;
import com.virjar.sipsoup.parse.TokenQueue;
import com.virjar.sipsoup.parse.expression.ExpressionParser;
import com.virjar.sipsoup.parse.expression.SyntaxNode;

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

        FunctionEnv.registFilterFunction(new FilterFunction() {

            private Object max(Object param1, Object param2) {

                if (new MyComparable(toComparable(param1)).compareTo(new MyComparable(toComparable(param2))) > 0) {
                    return param1;
                }
                return param2;
            }

            private Comparable toComparable(Object o) {
                if (o instanceof Comparable) {
                    return (Comparable) o;
                }
                return Integer.valueOf(0);
            }

            class MyComparable implements Comparable<Comparable> {
                private Comparable delegate;

                public MyComparable(Comparable delegate) {
                    this.delegate = delegate;
                }

                @Override
                public int compareTo(Comparable o) {
                    try {
                        return delegate.compareTo(o);
                    } catch (Exception e) {
                        try {
                            if (delegate instanceof Number || o instanceof Number) {
                                return (int) (transformToNumber(delegate).longValue()
                                        - transformToNumber(o).longValue());
                            } else {
                                return toComparable(delegate).compareTo(toComparable(o));
                            }
                        } catch (Exception e1) {
                            return 0;
                        }
                    }
                }

                private Number transformToNumber(Object o) {
                    if (o instanceof MyComparable) {
                        o = ((MyComparable) o).delegate;
                    }
                    if (o instanceof Number) {
                        return (Number) o;
                    }
                    if (o instanceof CharSequence) {
                        return NumberUtils.toDouble(o.toString(), 0.0);
                    }
                    return 0;
                }
            }

            @Override
            public Object call(Element element, List<SyntaxNode> params) {
                if (params.size() == 0) {
                    throw new RuntimeException("max function must have one parameter at lest");
                }
                Iterator<SyntaxNode> iterator = params.iterator();
                Object obj = iterator.next().calc(element);
                while (iterator.hasNext()) {
                    obj = max(obj, iterator.next().calc(element));
                }
                return obj;
            }

            @Override
            public String getName() {
                return "max";
            }
        });

        String expression = "100 - 2* max(ten(), 11, 12, 13, 20, \"这是一个字符串\")";

        SyntaxNode syntaxNode = new ExpressionParser(new TokenQueue(expression)).parse();
        Object calc = syntaxNode.calc(null);
        System.out.println(calc);
    }
}
