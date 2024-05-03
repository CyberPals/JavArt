package me.cyberpals.javart.graphics.tools;

import me.cyberpals.javart.vectors.Vector2Int;

public class MovementKey {
    private KeyManager up, down, left, right;

    public MovementKey(int keyUp, int keyDown, int keyLeft, int keyRight) {
        this.up = new KeyManager(keyUp);
        this.down = new KeyManager(keyDown);
        this.left = new KeyManager(keyLeft);
        this.right = new KeyManager(keyRight);
    }

    public void keyPressed(int keyCode) {
        up.keyPressed(keyCode);
        down.keyPressed(keyCode);
        left.keyPressed(keyCode);
        right.keyPressed(keyCode);
    }

    public void keyReleased(int keyCode) {
        up.keyReleased(keyCode);
        down.keyReleased(keyCode);
        left.keyReleased(keyCode);
        right.keyReleased(keyCode);
    }

    public Vector2Int getMovement() {
        int x = 0, y = 0;
        if (up.silentGetPressed()) y--;
        if (down.silentGetPressed()) y++;
        if (left.silentGetPressed()) x--;
        if (right.silentGetPressed()) x++;
        return new Vector2Int(x, y);
    }

}
