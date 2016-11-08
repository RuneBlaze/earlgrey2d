(ns earlgrey2d.color-test
  (:require [clojure.test :refer :all]
            [earlgrey2d.color :refer :all]))

(def cl (color 0.3 0.4 0.5 0.6))

(deftest color-getter
  (testing "Clojure getters with colors"
    (is (== (:r cl) (.r cl)))
    (is (== (:g cl) (.g cl)))
    (is (== (:b cl) (.b cl)))
    (is (== (:a cl) (.a cl)))))

(deftest color-assoc
  (testing "Clojure assoc with Colors"
    (let [cl' (assoc cl :r 0.0)]
      (is (== (:r cl') 0)))))