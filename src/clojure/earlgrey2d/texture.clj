(ns earlgrey2d.texture
  (:import (com.badlogic.gdx.graphics Texture)
           (xyz.unfound.earlgrey2d RTextureRegion)
           (com.badlogic.gdx.files FileHandle)
           (com.badlogic.gdx.graphics.g2d TextureRegion))
  (:require [earlgrey2d.meta :refer [implicit]]
            [earlgrey2d.filehandle :refer [->file-handle]]
            [defun :refer [defun]]))

(defun texture
       ([fh] (RTextureRegion. (Texture. ^FileHandle (implicit fh FileHandle ->file-handle))))
       ([^Texture t x y]
         (RTextureRegion. t x y))
       ([^TextureRegion t x y] (RTextureRegion. t x y))
       ([^TextureRegion t x y w h] (RTextureRegion. t ^Integer x ^Integer y ^Integer w ^Integer h)))

(defn dup-texture [^TextureRegion t] (RTextureRegion. t))

(defn flip-texture [^TextureRegion tr]
  (let [t (dup-texture tr)]
    (do
      (.flip t true false)
      t)))

(defn split-texture [^TextureRegion tr w h]
  (mapv vec (.split tr w h)))

(defun tscissors
  ([^TextureRegion t x y w h] (texture t x y w h))
  ([^TextureRegion t x y w] (texture t x y w w))
  ([^TextureRegion t x y w h (:or :* :x) m n] (texture t (* m x) (* n y) (* m w) (* n h)))
  ([^TextureRegion t x y w h (:or :* :x) m] (tscissors t x y w h :* m m))
  ([^TextureRegion t x y w (:or :* :x) m n] (tscissors t x y w w :* m n))
  ([^TextureRegion t x y w (:or :* :x) m] (tscissors t x y w w :* m m)))

(defn tex-width [^TextureRegion t] (.getRegionWidth t))
(defn tex-height [^TextureRegion t] (.getRegionHeight t))