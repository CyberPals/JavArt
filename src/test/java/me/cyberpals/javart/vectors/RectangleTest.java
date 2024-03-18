package me.cyberpals.javart.vectors;

import me.cyberpals.javart.shapes.simple.Oval;
import me.cyberpals.javart.shapes.simple.Rectangle;
import org.junit.Test;

public class RectangleTest {
    @Test
    public void testTest(){
        Rectangle a = new Rectangle(new Vector2Int(0,0),new Vector2Int(10,10));
        Vector2Int x = new Vector2Int(11,11); //exterior
        assert !a.test(x);
        Vector2Int y = new Vector2Int(5,5); //interior
        assert a.test(y);
        Vector2Int z = new Vector2Int(0,1); //border
        assert a.test(z);
    }
}
