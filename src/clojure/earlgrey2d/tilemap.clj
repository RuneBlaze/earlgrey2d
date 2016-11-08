(ns earlgrey2d.tilemap
  (:require [defun :refer :all]
            [earlgrey2d.core :refer [->file-handle]]
            [earlgrey2d.meta :refer [defalias]])
  (:import (com.badlogic.gdx.maps.tiled TmxMapLoader TiledMap TiledMapTileLayer)
           (com.badlogic.gdx.maps.tiled.renderers OrthogonalTiledMapRenderer)))

(defun ^TiledMap map-load
  ([:tmx name] (.load (TmxMapLoader.) name))
  ([:tmx fh name] (.load (TmxMapLoader. (->file-handle fh)) name)))

(def tmx-load (partial map-load :tmx))

(defun renderer
  ([:ortho ^TiledMap map ^double scale] (OrthogonalTiledMapRenderer. map scale)))

(defn layers [^TiledMap map]
  (vec (.getLayers map)))

(defn cell [^TiledMapTileLayer l c r]
  (.getCell l c r))

(defn layer-width [^TiledMapTileLayer l] (.getWidth l))
(defn layer-height [^TiledMapTileLayer l] (.getHeight l))
(defalias layer-width layer-columns)
(defalias layer-height layer-rows)