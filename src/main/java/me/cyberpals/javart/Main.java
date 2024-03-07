package me.cyberpals.javart;

import me.cyberpals.javart.vectors.Vector2Int;

public class Main {
    public static void main(String[] args) {
        Vector2Int v1 = new Vector2Int(2,3);
        Vector2Int v2 = new Vector2Int(3,2);
        Vector2Int v3 = v1.add(v2);
        System.out.println(v3);
    }
}