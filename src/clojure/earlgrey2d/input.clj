(ns earlgrey2d.input
  (:require [earlgrey2d.wrapperinput :refer :all]))

(defonce cache-map (atom {}))
(defonce input-map (atom {}))

(defn pack []
  {:cache @cache-map :current @input-map})

(defn update! []
  (reset! cache-map @input-map)
  (reset! input-map (zipmap (keys keycodes) (map is-key-pressed? (keys keycodes)))))

(defn- bmatch [lhs rhs]
  (or (= rhs :any) (= rhs lhs)))

(defn check-input
  ([b1 b2 s {:keys [cache current]}]
   (and (bmatch (get cache s) b1) (bmatch (get current s) b2)))
  ([b1 b2 s]
   (check-input b1 b2 s (pack))))

(def trigger? (partial check-input false true))
(def released? (partial check-input true false))
(def repeat? (partial check-input true true))
(def press? (partial check-input :any true))