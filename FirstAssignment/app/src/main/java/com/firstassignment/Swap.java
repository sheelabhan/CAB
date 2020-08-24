package com.firstassignment;

public class Swap {
    private int a;
    private int b;
    public void setA(int a){
        this.a=a;
    }
    public void setB(int b){
        this.b=b;
    }
    public String swapnumber(){
        a=a^b;//a becomes 15(1111)
        b=a^b; //b becomes 10(1010)
        a=a^b; //a becomes 5(0101)
        return "After swap:a =" +a +"b= " +b;
    }
}
