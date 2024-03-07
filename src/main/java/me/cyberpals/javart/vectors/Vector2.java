package me.cyberpals.javart.vectors;

public class Vector2 {
    private int x;
    private int y;
    public Vector2(int a,int b){
        this.x= a;
        this.y= b;
    }
    public Vector2 add(Vector2 v){
        return new Vector2(this.x + v.getX(), this.y + v.getY());
    }

    public int getY() {
        return this.y;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
