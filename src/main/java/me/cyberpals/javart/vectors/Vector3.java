package me.cyberpals.javart.vectors;

public class Vector3<T> {
    private T x;
    private T y;
    private T z;
    public Vector3(T a, T b, T c){
        this.x= a;
        this.y=b;
        this.z=c;
    }
    public T getZ() {
        return this.z;
    }

    public T getY() {
        return this.y;
    }

    public T getX() {
        return this.x;
    }

    public void setX(T x) {
        this.x = x;
    }

    public void setY(T y) {
        this.y = y;
    }

    public void setZ(T z) {
        this.z = z;
    }

}