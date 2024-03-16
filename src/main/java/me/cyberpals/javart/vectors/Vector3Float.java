package me.cyberpals.javart.vectors;

public class Vector3Float extends Vector3<Float>{
    public Vector3Float(float a, float b, float c) {
        super(a, b,c);
    }
    public Vector3Float add(Vector3Float v){
        return new Vector3Float(this.getX() + v.getX(),this.getY()+v.getY(),this.getZ()+v.getZ());
    }

    public Vector3Float sub(Vector3Float v){
        return new Vector3Float(this.getX() - v.getX(), this.getY() - v.getY(), this.getZ()-v.getZ());
    }

    public Vector3Float mult(Vector3Float v){
        return new Vector3Float(this.getX() * v.getX(), this.getY() * v.getY(), this.getZ() * v.getZ());
    }
}
