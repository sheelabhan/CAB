package com.example.debugging.arithmetic;

public class Arithmetic {

    private int first;
    private int second;

    public Arithmetic(int first, int second) {
        this.first = first;
        this.second = second;
    }

    public int getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }
    // For adding two nos

    public int add(){
        return first+second;
    }

    // For multiplying two nos
    public int mult(){
        return first*second;
    }
}

