(ns earlgrey2d.desktop
  (:import [com.badlogic.gdx.backends.lwjgl3 Lwjgl3Application Lwjgl3ApplicationConfiguration]
           (com.badlogic.gdx Game))
  (:require [swiss.arrows :refer :all]
            [earlgrey2d.core :refer :all]
            [earlgrey2d.meta :refer [implicit defalias]]))

(declare map->config)
(defn config [title width height]
  (let [c (Lwjgl3ApplicationConfiguration.)]
    (map->config {:title title :width width :height height})))

(defn map->config [{:keys [width height x y fullscreen title resizable]}]
  (let [c (Lwjgl3ApplicationConfiguration.)]
    (do
      (if (and width height)
        (.setWindowedMode c width height))
      (if (and x y)
        (.setWindowPosition c x y))
      (when title (.setTitle c title))
      (when resizable (.setResizable c resizable))
      c)))

(defn ^Lwjgl3Application lwjgl-app
  ([g cfg] (Lwjgl3Application. (implicit g Game ->game)
                              (implicit cfg Lwjgl3ApplicationConfiguration map->config)))
  ([g title width height] (lwjgl-app g (config title width height))))

(defalias lwjgl-app
          lwjgl-application lwjgl-application! lwjgl-app! app app!)