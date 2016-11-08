package xyz.unfound.earlgrey2d;

import clojure.lang.ILookup;
import clojure.lang.Keyword;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class RTextureRegion extends TextureRegion implements ILookup{
    public RTextureRegion(String internalP) {
        super(new Texture(internalP));
    }

    public RTextureRegion(Texture t) {
        super(t);
    }

    public RTextureRegion(Texture t, int w, int h) {
        super(t, w, h);
    }

    public RTextureRegion(Texture t, int x, int y, int w, int h) {
        super(t, x, y, w, h);
    }

    public RTextureRegion(TextureRegion t, int x, int y, int w, int h) {
        super(t, x, y, w, h);
    }

    public RTextureRegion(TextureRegion region) {
        super(region);
    }

    @Override
    public Object valAt(Object o) {
        if (o instanceof Keyword) {
            Keyword k = (Keyword) o;
            String n = k.getName();
            if (n.equals("x")) {
                return getRegionX();
            } else if (n.equals("y")) {
                return getRegionY();
            } else if (n.equals("width")) {
                return getRegionWidth();
            } else if (n.equals("height")) {
                return getRegionHeight();
            }
        }
       return null;
    }

    @Override
    public Object valAt(Object o, Object o1) {
        Object v = valAt(o);
        return v == null ? o1 : v;
    }
}
