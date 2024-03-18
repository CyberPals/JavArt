package me.cyberpals.javart.vectors;

import me.cyberpals.javart.shapes.operations.Difference;
import me.cyberpals.javart.shapes.operations.Xor;
import me.cyberpals.javart.shapes.simple.Rectangle;
import org.junit.Test;

public class DifferenceTest {
    @Test
    public void testTest(){
        Difference x = new Difference(
                new Rectangle(new Vector2Int(0,0),new Vector2Int(10,10)),
                new Rectangle(new Vector2Int(5,5),new Vector2Int(15,15)));
        Vector2Int a = new Vector2Int(7,7);
        assert !x.test(a);
        Vector2Int b = new Vector2Int(14,14);
        assert !x.test(b);
        Vector2Int c = new Vector2Int(1,1);
        assert x.test(c);
    }
}
