(ns earlgrey2d.meta)

(defn implicit [d t f]
  "checks if d is of type t, if not calls f to t, returns the result"
  (if (instance? t d)
    d
    (f d)))

(defn implicitly [t f d] (implicit d t f))

(defn fn-or [lhs rhs]
  (fn [& args]
    (or (apply lhs args) (apply rhs args))))

(defn nilmap [fn maybe]
  (if maybe (fn maybe) maybe))

(defmacro defalias [ori & args]
  (cons 'do (map (fn [a] `(def ~a ~ori)) args)))

(defn in-between [l1 l2 v]
  (or
    (<= l1 v l2)
    (<= l2 v l1)))