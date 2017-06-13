package com.virjar.sipsoup.parse.expression.operator;

import org.jsoup.nodes.Element;

import com.virjar.sipsoup.parse.expression.node.AlgorithmUnit;

/**
 * Created by virjar on 17/6/10.
 */
@OpKey(value = "&&", priority = 0)
public class AndUnit extends AlgorithmUnit {
    @Override
    public Object calc(Element element) {
        Object leftValue = left.calc(element);
        Object rightValue = right.calc(element);
        if (leftValue == null || rightValue == null) {
            return Boolean.FALSE;
        }
        // 左右都不为空,开始计算
        // step one think as number
        if (leftValue instanceof Boolean && rightValue instanceof Boolean) {
            return (Boolean) leftValue && (Boolean) rightValue;
        }

        return Boolean.FALSE;
    }

    @Override
    public Class judeResultType() {
        return Boolean.class;
    }
}
