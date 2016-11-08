(ns earlgrey2d.cache
  (:require [earlgrey2d.core :refer [asset-manager ->file-handle]]
            [earlgrey2d.meta :refer [implicit]])
  (:import (com.badlogic.gdx.files FileHandle)
           (com.badlogic.gdx.assets AssetManager)
           (com.badlogic.gdx.graphics.g2d TextureRegion)))

(defonce manager (delay (asset-manager)))

(defn queue-asset! [t p]
  (.load ^AssetManager @manager p t))

(def queue-texture! (partial queue-asset! TextureRegion))

(defn get! [^String f] (.get ^AssetManager @manager f))

(defn update! [] (.update ^AssetManager @manager))

(defn finish! [] (.finishLoading ^AssetManager @manager))

(defn finish-asset! [p] (.finishLoadingAsset ^AssetManager @manager p))

(def drain! (comp get! finish-asset!))

(defn as-texture [obj] (^TextureRegion obj))