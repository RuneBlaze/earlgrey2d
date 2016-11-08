(ns earlgrey2d.audiomanager
  (:import (com.badlogic.gdx.audio Music Sound)
           (com.badlogic.gdx Gdx)))

(def cur-music (atom []))
(def cur-sound (atom []))

(defn bgm! [str]
  (if @cur-music
    (doto ^Music @cur-music
      (.stop)
      (.dispose)))
  (let [m (.newMusic Gdx/audio (.internal Gdx/files str))]
    (do
      (.play m)
      (.setLooping m true)
      (reset! cur-music m))))

(defn se! [str]
  (if @cur-sound
    (doto ^Sound @cur-sound
      (.dispose)))
  (let [s (.newSound Gdx/audio (.internal Gdx/files str))]
    (do
      (.play s)
      (reset! cur-sound s))))