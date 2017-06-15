package com.virjar.sipsoup.parse.expression.operator;

import java.math.BigDecimal;

import org.jsoup.nodes.Element;

import com.virjar.sipsoup.parse.expression.node.AlgorithmUnit;
import com.virjar.sipsoup.util.XpathUtil;

/**
 * Created by virjar on 17/6/10.
 * 
 * @author virjar
 * @since 0.0.1 加法运算
 */
@OpKey(value = "+", priority = 20)
public class AddUnit extends AlgorithmUnit {

    @Override
    public Object calc(Element element) {
        Object leftValue = left.calc(element);
        Object rightValue = right.calc(element);
        if (leftValue == null || rightValue == null) {
            return XpathUtil.toPlainString(leftValue) + XpathUtil.toPlainString(rightValue);
        }
        // 左右都不为空,开始计算
        // step one think as number
        if (leftValue instanceof Number && rightValue instanceof Number) {
            // 都是整数,则执行整数加法
            if (leftValue instanceof Integer && rightValue instanceof Integer) {
                return (Integer) leftValue + (Integer) rightValue;
            }

            // 包含小数,转double执行加法
            if (leftValue instanceof Double || rightValue instanceof Double || leftValue instanceof Float
                    || rightValue instanceof Float) {
                return ((Number) leftValue).doubleValue() + ((Number) rightValue).doubleValue();
            }

            // 包含BigDecimal 转bigDecimal
            if (leftValue instanceof BigDecimal || rightValue instanceof BigDecimal) {
                return XpathUtil.toBigDecimal((Number) leftValue).add(XpathUtil.toBigDecimal((Number) rightValue));
            }

            // 包含长整数,且不包含小数,全部转化为长整数计算
            if (leftValue instanceof Long || rightValue instanceof Long) {
                return ((Number) leftValue).longValue() + ((Number) rightValue).longValue();
            }

            // 兜底,用double执行计算
            return ((Number) leftValue).doubleValue() + ((Number) rightValue).doubleValue();
        }

        // 有一方不是数字,转化为字符串进行链接
        return XpathUtil.toPlainString(leftValue) + XpathUtil.toPlainString(rightValue);
    }

    @Override
    public Class judeResultType() {
        return null;
    }
}
