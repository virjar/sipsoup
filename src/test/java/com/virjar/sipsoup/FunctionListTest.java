package com.virjar.sipsoup;

import java.util.List;

import com.google.common.collect.Lists;
import com.virjar.sipsoup.function.NameAware;
import com.virjar.sipsoup.function.axis.AxisFunction;
import com.virjar.sipsoup.function.filter.FilterFunction;
import com.virjar.sipsoup.function.select.SelectFunction;
import com.virjar.sipsoup.util.ClassScanner;
import com.virjar.sipsoup.util.ObjectFactory;

/**
 * Created by virjar on 17/6/15.
 */
public class FunctionListTest {
    public static void main(String[] args) {
        ClassScanner.SubClassVisitor<NameAware> functionVisitor = new ClassScanner.SubClassVisitor<NameAware>(true,
                NameAware.class);
        ClassScanner.scan(functionVisitor, Lists.newArrayList("com.virjar.sipsoup.function"));
        // 系统所有的类
        List<Class<? extends NameAware>> allFunctionClasses = functionVisitor.getSubClass();
        for (Class<? extends NameAware> clazz : allFunctionClasses) {
            NameAware nameAware = ObjectFactory.newInstance(clazz);
            if (nameAware instanceof SelectFunction) {
                System.out.println("抽取函数 :" + nameAware.getName());
            } else if (nameAware instanceof FilterFunction) {
                System.out.println("谓语过滤函数 :" + nameAware.getName());
            } else if (nameAware instanceof AxisFunction) {
                System.out.println("轴函数 :" + nameAware.getName());
            } else {
                System.out.println("非法函数 :" + nameAware.getName());
            }
        }
    }
}
