package me.cyberpals.javart.graphics.pictures;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class PictureManager {

    //attributes for the atlas
    Map<String, BufferedImage> atlas;
    BufferedImage atlasImage;

    // mains attributes
    private String path;
    private int width;
    private int height;

    public PictureManager(String path, int width, int height) throws IOException {
        this.path = path;
        this.width = width;
        this.height = height;

        // generate empty atlas
        atlas = new HashMap<>();
        InputStream is = getClass().getResourceAsStream(path);
        assert is != null;
        atlasImage = ImageIO.read(is);
    }

    public void addPicture(String name, int x, int y) {
        atlas.put(name, atlasImage.getSubimage(x * width, y * height, width, height));
    }

    public BufferedImage getPicture(String name) {
        return atlas.get(name);
    }

    public ImageIcon getIcon(String name) {
        return new ImageIcon(getPicture(name));
    }
}
