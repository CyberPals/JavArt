package me.cyberpals.javart.vectors;

import me.cyberpals.javart.shapes.operations.Intersection;
import me.cyberpals.javart.shapes.simple.Rectangle;
import org.junit.Test;

public class IntersectionTest {
    @Test
    public void testTest(){
        Intersection x = new Intersection(
                new Rectangle(new Vector2Int(0,0),new Vector2Int(10,10)),
                new Rectangle(new Vector2Int(5,5),new Vector2Int(15,15)));
        Vector2Int a = new Vector2Int(7,7);
        assert x.test(a);
        Vector2Int b = new Vector2Int(14,14);
        assert !x.test(b);
        Vector2Int c = new Vector2Int(14,1);
        assert !x.test(c);
    }
}
