(ns earlgrey2d.gradients
  (:require [clojure.spec :as s]
            [earlgrey2d.meta :refer [in-between]]))

(defn amplify [f x] (comp (partial * x) f))
(defn stretch [f x]
  (fn [a] (f (/ a x))))
(defn vert-shift [f x] (comp (partial + x) f))

(defn zero-to-one-ceil [t]
  (if (< t 1.0)
    t 1.0))

(s/fdef zero-to-one-ceil
  :args (s/and (s/cat :t number?))
  :ret number?
  :fn (s/and #(<= 0 (:ret %) 1)))

(defn mkline [a k]
  (fn [x] (+ a (* x k))))

(defn linear [y1 y2 dx]
  (mkline y1 (/ (- y2 y1) dx)))

(s/fdef linear
  :args (s/and (s/cat :y1 number? :y2 number? :dx number?))
  :ret fn?)

(defn trend-until-ceiling [min max duration t]
  ( (-> zero-to-one-ceil
        (amplify (- max min))
        (vert-shift min)
        (stretch duration)) t))

(s/fdef trend-until-ceiling
  :args (s/and (s/cat :min number? :max number? :duration number? :t number?)
               #(<= (:min %) (:max %)))
  :ret number?
  :fn (s/and #(<= (-> % :args :min) (:ret %) (-> % :args :max))))

(defn zero-to-one-bounce [t]
  (let [t' (mod t 2)]
    (if (< t' 1)
      t'
      (let [t'' (- t' 1)]
        (- 1 t'')))))

(s/fdef zero-to-one-bounce
  :args (s/and (s/cat :t number?))
  :ret number?
  :fn (s/and #(<= 0 (:ret %) 1)))

(defn bounce-between [r1 r2 duration t]
  ((-> zero-to-one-bounce
       (amplify (- r2 r1))
       (vert-shift r1)
       (stretch duration))
    t))

(s/fdef bounce-between
  :args (s/and (s/cat :r1 number? :r2 number? :duration number? :t number?))
  :ret number?
  :fn #(in-between (-> % :args :min) (:ret %) (-> % :args :max)))

(defn binary-switch [lhs rhs duration t]
  (let [n (quot duration t)]
    (if (even? n)
      rhs lhs)))