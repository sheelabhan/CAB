package com.firstassignment;

public class Reverse {
    public int reverse(int n){
        int r, rev=0;
        while(n>0){
            r=n%10;
            rev=rev *10+r;
            n=n/10;

        }
        return rev;
    }
}
