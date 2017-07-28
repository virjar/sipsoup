package com.virjar.sipsoup.model;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.virjar.sipsoup.parse.XpathParser;

/**
 * Created by virjar on 17/7/29.
 */
public class SipNodes extends LinkedList<SIPNode> {
    public SipNodes() {
    }

    public SipNodes(Collection<? extends SIPNode> c) {
        super(c);
    }

    public SipNodes(SIPNode sipNode) {
        add(sipNode);
    }

    public SipNodes evaluate(String xpathQuery) {
        return XpathParser.compileNoError(xpathQuery).evaluate(this);
    }

    public List<String> evaluateToString(String xpathQuery) {
        return XpathParser.compileNoError(xpathQuery).evaluateToString(this);
    }

    public List<Element> evaluateToElement(String xpathQuery) {
        return XpathParser.compileNoError(xpathQuery).evaluateToElement(this);
    }

    public Elements evaluateToElements(String xpathQuery) {
        return new Elements(evaluateToElement(xpathQuery));
    }

    public String evaluateToSingleStr(String xpathQuery) {
        List<String> strings = evaluateToString(xpathQuery);
        if (strings.size() == 0) {
            return null;
        }
        return strings.get(0);
    }
}
