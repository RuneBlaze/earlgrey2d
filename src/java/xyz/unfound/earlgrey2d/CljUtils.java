package xyz.unfound.earlgrey2d;

import clojure.lang.Keyword;

public class CljUtils {
    public static String extractStringFromKeyword(Object o) {
        if (o instanceof Keyword) {
            Keyword k = (Keyword) o;
            return k.getName();
        }
        return null;
    }
}
