package me.cyberpals.javart.vectors;

public class Vector3 {
    private int x;
    private int y;
    private int z;
    public Vector3(int a, int b, int c){
        this.x= a;
        this.y=b;
        this.z=c;
    }

    public Vector3 add(Vector3 v){
        return new Vector3(this.x + v.getX(),this.y+v.getY(),this.z+v.getZ());
    }

    public Vector3 sub(Vector3 v){
        return new Vector3(this.x - v.getX(), this.y - v.getY(), this.z-v.getZ());
    }

    public Vector3 mult(Vector3 v){
        return new Vector3(this.x * v.getX(), this.y * v.getY(), this.z * v.getZ());
    }

    private int getZ() {
        return this.z;
    }

    private int getY() {
        return this.y;
    }

    private int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setZ(int z) {
        this.z = z;
    }

}
