package com.vk.api.navigation;

import android.support.v4.app.Fragment;

import java.util.Stack;

public class BackStack {
    private final Stack<Fragment> backstack;

    public BackStack() {
        backstack = new Stack<>();
    }

    public void add(Fragment fragment) {
        backstack.push(fragment);
    }

    public boolean pop() {
        if(backstack.empty()) {
            return false;
        }
        backstack.pop();
        return true;
    }
}
