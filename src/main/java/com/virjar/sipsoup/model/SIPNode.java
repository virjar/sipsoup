package com.virjar.sipsoup.model;
/*
 * Copyright 2014 Wang Haomiao<et.tw@163.com> Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and limitations under the
 * License.
 */

import org.jsoup.nodes.Element;

/**
 * XPath提取后的
 * 
 * @author github.com/zhegexiaohuozi [seimimaster@gmail.com]
 * @since 2016/5/12.
 */
public class SIPNode {
    public enum NodeType {
        NODE, TEST
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

    @Override
    public String toString() {
        if (isText) {
            return textVal;
        } else {
            return element.toString();
        }
    }
}
