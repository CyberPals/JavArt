package me.cyberpals.javart.vectors;

public class Vector2 {
    private int x;
    private int y;
    public Vector2(int a,int b){
        this.x= a;
        this.y= b;
    }
    public Vector2 add(Vector2 v){
        return new Vector2(this.x + v.getabs(), this.y + v.getord());
    }

    private int getord() {
        return this.y;
    }

    private int getabs() {
        return this.x;
    }
}
