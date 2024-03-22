package me.cyberpals.javart.vectors;

import me.cyberpals.javart.shapes.Shape;
import me.cyberpals.javart.shapes.simple.Oval;
import me.cyberpals.javart.shapes.simple.SimpleShape;
import org.junit.Test;

import java.awt.*;

public class OvalTest {
    @Test
    public void testTest(){
        Oval a = new Oval(new Vector2Int(0,0),new Vector2Int(10,10));
        Vector2Int x = new Vector2Int(11,11); //exterior
        assert !a.test(x);
        Vector2Int y = new Vector2Int(5,5); //interior
        assert a.test(y);
        Vector2Int z = new Vector2Int(0,5); //border
        assert a.test(z);
    }
}
