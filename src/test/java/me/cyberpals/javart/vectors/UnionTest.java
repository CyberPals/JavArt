package me.cyberpals.javart.vectors;

import me.cyberpals.javart.shapes.operations.Union;
import me.cyberpals.javart.shapes.operations.Xor;
import me.cyberpals.javart.shapes.simple.Rectangle;
import org.junit.Test;

public class UnionTest {
    @Test
    public void testTest(){
        Union x = new Union(
                new Rectangle(new Vector2Int(0,0),new Vector2Int(10,10)),
                new Rectangle(new Vector2Int(5,5),new Vector2Int(15,15)));
        Vector2Int b = new Vector2Int(14,14);
        assert x.test(b);
    }
}
