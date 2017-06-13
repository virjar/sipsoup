package com.virjar.sipsoup.model;

/*
 * Copyright 2014 Wang Haomiao<et.tw@163.com> Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and limitations under the
 * License.
 */

import java.util.List;

import com.virjar.sipsoup.function.axis.AxisFunction;
import com.virjar.sipsoup.function.select.SelectFunction;

import lombok.Getter;
import lombok.Setter;

/**
 * xpath语法链的一个基本节点
 * 
 * @author github.com/zhegexiaohuozi [seimimaster@gmail.com]
 */
public class XpathNode {
    public  enum ScopeEm {
        INCHILREN("/"), // 默认只在子代中筛选,有轴时由轴定义筛选域
        RECURSIVE("//"), // 向下递归查找
        CUR("./"), // 当前节点下
        CURREC(".//"); // 当前节点向下递归
        private String val;

        ScopeEm(String type) {
            this.val = type;
        }

        public String val() {
            return this.val;
        }
    }

    /**
     * 查找方向,
     */
    @Getter
    @Setter
    private ScopeEm scopeEm;
    /**
     * 轴
     */
    @Getter
    @Setter
    private AxisFunction axis;

    @Getter
    @Setter
    private List<String> axisParams;

    /**
     * 谓语
     */
    @Getter
    @Setter
    private Predicate predicate;

    @Setter
    @Getter
    private SelectFunction selectFunction;

    @Getter
    @Setter
    private List<String> selectParams;
}
