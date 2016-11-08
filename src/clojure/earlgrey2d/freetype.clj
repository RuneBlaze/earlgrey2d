(ns earlgrey2d.freetype
  (:require [earlgrey2d.core :refer :all]
            [earlgrey2d.meta :refer [implicit]]
            [camel-snake-kebab.core :refer :all])
  (:import (com.badlogic.gdx.graphics.g2d.freetype FreeTypeFontGenerator$FreeTypeFontParameter FreeTypeFontGenerator)))

(defmacro when-set! [obj prop value]
  `(when ~prop (set! (. ~obj ~(->camelCase prop)) ~value)))

(defn ^FreeTypeFontGenerator$FreeTypeFontParameter ->FreeTypeFontParameter
  [{:keys [size color border-width border-color border-straight
           shadow-offset-x shadow-offset-y shadow-color characters kerning packer flip gen-mip-maps
           min-filter mag-filter]}]
  (let [p (FreeTypeFontGenerator$FreeTypeFontParameter.)]
    (doto p
      (when-set! size size)
      (when-set! color color)
      (when-set! border-width border-width)
      (when-set! border-color border-color)
      (when-set! border-straight border-straight)
      (when-set! shadow-offset-x shadow-offset-x)
      (when-set! shadow-offset-y shadow-offset-y)
      (when-set! shadow-color shadow-color)
      (when-set! characters characters)
      (when-set! kerning kerning)
      (when-set! packer packer)
      (when-set! flip flip)
      (when-set! gen-mip-maps gen-mip-maps)
      (when-set! min-filter min-filter)
      (when-set! mag-filter mag-filter))))

(defn ^FreeTypeFontGenerator generator [fh]
  (FreeTypeFontGenerator. (->FileHandle fh)))

(defn dispose! [^FreeTypeFontGenerator g]
  (.dispose g))

(defn gen [fh param]
  (let [g (generator fh)
        p (implicit param FreeTypeFontGenerator$FreeTypeFontParameter ->FreeTypeFontParameter)
        ft (.generateFont g ^FreeTypeFontGenerator$FreeTypeFontParameter p)]
    (do
      (.dispose g)
      ft)))