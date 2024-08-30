package com.prajwal.joinmyride1.service;

import org.springframework.stereotype.Service;

@Service
public class Increment {

    public int a = 1;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a+1;
    }
}
