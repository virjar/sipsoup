package com.virjar.sipsoup.exception;

/*
 * Copyright 2014 Wang Haomiao<et.tw@163.com> Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and limitations under the
 * License.
 */
/**
 * @author github.com/zhegexiaohuozi [seimimaster@gmail.com]
 * @since 14-3-19
 */
public class XpathSyntaxErrorException extends Exception {
    private  int errorPos;


    public XpathSyntaxErrorException(int errorPos,Throwable cause) {
        super(cause);
        this.errorPos = errorPos;
    }

    public XpathSyntaxErrorException(int errorPos,String msg) {
        super(msg);
        this.errorPos = errorPos;
    }

    public XpathSyntaxErrorException(int errorPos, String message, Throwable cause) {
        super(message, cause);
        this.errorPos = errorPos;
    }

    public XpathSyntaxErrorException(int errorPos, String message, Throwable cause, boolean enableSuppression,
                                     boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorPos = errorPos;
    }
}
