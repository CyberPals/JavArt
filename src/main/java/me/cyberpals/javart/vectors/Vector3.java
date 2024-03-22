package me.cyberpals.javart.vectors;

import java.io.Serializable;

public class Vector3<T> implements Serializable {
    private T x;
    private T y;
    private T z;

    public Vector3(T a, T b, T c) {
        this.x = a;
        this.y = b;
        this.z = c;
    }

    public T getZ() {
        return this.z;
    }

    public void setZ(T z) {
        this.z = z;
    }

    public T getY() {
        return this.y;
    }

    public void setY(T y) {
        this.y = y;
    }

    public T getX() {
        return this.x;
    }

    public void setX(T x) {
        this.x = x;
    }

    public String toString() {
        return this.getX() + "," + this.getY() + "," + this.getZ();
    }
    public boolean equals(Vector3<T> other) {
        return (this.x == other.getX()) && (this.y == other.getY()) && (this.z == other.getZ()) ;}
}
