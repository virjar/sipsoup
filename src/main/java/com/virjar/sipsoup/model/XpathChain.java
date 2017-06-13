package com.virjar.sipsoup.model;

import java.util.LinkedList;

import com.google.common.collect.Lists;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by virjar on 17/6/9.
 */
public class XpathChain {
    @Getter
    @Setter
    /**
     * xpath最终的数据,可能是节点集或者字符集
     */
    private SIPNode.NodeType finalType;

    @Getter
    private LinkedList<XpathNode> xpathNodeList = Lists.newLinkedList();
}
