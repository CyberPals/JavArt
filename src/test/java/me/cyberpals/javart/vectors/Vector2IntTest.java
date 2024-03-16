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
}
