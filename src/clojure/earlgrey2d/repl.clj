(ns earlgrey2d.repl
  (:require [clojure.tools.nrepl.server :refer [start-server stop-server]]))

(def server (atom nil))

(defn start-server! []
  (reset! server (start-server :port 7342)))

(defn stop-server! []
  (stop-server @server))

(defn fstop-server! []
  (if @server (stop-server!)))