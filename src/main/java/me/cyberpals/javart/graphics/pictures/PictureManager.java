package me.cyberpals.javart.graphics.pictures;

import me.cyberpals.javart.graphics.pictures.types.ExtendablePicture;
import me.cyberpals.javart.graphics.pictures.types.Picture;
import me.cyberpals.javart.graphics.pictures.types.PictureBox;
import me.cyberpals.javart.graphics.pictures.types.SimplePicture;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class PictureManager {

    //attributes for the atlas
    Map<String, Picture> atlas;
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
        System.out.println(getClass().getResource("/"));
        InputStream is = getClass().getResourceAsStream(path);
        assert is != null;
        atlasImage = ImageIO.read(is);
    }

    public void addPicture(String name, int x, int y) {
        atlas.put(name, new SimplePicture(atlasImage.getSubimage(x * width, y * height, width, height)));
    }

    public void addExtendablePicture(String name, int x, int y, int top, int right, int bottom, int left) {
        BufferedImage picture = atlasImage.getSubimage(x * width, y * height, width, height);
        atlas.put(name, new ExtendablePicture(picture, new PictureBox(top, left, right, bottom)));
    }

    public Picture getPicture(String name) {
        return atlas.get(name);
    }
}
