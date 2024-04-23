package me.cyberpals.javart.serialisation;

import me.cyberpals.javart.shapes.Shape;

import java.io.*;

public class SaveManager {
    public void saveShape(Shape s, String fileName) throws IOException {
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fileName));
        System.out.println(new File(fileName).getAbsolutePath());
        os.writeObject(s);
        os.close();
    }

    public Shape loadShape(String fileName) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
        System.out.println(new File(fileName).getAbsolutePath());
        Shape s = (Shape) ois.readObject();
        return s;
    }
}
