package me.cyberpals.javart.vectors;

import org.junit.Test;

public class Vector3IntTest {
    @Test
    public void testAdd() {
        Vector3Int a = new Vector3Int(1, 2,1);
        Vector3Int b = new Vector3Int(2, 3,1);

        Vector3Int result = new Vector3Int(3, 5,2);

        assert a.add(b).equals(result);
    }

    @Test
    public void testSub() {
        Vector3Int a = new Vector3Int(1, 2,1);
        Vector3Int b = new Vector3Int(2, 3,1);

        Vector3Int result = new Vector3Int(-1, -1,0);

        assert a.sub(b).equals(result);
    }
    @Test
    public void testMult() {
        Vector3Int a = new Vector3Int(1, 2,1);
        Vector3Int b = new Vector3Int(2, 3,1);

        Vector3Int result = new Vector3Int(2, 6,1);

        assert a.mult(b).equals(result);
    }
}
