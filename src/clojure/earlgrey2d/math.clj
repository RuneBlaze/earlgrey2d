(ns earlgrey2d.math
  (:require [defun :refer :all]
            [clojure.spec :as s]
            [earlgrey2d.meta :refer :all])
  (:use [earlgrey2d.gradients])
  (:import (com.badlogic.gdx.math Vector2)
           (xyz.unfound.earlgrey2d RVector2)))

(defun ^Vector2 ->vector2
  ([x y] (RVector2. x y))
  ([[x y]] (RVector2. x y))
  ([{:x x :y y}] (RVector2. x y))
  ([a] a))

(defn vec2 [x y]
  (->vector2 x y))

(s/fdef vec2
  :args (s/and (s/cat :x number? :y number?))
  :ret (partial instance? Vector2))

(defn ^Vector2 vec2-clone [^Vector2 vec]
  (vec2 (.x vec) (.y vec)))

(defn ^Vector2 vec-op [op lhs rhs]
  (let [^Vector2 lhs' (implicit lhs Vector2 ->vector2)
        ^Vector2 rhs' (implicit rhs Vector2 ->vector2)
        ^Vector2 lhs'' (vec2-clone lhs')]
    (op lhs'' rhs')))

(def vec2+ (partial vec-op #(.add ^Vector2 %1 ^Vector2 %2)))
(def vec2- (partial vec-op #(.sub ^Vector2 %1 ^Vector2 %2)))
(def vec2-crs (partial vec-op #(.crs ^Vector2 %1 ^Vector2 %2)))
(def vec2-dot (partial vec-op #(.dot ^Vector2 %1 ^Vector2 %2)))

(defn distance [p1 p2]
  (let [p3 (vec2- p2 p1)]
    (.len p3)))

(defn angle [p1 p2]
  (let [p3 (vec2- p2 p1)]
    (.angle p3)))