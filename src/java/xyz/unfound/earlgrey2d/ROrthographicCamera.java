package xyz.unfound.earlgrey2d;


import clojure.lang.*;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

public class ROrthographicCamera extends OrthographicCamera implements ILookup, Associative{
    float rotation = 0f;

    public ROrthographicCamera() {
    }

    public ROrthographicCamera(float viewportWidth, float viewportHeight) {
        super(viewportWidth, viewportHeight);
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
        String k = CljUtils.extractStringFromKeyword(o);
        switch (k) {
            case "width":
                this.viewportWidth = ((Double) o1).floatValue();
                return this;
            case "height":
                this.viewportHeight = ((Double) o1).floatValue();
                return this;
            case "zoom":
                this.zoom = ((Double) o1).floatValue();
                return this;
            case "rotation":
                float trotat = ((Double) o1).floatValue();
                float diff = trotat - rotation;
                this.rotate(diff);
                this.rotation = trotat;
                return this;
            case "position":
                float x, y;
                if (o instanceof Vector2) {
                    x = ((Vector2) o).x;
                    y = ((Vector2) o).y;
                } else {
                    IPersistentVector v = (IPersistentVector) o1;
                    x = (float) v.valAt(0);
                    y = (float) v.valAt(1);
                }
                this.position.x = x;
                this.position.y = y;
                return this;
            default:
                return this;
        }
    }

    @Override
    public Object valAt(Object o) {
        String k = CljUtils.extractStringFromKeyword(o);
        switch (k) {
            case "width":
                return viewportWidth;
            case "height":
                return viewportHeight;
            case "zoom":
                return zoom;
            case "position":
                return new RVector2(position.x, position.y);
            case "rotation":
                return rotation;
            default:
                return null;
        }
    }

    @Override
    public Object valAt(Object o, Object o1) {
        Object r = valAt(o);
        return r != null ? r : o1;
    }

    @Override
    public int count() {
        return -1;
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
        return o.equals(this);
    }

    @Override
    public ISeq seq() {
        return null;
    }
}
