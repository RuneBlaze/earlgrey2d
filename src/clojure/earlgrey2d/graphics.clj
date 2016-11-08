(ns earlgrey2d.graphics
  (:import [com.badlogic.gdx.graphics.g2d TextureRegion SpriteBatch BitmapFont Batch GlyphLayout]
           [com.badlogic.gdx.graphics Texture GL20 OrthographicCamera Color Camera]
           [xyz.unfound.earlgrey2d RTextureRegion ROrthographicCamera]
           (com.badlogic.gdx.math Vector2)
           (com.badlogic.gdx.graphics.glutils ShapeRenderer ShapeRenderer$ShapeType))
  (:use [earlgrey2d.texture]
        [earlgrey2d.math]
        [earlgrey2d.color])
  (:require [defun :refer [defun]]
            [earlgrey2d.core :refer :all]
            [earlgrey2d.utils :refer [->degrees]]
            [earlgrey2d.filehandle :refer :all]
            [earlgrey2d.meta :refer [implicit]]))

(defn orthographic-camera [b w h]
  (let [c (ROrthographicCamera.)]
    (doto c
      (.setToOrtho b w h))))

(def ^:dynamic *auto-update-camera-before-combine* false)

(defn combined [^Camera c]
  (if *auto-update-camera-before-combine*
    (.update c))
  (.combined c))

(defn zoom! [^Camera c z]
  (set! (.-zoom c) z))

(defn update-cam! [^Camera c] (.update c))

(def ortho-camera orthographic-camera)
(def ortho-cam orthographic-camera)

(defn spritebatch [] (SpriteBatch.))

(defn shape-renderer [] (ShapeRenderer.))

(defn projection-matrix [^SpriteBatch sb ^Camera c]
  (.setProjectionMatrix sb (combined c)))

(defn camera-pos![^OrthographicCamera c x y]
  (.set (.position c) x y 0)
  c)

(defn shape-proj! [^ShapeRenderer sr coll]
  (doseq [s coll]
    (do
      (let [ori-color (.getColor sr)]
        (if (:color s)
          (.setColor sr (implicit (:color s) Color ->color)))
        (case (:filled? s)
          true (.begin sr ShapeRenderer$ShapeType/Line)
          false (.begin sr ShapeRenderer$ShapeType/Filled)
          nil (.begin sr ShapeRenderer$ShapeType/Line))
        (case (:type s)
          :line (let [^Vector2 p1 (implicit (:p1 s) Vector2 ->vector2)
                      ^Vector2 p2 (implicit (:p2 s) Vector2 ->vector2)
                      x (.x p1)
                      y (.y p1)
                      x2 (.x p2)
                      y2 (.y p2)]
                  (.line sr x y x2 y2))
          :rect (let [{:keys [x y w h]} s]
                  (.rect sr x y w h))
          :circle (let [{:keys [x y r]} s]
                    (.circle sr x y r)))
        (.end sr)
        (.setColor sr ori-color)))))

(defmacro begin-batch [batch & body]
  `(do
     (.begin ^SpriteBatch ~batch)
     ~(cons 'do body)
     (.end ^SpriteBatch ~batch)))

(defn draw!
  "wrapper for .draw for SpriteBatch"
  ([^SpriteBatch batch ^TextureRegion t ^double x ^double y]
   (.draw batch t x y))
  ([^SpriteBatch batch ^TextureRegion t x y w h]
   (.draw batch t ^double x ^double y ^double w ^double h))
  ([^SpriteBatch batch ^TextureRegion t x y ox oy w h sx sy rot]
   (.draw batch t ^double x ^double y ^double ox ^double oy
          ^double w ^double h ^double sx ^double sy ^double rot))
  ([^SpriteBatch batch ^TextureRegion t x y ox oy w h sx sy rot cw]
   (.draw batch t ^double x ^double y ^double ox ^double oy
          ^double w ^double h ^double sx ^double sy ^double rot ^Boolean cw)))

(defun ^GlyphLayout ->GlyphLayout
  ([^BitmapFont f ^String str] (GlyphLayout. f str)))

(defn font-draw!
  "wrapper for BitmapFont#draw"
  ([^BitmapFont f ^Batch b str x y] (.draw f b ^String str ^double x ^double y))
  ([^BitmapFont f b str x y w hal wrap] (.draw f b str x y w hal wrap))
  ([^BitmapFont f b str x y start end tw hal wrap] (.draw f b str x y start end tw hal wrap)))

; TODO: font-drawr! should support more advanced operations
(defun font-drawr!
  ([f b str x y] (font-draw! f b str x y))
  ([f b str [x y]] (font-draw! f b str x y))
  ([f b str :at x y] (font-draw! f b str x y))
  ([f b str :at [x y]] (font-draw! f b str x y))
  ([f b str :at ^Vector2 v] (font-draw! f b str (.x v) (.y v))))

(defn- draw-sweet! [^SpriteBatch batch ^TextureRegion tex options]
  (let [
        w (or (:w options) (.getRegionWidth tex))
        h (or (:h options) (.getRegionHeight tex))
        ; drawing origins
        dox (or (:dox options) (/ w 2))
        doy (or (:doy options) (/ h 2))
        x (if (:center options) (- (:x options) dox) (:x options))
        y (if (:center options) (- (:y options) doy) (:y options))
        sx (or (:sx options) 1.0) sy (or (:sy options) 1.0)
        ;rot (or (->degrees (:rot options)) 0.0)
        ox (or (:ox options) 0.0) oy (or (:oy options) 0.0)
        cw (or (:cw options) true)
        ori-color (.getColor batch)
        color (let [c (:color options)] (if (number? c) #(assoc % :a c) c))]
    (do
      (when color
        (let [sweetend-color (->color ori-color)
              color' (if (fn? color) (apply color sweetend-color) (implicit color Color ->color))]
          (.setColor batch ^Color color')))
      (draw! batch tex x y ox oy w h sx sy 0 cw)
      (when color (.setColor batch ori-color)))))

(defn bitmap-font
  ([] (BitmapFont.))
  ([fh] (BitmapFont. (implicit-file-handle fh))))

(def drawr! draw-sweet!)

(defn set-color! [^SpriteBatch batch c]
  (let [^Color c' (implicit c Color ->color)]
    (.setColor batch c')))