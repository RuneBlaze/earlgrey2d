package xyz.unfound.earlgrey2d;

import clojure.lang.*;
import com.badlogic.gdx.graphics.Color;

public class RColor extends Color implements ILookup, Associative, Cloneable{
    public RColor() {
        super();
    }

    public RColor(float r, float g, float b, float a) {
        super(r,g,b,a);
    }
    @Override
    public Object valAt(Object o) {
        if (o instanceof Keyword) {
            Keyword k = (Keyword) o;
            String n = k.getName();
            switch (n) {
                case "r":
                    return r;
                case "g":
                    return g;
                case "b":
                    return b;
                case "a":
                    return a;
            }
        }
        return null;
    }

    @Override
    public Object valAt(Object o, Object o1) {
        Object v = valAt(o);
        return v == null ? o1 : v;
    }

    @Override
    public boolean containsKey(Object o) {
        return false;
    }

    @Override
    public IMapEntry entryAt(Object o) {
        return null;
    }

    @Override
    public Associative assoc(Object o, Object o1) {
        RColor c = clone();
        if (o instanceof Keyword) {
            Keyword k = (Keyword) o;
            String n = k.getName();
            float d = ((Double) o1).floatValue();
            switch (n) {
                case "r":
                    c.r = d;
                    return c;
                case "g":
                    c.g = d;
                    break;
                case "b":
                    c.b = d;
                    break;
                case "a":
                    c.a = d;
                    break;
                default:
                    return null;
            }
            return c;
        }
        return null;
    }

    @Override
    public RColor clone() {
        RColor rColor = new RColor();
        rColor.r = r;
        rColor.g = g;
        rColor.b = b;
        rColor.a = a;
        return rColor;
    }

    @Override
    public int count() {
        return 0;
    }

    @Override
    public IPersistentCollection cons(Object o) {
        return null;
    }

    @Override
    public IPersistentCollection empty() {
        return null;
    }

    @Override
    public boolean equiv(Object o) {
        return this.equals(o);
    }

    @Override
    public ISeq seq() {
        return null;
    }
}
