(ns earlgrey2d.hierachy
  (:require [defun :refer [defun]]
            [earlgrey2d.repl :as rp]
            [earlgrey2d.utils :as utils]
            [earlgrey2d.meta :refer [implicit implicitly fn-or defalias]])
  (:import (com.badlogic.gdx Game Gdx Screen)
           (xyz.unfound.earlgrey2d ErrorScreen)
           (clojure.lang Namespace)))

(defn ^Game get-game []
  (.getApplicationListener Gdx/app))

(declare reset-screen!)

(defmacro error-screen-wrap [& forms]
  `(try ~(cons 'do forms)
        (catch Exception e#
          (.printStackTrace e#)
          (reset-screen! (get-game) (ErrorScreen. e#)))))

(defn ^Screen screen [hooks]
  (letfn [(fmap-invoke [f & args]
            (do
              (when f (if (first args)
                        (apply f args)
                        (f)))))]
    (proxy [Screen] []
      (dispose [] (error-screen-wrap (fmap-invoke (:dispose hooks))))
      (hide [] (error-screen-wrap (fmap-invoke (:hide hooks))))
      (pause [] (error-screen-wrap (fmap-invoke (:pause hooks))))
      (render [d] (error-screen-wrap
                    (fmap-invoke (:update hooks))
                    (fmap-invoke (:updato hooks))
                    (fmap-invoke (:render hooks))))
      (resize [w h] (error-screen-wrap (fmap-invoke (:resize hooks) w h)))
      (resume [] (error-screen-wrap (fmap-invoke (:resume hooks))))
      (show [] (error-screen-wrap (fmap-invoke (:load hooks))
                                  (fmap-invoke (:create hooks))
                                  (fmap-invoke (:show hooks)))))))

(def map->screen screen)
(defn ^Screen fn->screen [f]
  (map->screen {:render f}))

(defn ns->screenmap [s]
  (letfn [(nresolve [a b] (let [r (ns-resolve a b)] (if r (var-get r) r)))]
    {:dispose (nresolve s 'dispose)
     :hide    (nresolve s 'hide)
     :pause   (nresolve s 'pause)
     :updato  (nresolve s 'updato)
     :render  (nresolve s 'render)
     :resize  (nresolve s 'resize)
     :resume  (nresolve s 'resume)
     :load    (nresolve s 'load)
     :create  (nresolve s 'create)
     :show    (nresolve s 'show)}))

(def ^Screen ns->screen (comp map->screen ns->screenmap))

(defun ^Screen ->screen
       ([s :guard (partial instance? Screen)] s)
       ([n :guard (fn-or symbol? (partial instance? Namespace))] (ns->screen n))
       ([f :guard fn?] (fn->screen f))
       ([m] (map->screen m)))

(defn reset-screen! [^Game g s]
  (.setScreen g (implicit s Screen ->screen)))

(defalias reset-screen! set-screen!)

(defn reload-screen! [s]
  (reset-screen! (get-game) s))

(defn r-ns! [k]
  (require k :reload)
  (reload-screen! k))

(defun rl!
  ([] (doseq [n (all-ns)]
        (require (ns-name n) :reload)) (r-ns! (.getName *ns*)))
  ([:f] (binding [utils/*force-reload* true]
          (rl!))))

(defun ^Game game
       ([f :guard fn?]
         (proxy [Game] []
           (create []
             (rp/start-server!)
             (f this))
           (dispose []
             (if @rp/server (rp/stop-server!)))))
       ([s] (game #(reset-screen! % s))))

(def ->game game)

(defn exit-game! []
  (.exit Gdx/app)
  (rp/fstop-server!)
  (System/exit 0))

(defalias exit-game! q!)