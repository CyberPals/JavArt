package me.cyberpals.javart.vectors;

import me.cyberpals.javart.shapes.simple.Oval;
import me.cyberpals.javart.shapes.simple.Rhombus;
import org.junit.Test;

public class RhombusTest {
    @Test
    public void testTest(){
        Rhombus a = new Rhombus(new Vector2Int(0,0),new Vector2Int(10,10));
        Vector2Int x = new Vector2Int(11,11); //exterior
        assert !a.test(x);
        Vector2Int y = new Vector2Int(5,5); //interior
        assert a.test(y);
        Vector2Int z = new Vector2Int(0,5); //border
        assert a.test(z);
    }
}
