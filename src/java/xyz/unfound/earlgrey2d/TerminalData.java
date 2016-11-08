package xyz.unfound.earlgrey2d;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import jdk.nashorn.internal.ir.Terminal;

public class TerminalData {
    class TerminalChar {
        public TerminalChar(char c, Color fg, Color bg) {
            this.c = c;
            this.fg = fg;
            this.bg = bg;
        }

        public char getC() {
            return c;
        }

        public Color getFg() {
            return fg;
        }

        public Color getBg() {
            return bg;
        }

        public void setC(char c) {
            this.c = c;
        }

        public void setFg(Color fg) {
            this.fg = fg;
        }

        public void setBg(Color bg) {
            this.bg = bg;
        }

        char c;
        Color fg;
        Color bg;
    }

    TerminalChar[][] chars;
    int width;
    int height;
    public TerminalData(int width, int height) {
        this.width = width;
        this.height = height;
        this.chars = new TerminalChar[width][height];
    }

    public void putChar(int x, int y, char c) {
        this.chars[x][y] = new TerminalChar(c, null, null);
    }

    public void draw(SpriteBatch sb) {
        for (int x = 0; x < width; x ++ ) {
            for (int y = 0; y < height; y ++) {
                TerminalChar maybeC = chars[x][y];
                if (maybeC != null) {

                }
            }
        }
    }
}