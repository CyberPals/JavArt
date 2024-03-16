package me.cyberpals.javart.vectors;

public class Vector2Float extends Vector2<Float>  {
    public Vector2Float add(Vector2<Float> v) {
        return new Vector2Float(this.getX() + v.getX(), this.getY() + v.getY());
    }

    public Vector2Float sub(Vector2<Float> v) {
        return new Vector2Float(this.getX()-v.getX(),this.getY()-v.getY());
    }

    public Vector2Float mult(Vector2<Float> v) {
        return new Vector2Float(this.getX()*v.getX(),this.getY()*v.getY());
    }

    public Vector2Float(float a, float b) {
        super(a, b);
    }
}
