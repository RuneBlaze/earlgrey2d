(ns earlgrey2d.filehandle
  (:require [earlgrey2d.meta :refer [implicit implicitly fn-or defalias]]
            [clojure.spec :as s]
            [defun :refer :all])
  (:import (com.badlogic.gdx Gdx)
           (com.badlogic.gdx.files FileHandle)))

(defun ^FileHandle ->file-handle
       ([^String str] (->file-handle :internal str))
       ([:internal ^String str] (.internal Gdx/files str))
       ([:external ^String str] (.external Gdx/files str))
       ([:classpath ^String str] (.classpath Gdx/files str))
       ([[a b]] (->file-handle a b)))

(def ->FileHandle ->file-handle)

(def file-handle ->file-handle)

(def implicit-file-handle (partial implicitly FileHandle ->file-handle))