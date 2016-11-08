(ns earlgrey2d.vec2-test
  (:require [clojure.test :refer :all]
            [earlgrey2d.math :refer :all]
            [clojure.test.check :as tc]
            [clojure.test.check.clojure-test :refer [defspec]]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop])
  (:import (com.badlogic.gdx.math Vector2)))

(deftest vec2-constructor
  (testing "All forms of ctor should work"
    (let [rhs (Vector2. 4 5)]
      (is (= (->vector2 4 5) rhs))
      (is (= (->vector2 [4 5]) rhs))
      (is (= (->vector2 rhs) rhs))
      (is (= (->vector2 {:y 5 :x 4}) rhs)))))

(deftest vec2-getter
  (let [v (vec2 4 5)]
    (= (:x v) 4)
    (= (:y v) 5)))

(deftest vec2-setter
  (let [v (vec2 4.0 5.0)]
    (= (assoc v :x 6.0 :y 7.0) (vec2 6.0 7.0))
    (= (update v :x inc) (vec2 5.0 5.0))))

(deftest vec2-operations
  (testing "Basic operations should work"
    (is (= (vec2+ [3 4] [5 6]) (vec2 8 10)))))

(def vec2-normal-form
  (gen/fmap (fn [[x y]] (Vector2. x y)) (gen/tuple gen/int gen/int)))

(def vec2-map-form
  (gen/fmap (fn [[x y]] {:x x :y y}) (gen/tuple gen/int gen/int)))

(comment def vec2-implicit-form
  (gen/one-of [(gen/tuple gen/int gen/int)
               vec2-normal-form
               vec2-map-form]))

(defspec implicit-form-same-as-java
         30
         (prop/for-all [x1 gen/int
                        y1 gen/int
                        x2 gen/int
                        y2 gen/int]
                       (let [v1 [x1 y1]
                             v2 [x2 y2]
                             m1 {:x x1 :y y1}
                             m2 {:x x2 :y y2}
                             ^Vector2 v1' (Vector2. x1 y1)
                             ^Vector2 v2' (Vector2. x2 y2)]
                         (and
                           (= (vec2+ v1 v2)
                              (vec2+ m1 m2)
                              (.add (vec2-clone v1') v2'))
                           (= (vec2- v1 m2)
                              (vec2- m1 v2)
                              (.sub (vec2-clone v1') v2'))
                           (= (vec2-crs v1 v2)
                              (vec2-crs m1 m2)
                              (.crs (vec2-clone v1') v2'))
                           (= (vec2-dot v1 v2)
                              (vec2-dot m1 m2)
                              (.dot (vec2-clone v1') v2'))))))