package me.cyberpals.javart.vectors;

public class Vector3Int extends Vector3<Integer> {
    public Vector3Int(int a, int b, int c) {
        super(a, b,c);
    }
    public Vector3Int add(Vector3Int v){
        return new Vector3Int(this.getX() + v.getX(),this.getY()+v.getY(),this.getZ()+v.getZ());
    }

    public Vector3Int sub(Vector3Int v){
        return new Vector3Int(this.getX() - v.getX(), this.getY() - v.getY(), this.getZ()-v.getZ());
    }

    public Vector3Int mult(Vector3Int v){
        return new Vector3Int(this.getX() * v.getX(), this.getY() * v.getY(), this.getZ() * v.getZ());
    }
}
