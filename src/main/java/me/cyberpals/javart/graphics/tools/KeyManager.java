package me.cyberpals.javart.graphics.tools;

public class KeyManager {
    private boolean pressed;
    private boolean used;

    private int keyCode;

    public KeyManager(int keyCode) {
        this.keyCode = keyCode;

        this.pressed = false;
        this.used = false;
    }

    public void keyPressed(int keyCode) {
        if (keyCode == this.keyCode) {
            this.pressed = true;
        }
    }

    public void keyReleased(int keyCode) {
        if (keyCode == this.keyCode) {
            this.pressed = false;
            this.used = false;
        }
    }

    public boolean getPressed() {
        if (this.pressed && !this.used) {
            this.used = true;
            return true;
        }
        return false;
    }

    public boolean silentGetPressed() {
        return this.pressed;
    }
}
