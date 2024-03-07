package me.cyberpals.javart.vectors;

public class Vector3Int extends Vector3<Integer> {
    public Vector3Int(int a, int b, int c) {
        super(a, b,c);
    }
    public Vector3<Integer> add(Vector3<Integer> v){
        return new Vector3<Integer>(this.getX() + v.getX(),this.getY()+v.getY(),this.getZ()+v.getZ());
    }

    public Vector3<Integer> sub(Vector3<Integer> v){
        return new Vector3<Integer>(this.getX() - v.getX(), this.getY() - v.getY(), this.getZ()-v.getZ());
    }

    public Vector3<Integer> mult(Vector3<Integer> v){
        return new Vector3<Integer>(this.getX() * v.getX(), this.getY() * v.getY(), this.getZ() * v.getZ());
    }
}
