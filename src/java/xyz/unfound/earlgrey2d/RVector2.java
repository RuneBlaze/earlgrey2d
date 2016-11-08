package xyz.unfound.earlgrey2d;

import clojure.lang.*;
import com.badlogic.gdx.math.Vector2;

public class RVector2 extends Vector2 implements ILookup, Associative{
    public RVector2() {
    }

    public RVector2(float x, float y) {
        super(x, y);
    }

    public RVector2(Vector2 v) {
        super(v);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Vector2) {
            Vector2 v = (Vector2) o;
            return this.x == v.x && this.y == v.y;
        }
        return false;
    }

    @Override
    public Object valAt(Object o) {
        String k = CljUtils.extractStringFromKeyword(o);
        switch (k) {
            case "x":
                return x;
            case "y":
                return y;
            default:
                return null;
        }
    }

    @Override
    public RVector2 clone() {
        return new RVector2(x,y);
    }

    @Override
    public Object valAt(Object o, Object o1) {
        Object r = valAt(o);
        return r != null ? r : o1;
    }

    @Override
    public boolean containsKey(Object o) {
        String k = CljUtils.extractStringFromKeyword(o);
        switch (k) {
            case "x":
                return true;
            case "y":
                return true;
            default:
                return false;
        }
    }

    @Override
    public IMapEntry entryAt(Object o) {
       return null;
    }

    @Override
    public Associative assoc(Object o, Object o1) {
        String k = CljUtils.extractStringFromKeyword(o);
        switch (k) {
            case "x":
                RVector2 v = clone();
                v.x = ((Double) o1).floatValue();
                return v;
            case "y":
                RVector2 v2 = clone();
                v2.y = ((Double) o1).floatValue();
                return v2;
            default:
                return clone();
        }
    }

    @Override
    public int count() {
        return 2;
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
        return equals(o);
    }

    @Override
    public ISeq seq() {
        return null;
    }
}
