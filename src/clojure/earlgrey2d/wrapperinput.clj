(ns earlgrey2d.wrapperinput
  (:import [com.badlogic.gdx Input Gdx])
  (:require [defun :refer [defun]]))

(def keycodes
  {:any-key 1,
   :num-0 0,
   :num-1 1, :num-2 2, :num-3 3, :num-4 4,
   :num-5 5, :num-6 6, :num-7 7, :num-8 8, :num-9 9, :a 29,
   :alt-left 57, :alt-right 58, :apostrophe 75, :at 77,
   :b 30, :back 4, :backslash 73, :c 31, :call 5, :camera 27,
   :clear 28, :comma 55, :d 32, :del 67, :backspace 67,
   :forward-del 112, :dpad-center 23, :dpad-down 20,
   :dpad-left 21, :dpad-right 22, :dpad-up 19, :center 23,
   :down 20, :left 21, :right 22, :up 19, :e 33, :endcall 6,
   :enter 66, :envelope 65, :equals 70, :explorer 64, :f 34,
   :focus 80, :g 35, :grave 68, :h 36, :headsethook 79, :home 3,
   :i 37, :j 38, :k 39, :l 40, :left-bracket 71, :m 41, :media-fast-forward 90,
   :media-next 87, :media-play-pause 85, :media-previous 88, :media-rewind 89,
   :media-stop 86, :menu 82, :minus 69, :mute 91, :n 42, :notification 83,
   :num 78, :o 43, :p 44, :period 56, :plus 81, :pound 18, :power 26, :q 45,
   :r 46, :right-bracket 72, :s 47, :search 84, :semicolon 74, :shift-left 59,
   :shift-right 60, :slash 76, :soft-left 1, :soft-right 2, :space 62, :star 17,
   :sym 63, :t 48, :tab 61, :u 49,
   :unknown 0,
   :v 50, :volume-down 25, :volume-up 24,
   :w 51, :x 52, :y 53, :z 54, :meta-alt-left-on 16, :meta-alt-on 2, :meta-alt-right-on 32,
   :meta-shift-left-on 64, :meta-shift-on 1, :meta-shift-right-on 128, :meta-sym-on 4,
   :control-left 129, :control-right 130, :escape 131, :end 132, :insert 133, :page-up 92,
   :page-down 93, :pictsymbols 94, :switch-charset 95, :button-circle 255, :button-a 96,
   :button-b 97, :button-c 98, :button-x 99, :button-y 100, :button-z 101, :button-l1 1,
   :button-r1 1, :button-l2 2, :button-r2 2, :button-thumbl 106, :button-thumbr 107,
   :button-start 108, :button-select 109, :button-mode 110,
   :numpad-0 0,
   :numpad-1 1,
   :numpad-2 2, :numpad-3 3, :numpad-4 4, :numpad-5 5, :numpad-6 6, :numpad-7 7, :numpad-8 8,
   :numpad-9 9, :colon 243, :f1 1, :f2 2, :f3 3, :f4 4, :f5 5, :f6 6, :f7 7, :f8 8,
   :f9 9, :f10 10, :f11 11, :f12 12}
  )

(comment def keycodes (select-keys keycodes [:up :right :left :down :z :x :c :a :s :space]))

(def keycode-values (vals keycodes))

(defun is-key-pressed?
       ([k :guard keyword?]
         (let [key-i (k keycodes)]
           (is-key-pressed? key-i)))
       ([key-i] (.isKeyPressed Gdx/input key-i)))

(def pressed? is-key-pressed?)