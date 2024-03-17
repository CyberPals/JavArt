package me.cyberpals.javart.vectors;

import java.io.Serializable;

public class Vector2<T> implements Serializable {
    private T x;
    private T y;

    public Vector2(T a, T b) {
        this.x = a;
        this.y = b;
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
        return this.getX() + "," + this.getY();
    }

    public boolean equals(Vector2<T> other) {
        return (this.x == other.getX()) && (this.y == other.getY());
    }
}
