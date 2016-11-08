(ns earlgrey2d.color
  (:import (xyz.unfound.earlgrey2d RColor)
           (com.badlogic.gdx.graphics Color))
  (:require [defun :refer [defun]]))

(defun ^RColor ->color
       ([{:r r :g g :b b :a a}] (RColor. r g b a))
       ([{:r r :g g :b b}] (RColor. r g b 1))
       ([[r g b a]] (RColor. r g b a))
       ([[r g b]] (RColor. r g b 1))
       ([^Color c :guard (partial instance? Color)] (RColor. (.r c) (.g c) (.b c) (.a c)))
       ([r g b a] (->color [r g b a]))
       ([c] c))

(def color ->color)