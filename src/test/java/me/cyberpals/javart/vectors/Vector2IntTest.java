package me.cyberpals.javart.vectors;

import org.junit.Test;

public class Vector2IntTest {
    @Test
    public void testAdd() {
        Vector2Int a = new Vector2Int(1, 2);
        Vector2Int b = new Vector2Int(2, 3);

        Vector2Int result = new Vector2Int(3, 5);

        assert a.add(b).equals(result);
    }

    @Test
    public void testSub() {
        Vector2Int a = new Vector2Int(1, 2);
        Vector2Int b = new Vector2Int(2, 3);

        Vector2Int result = new Vector2Int(-1, -1);

        assert a.sub(b).equals(result);
    }
    @Test
    public void testMult() {
        Vector2Int a = new Vector2Int(1, 2);
        Vector2Int b = new Vector2Int(2, 3);

        Vector2Int result = new Vector2Int(2, 6);

        assert a.mult(b).equals(result);
    }
}
