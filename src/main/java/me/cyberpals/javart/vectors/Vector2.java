package me.cyberpals.javart.vectors;

public class Vector2<T> {
    private T x;
    private T y;
    public Vector2(T a,T b){
        this.x= a;
        this.y= b;
    }

    public T getY() {
        return this.y;
    }

    public T getX() {return this.x;}

    public void setX(T x) {
        this.x = x;
    }
    public void setY(T y) {
        this.y = y;
    }
}
