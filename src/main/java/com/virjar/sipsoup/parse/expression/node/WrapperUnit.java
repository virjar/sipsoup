package com.virjar.sipsoup.parse.expression.node;

import com.virjar.sipsoup.parse.expression.OperatorEnv;

/**
 * Created by virjar on 17/6/10.
 */
public abstract class WrapperUnit extends AlgorithmUnit {
    private AlgorithmUnit delegate = null;

    protected abstract String targetName();

    protected AlgorithmUnit wrap() {
        if (delegate == null) {
            delegate = OperatorEnv.createByName(targetName());
            delegate.setLeft(left);
            delegate.setRight(right);
        }
        return delegate;
    }
}
