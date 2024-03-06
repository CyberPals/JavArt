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
        return new Vector3(this.x + v.getx(),this.y+v.gety(),this.z+v.getz());
    }

    private int getz() {
        return this.z;
    }

    private int gety() {
        return this.y;
    }

    private int getx() {
        return this.x;
    }

}
