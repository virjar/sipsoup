package com.virjar.sipsoup.model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import com.virjar.sipsoup.parse.XpathParser;


public class SIPNode {
    public enum NodeType {
        NODE, TEXT
    }

    private Element element;
    private boolean isText;
    private String textVal;

    public Element getElement() {
        return element;
    }

    public SIPNode setElement(Element element) {
        this.element = element;
        return this;
    }

    public boolean isText() {
        return isText;
    }

    public SIPNode setText(boolean text) {
        isText = text;
        return this;
    }

    public String getTextVal() {
        return textVal;
    }

    public SIPNode setTextVal(String textVal) {
        this.textVal = textVal;
        return this;
    }

    public static SIPNode e(Element element) {
        SIPNode n = new SIPNode();
        n.setElement(element).setText(false);
        return n;
    }

    public static SIPNode t(String txt) {
        SIPNode n = new SIPNode();
        n.setTextVal(txt).setText(true);
        return n;
    }

    public static SIPNode parse(String html, String baseUrl) {
        return e(Jsoup.parse(html, baseUrl));
    }

    public static SIPNode parse(String html) {
        return parse(html, null);
    }

    public SipNodes select(String xpathQuery) {
        return XpathParser.compileNoError(xpathQuery).evaluate(this);
    }

    @Override
    public String toString() {
        if (isText) {
            return textVal;
        } else {
            return element.toString();
        }
    }
}
