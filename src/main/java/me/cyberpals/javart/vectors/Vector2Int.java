package me.cyberpals.javart.vectors;

public class Vector2Int extends Vector2<Integer>  {
    public Vector2Int add(Vector2<Integer> v) {
        return new Vector2Int(this.getX() + v.getX(), this.getY() + v.getY());
    }

    public Vector2Int sub(Vector2<Integer> v) {
        return new Vector2Int(this.getX()-v.getX(),this.getY()-v.getY());
    }

    public Vector2Int mult(Vector2<Integer> v) {
        return new Vector2Int(this.getX()*v.getX(),this.getY()*v.getY());
    }

    public Vector2Int(int a, int b) {
        super(a, b);
    }
}
