(defproject earlgray2d "0.1.0-SNAPSHOT"
  :description "limited wrapper for libGdx in Clojure, focusing on 2d right now"
  :url "http://github.com/RuneBlaze/ramp"
  :global-vars {*warn-on-reflection* true}
  :license {:name "LGPL-3.0"
            :url "https://www.gnu.org/licenses/lgpl-3.0.txt"}
  :dependencies [[org.clojure/clojure "1.9.0-alpha13"]
                 [com.badlogicgames.gdx/gdx "1.9.4"]
                 [com.badlogicgames.gdx/gdx-backend-lwjgl3 "1.9.4"]
                 [com.badlogicgames.gdx/gdx-platform "1.9.4" :classifier "natives-desktop"]
                 [com.badlogicgames.gdx/gdx-freetype "1.9.4"]
                 [com.badlogicgames.gdx/gdx-freetype-platform "1.9.4" :classifier "natives-armeabi"]
                 [com.badlogicgames.gdx/gdx-freetype-platform "1.9.4" :classifier "natives-armeabi-v7a"]
                 [com.badlogicgames.gdx/gdx-freetype-platform "1.9.4" :classifier "natives-arm64-v8a"]
                 [com.badlogicgames.gdx/gdx-freetype-platform "1.9.4" :classifier "natives-x86"]
                 [com.badlogicgames.gdx/gdx-freetype-platform "1.9.4" :classifier "natives-x86_64"]
                 [defun "0.3.0-alapha"]
                 [org.clojure/test.check "0.9.0"]
                 [camel-snake-kebab "0.4.0"]
                 [com.taoensso/tufte "1.0.2"]
                 [org.clojure/tools.nrepl "0.2.12"]
                 [swiss-arrows "1.0.0"]]
  :source-paths ["src/clojure"]
  :java-source-paths ["src/java"])
