package com.virjar.sipsoup.function.filter;

import java.util.List;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;

import com.virjar.sipsoup.parse.expression.SyntaxNode;
import com.virjar.sipsoup.util.XpathUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by virjar on 17/6/23.
 */
@Slf4j
public class SipSoupPredicteJudgeFunction implements FilterFunction {
    @Override
    public Object call(Element element, List<SyntaxNode> params) {
        if (element == null) {
            return false;
        }
        Object ret = params.get(0).calc(element);
        if (ret == null) {
            return false;
        }

        if (ret instanceof Number) {
            int i = ((Number) ret).intValue();
            return XpathUtil.getElIndexInSameTags(element) == i;
        }

        if (ret instanceof Boolean) {
            return (boolean) ret;
        }

        if (ret instanceof CharSequence) {
            String s = ret.toString();
            Boolean booleanValue = BooleanUtils.toBooleanObject(s);
            if (booleanValue != null) {
                return booleanValue;
            }
            return StringUtils.isNotBlank(s);
        }

        log.warn("can not recognize predicate expression calc result:" + ret);
        return false;
    }

    @Override
    public String getName() {
        return "sipSoupPredictJudge";
    }
}
