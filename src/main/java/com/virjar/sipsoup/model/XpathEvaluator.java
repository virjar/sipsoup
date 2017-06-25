package com.virjar.sipsoup.model;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.virjar.sipsoup.exception.FinalTypeNotSameException;
import com.virjar.sipsoup.function.axis.AxisFunction;
import com.virjar.sipsoup.function.select.SelectFunction;
import com.virjar.sipsoup.function.select.SelfFunction;
import com.virjar.sipsoup.function.select.TagSelectFunction;
import com.virjar.sipsoup.util.XpathUtil;

/**
 * Created by virjar on 17/6/9.
 */
public abstract class XpathEvaluator {
    public abstract List<SIPNode> evaluate(List<SIPNode> elements);

    public List<SIPNode> evaluate(Elements elements) {
        return evaluate(Lists.transform(elements, new Function<Element, SIPNode>() {
            @Override
            public SIPNode apply(Element input) {
                return SIPNode.e(input);
            }
        }));
    }

    public List<SIPNode> evaluate(Element element) {
        return evaluate(new Elements(element));
    }

    public List<String> evaluateToString(List<SIPNode> SIPNodes) {
        return transformToString(evaluate(SIPNodes));
    }

    public List<String> evaluateToString(Element element) {
        return transformToString(evaluate(element));
    }

    public List<Element> evaluateToElement(List<SIPNode> SIPNodes) {
        return transformToElement(evaluate(SIPNodes));
    }

    public List<Element> evaluateToElement(Element element) {
        return transformToElement(evaluate(element));
    }

    public Elements evaluateToElements(List<SIPNode> nodeLists) {
        return new Elements(evaluateToElement(nodeLists));
    }

    public Elements evaluateToElements(Element element) {
        return new Elements(evaluateToElement(element));
    }

    public String evaluateToSingleStr(Element element) {
        List<String> strings = evaluateToString(element);
        if (strings.size() == 0) {
            return null;
        }
        return strings.get(0);
    }

    public static List<Element> transformToElement(List<SIPNode> SIPNodes) {
        return Lists.newLinkedList(Iterables.transform(Iterables.filter(SIPNodes, new Predicate<SIPNode>() {
            @Override
            public boolean apply(SIPNode input) {
                return input.getElement() != null;
            }
        }), new Function<SIPNode, Element>() {
            @Override
            public Element apply(SIPNode input) {
                return input.getElement();
            }
        }));
    }

    public static List<String> transformToString(List<SIPNode> SIPNodes) {
        return Lists.newLinkedList(Iterables.transform(Iterables.filter(SIPNodes, new Predicate<SIPNode>() {
            @Override
            public boolean apply(SIPNode input) {
                return input.isText();
            }
        }), new Function<SIPNode, String>() {
            @Override
            public String apply(SIPNode input) {
                return input.getTextVal();
            }
        }));
    }

    /**
     * 系统会checktype
     *
     * @return type
     */
    public abstract SIPNode.NodeType judeNodeType() throws FinalTypeNotSameException;

    public XpathEvaluator wrap(XpathEvaluator newRule) {
        throw new UnsupportedOperationException();
    }

    public static class AnanyseStartEvaluator extends XpathEvaluator {

        @Override
        public List<SIPNode> evaluate(List<SIPNode> elements) {
            throw new UnsupportedOperationException();
        }

        @Override
        public SIPNode.NodeType judeNodeType() throws FinalTypeNotSameException {
            throw new UnsupportedOperationException();
        }

        @Override
        public XpathEvaluator wrap(XpathEvaluator newRule) {
            return newRule;
        }
    }

    public static class ChainEvaluator extends XpathEvaluator {
        // @Getter
        // for xsoup
        private LinkedList<XpathNode> xpathNodeList = Lists.newLinkedList();

        public ChainEvaluator(LinkedList<XpathNode> xpathNodeList) {
            this.xpathNodeList = xpathNodeList;
        }

        private List<SIPNode> handleNode(List<SIPNode> input, final XpathNode xpathNode) {

            // 目前只支持对element元素进行抽取,如果中途抽取到了文本,则会断节
            List<Element> elements = Lists.newLinkedList(
                    Sets.newLinkedHashSet(Iterables.transform(Iterables.filter(input, new Predicate<SIPNode>() {
                        @Override
                        public boolean apply(SIPNode input) {
                            return !input.isText();
                        }
                    }), new Function<SIPNode, Element>() {
                        @Override
                        public Element apply(SIPNode input) {
                            return input.getElement();
                        }
                    })));

            List<Element> contextElements;

            // 轴
            AxisFunction axis = xpathNode.getAxis();
            if (axis != null) {
                contextElements = Lists.newLinkedList();
                for (Element element : elements) {
                    Elements call = axis.call(element, xpathNode.getAxisParams());
                    if (call != null) {
                        contextElements.addAll(call);
                    }
                }
            } else {
                contextElements = elements;
            }

            // 调用抽取函数
            List<SIPNode> SIPNodes = xpathNode.getSelectFunction().call(xpathNode.getScopeEm(),
                    new Elements(contextElements), xpathNode.getSelectParams());

            // 谓语过滤
            if (xpathNode.getPredicate() == null) {
                return SIPNodes;
            }

            // 谓语只支持对元素过滤,非元素节点直接被过滤
            return Lists.newLinkedList(Iterables.filter(SIPNodes, new Predicate<SIPNode>() {
                @Override
                public boolean apply(SIPNode input) {
                    return xpathNode.getPredicate().isValid(input.getElement());
                }
            }));
        }

        @Override
        public List<SIPNode> evaluate(List<SIPNode> elements) {
            for (XpathNode xpathNode : xpathNodeList) {// 对xpath语法树上面每个节点进行抽取
                elements = handleNode(elements, xpathNode);
                if (elements.isEmpty()) {// 如果已经为空,终止抽取链
                    return elements;
                }
            }
            return elements;
        }

        @Override
        public SIPNode.NodeType judeNodeType() throws FinalTypeNotSameException {
            // TODO 这里需要优化
            SelectFunction selectFunction = xpathNodeList.getLast().getSelectFunction();
            if (selectFunction instanceof SelfFunction || selectFunction instanceof TagSelectFunction) {
                return SIPNode.NodeType.NODE;
            } else {
                return SIPNode.NodeType.TEXT;
            }
        }
    }

    public static class OrEvaluator extends XpathEvaluator {
        private List<XpathEvaluator> subEvaluators = Lists.newLinkedList();

        public OrEvaluator() {
        }

        @Override
        public List<SIPNode> evaluate(List<SIPNode> elements) {
            Iterator<XpathEvaluator> iterator = subEvaluators.iterator();
            List<SIPNode> evaluate = iterator.next().evaluate(elements);
            while (iterator.hasNext()) {
                evaluate.addAll(iterator.next().evaluate(elements));
            }
            return evaluate;
        }

        @Override
        public SIPNode.NodeType judeNodeType() throws FinalTypeNotSameException {
            XpathUtil.checkSameResultType(subEvaluators);
            return subEvaluators.iterator().next().judeNodeType();
        }

        @Override
        public XpathEvaluator wrap(XpathEvaluator newRule) {
            subEvaluators.add(newRule);
            return this;
        }
    }

    public static class AndEvaluator extends XpathEvaluator {

        private List<XpathEvaluator> subEvaluators = Lists.newLinkedList();

        public AndEvaluator() {
        }

        @Override
        public List<SIPNode> evaluate(List<SIPNode> elements) {
            Iterator<XpathEvaluator> iterator = subEvaluators.iterator();
            List<SIPNode> evaluate = iterator.next().evaluate(elements);
            while (iterator.hasNext()) {
                evaluate.retainAll(iterator.next().evaluate(elements));
            }
            return evaluate;
        }

        @Override
        public SIPNode.NodeType judeNodeType() throws FinalTypeNotSameException {
            XpathUtil.checkSameResultType(subEvaluators);
            return subEvaluators.iterator().next().judeNodeType();
        }

        @Override
        public XpathEvaluator wrap(XpathEvaluator newRule) {
            subEvaluators.add(newRule);
            return this;
        }
    }
}
