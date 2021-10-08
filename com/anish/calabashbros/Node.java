package com.anish.calabashbros;

public class Node {
    public  Node(int _x,int _y){
        this.x = _x;
        this.y = _y;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    @Override
    public String toString() {
        return "(" + x +" , " + y + " ) "; 
    }
    private int x;
    private int y;
}
