(ns earlgrey2d.utils
  (:import (xyz.unfound.earlgrey2d GdxUtils))
  [:require [earlgrey2d.filehandle :refer :all]
            [defun :refer :all]
            [clojure.spec :as s]])

(defn screenshot
  ([] (GdxUtils/screenshot)))

(defn screenshot!
  ([fh] (GdxUtils/saveScreenshot (implicit-file-handle fh))))

(defun ->degrees
  ([i :guard number?] i)
  ([(:or :degree :degrees :d) i] i)
  ([(:or :radian :radians :r) r] (Math/toDegrees r))
  ([i k :guard keyword?] (->degrees k i))
  ([[a b]] (->degrees a b)))

(def degrees ->degrees)

(defn clamp [l r v]
  (max l (min r v)))

(s/fdef clamp
  :args (s/and (s/cat :l number? :r number? :v number?))
  :ret number?)

(defn bd+ [amt bd n]
  (min (+ n amt) bd))

(defn bd- [amt bd n]
  (max (- n amt) bd))

(def bd-inc (partial bd+ 1))
(def bd-dec (partial bd- 1))

(defn sign [x]
  (if (neg? x) -1 1))

(defn wordwrap []
  ; TODO: implement wordwrap
  )

(defonce ^:dynamic *force-reload* false)

(defmacro let-do [bindings & body]
  `(let ~bindings ~(cons 'do body)))

(defn notnil [v]
  (if (nil? v)
    (throw (Exception. (str v " is nil.")))
    v))

(defn maybe-map [f v]
  (if v (f v) v))

(defmacro defreloadable [name expr]
  `(let [v# (def ~name)]
     (when (or (.hasRoot v#) *force-reload*) (def ~name ~expr))))