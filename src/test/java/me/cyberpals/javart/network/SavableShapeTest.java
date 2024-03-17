package me.cyberpals.javart.network;

import me.cyberpals.javart.shapes.Shape;
import me.cyberpals.javart.shapes.simple.Rectangle;
import me.cyberpals.javart.vectors.Vector2Int;
import org.junit.Test;

import java.rmi.RemoteException;

public class SavableShapeTest {

    @Test
    public void testSave() throws RemoteException {
        Shape shape = new Rectangle(
                new Vector2Int(0, 0),
                new Vector2Int(10, 10)
        );

        SavableShape savableShape = new SavableShape();

        savableShape.saveShape(shape);

        assert shape == savableShape.saved;
    }

    @Test
    public void testLoad() throws RemoteException {
        Shape shape = new Rectangle(
                new Vector2Int(0, 0),
                new Vector2Int(10, 10)
        );

        SavableShape savableShape = new SavableShape();

        savableShape.saved = shape;

        assert savableShape.loadShape() == shape;
    }
}
