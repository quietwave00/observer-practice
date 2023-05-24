package com.observer.main;

import com.observer.impl.ObserverImpl;
import com.observer.impl.SubjectImpl;

public class Main {
    public static void main(String[] args) {
        SubjectImpl subject = new SubjectImpl();
        ObserverImpl observer1 = new ObserverImpl();
        ObserverImpl observer2 = new ObserverImpl();
        ObserverImpl observer3 = new ObserverImpl();

        subject.subscribe(observer1);
        subject.subscribe(observer2);
        subject.subscribe(observer3);

        subject.upload();
    }
}
