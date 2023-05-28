package com.observer.entity;

import com.observer.action.ObserverAction;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Entity
public class Observer implements ObserverAction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long observerId;

    public String name;

    @Override
    public void update(String msg) {
        System.out.println("New Alert: " + msg);
    }
}
