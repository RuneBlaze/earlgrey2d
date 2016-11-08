(ns earlgrey2d.core
  (:import [com.badlogic.gdx Screen Gdx Application]
           (com.badlogic.gdx.assets AssetManager)
           (com.badlogic.gdx.assets.loaders FileHandleResolver)
           (com.badlogic.gdx.graphics Color GL20))
  (:use [earlgrey2d.math]
        [earlgrey2d.color]
        [earlgrey2d.hierachy])
  (:require [earlgrey2d.meta :refer [implicit implicitly fn-or defalias]]
            [earlgrey2d.repl :as rp]
            [clojure.spec :as s]
            [defun :refer :all]))

(defn clear! []
  (.glClearColor Gdx/gl 0 0 0 1)
  (.glClear Gdx/gl GL20/GL_COLOR_BUFFER_BIT))

(s/def ::screen (s/map-of string? fn?))

(s/fdef screen
        :ret (partial instance? Screen))

(defn ^AssetManager asset-manager
  ([] (AssetManager.))
  ([^FileHandleResolver resolver] (AssetManager. resolver))
  ([^FileHandleResolver resolver loaders] (AssetManager. resolver loaders)))

(defalias asset-manager am)

(defun log
  ([t str] (.log Gdx/app t str))
  ([:error t str] (.error Gdx/app t str))
  ([:error t str e] (.error Gdx/app t str e))
  ([:debug t str] (.debug Gdx/app t str))
  ([:debug t str e] (.debug Gdx/app t str e)))

(defun set-log-level!
  ([:none] (.setLogLevel Gdx/app Application/LOG_NONE))
  ([:debug] (.setLogLevel Gdx/app Application/LOG_DEBUG))
  ([:error] (.setLogLevel Gdx/app Application/LOG_ERROR))
  ([:info] (.setLogLevel Gdx/app Application/LOG_INFO)))