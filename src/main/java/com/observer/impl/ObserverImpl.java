package com.observer.impl;

import com.observer.action.Observer;

public class ObserverImpl implements Observer {
    @Override
    public void update(String msg) {
        System.out.println("New Alert: " + msg);
    }
}
