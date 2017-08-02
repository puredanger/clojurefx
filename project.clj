(defproject clojurefx/clojurefx "0.3.0"
  :description "A Clojure wrapper for JavaFX."
  :license "Like Clojure."
  :url "https://www.bitbucket.org/zilti/clojurefx"
  :signing {:gpg-key "68484437"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [swiss-arrows "1.0.0"]
                 [camel-snake-kebab "0.4.0"]
                 [org.controlsfx/controlsfx "8.40.13"]
                 [com.taoensso/timbre "4.7.4" :exclusions [com.taoensso/carmine]]
                 [net.openhft/compiler "2.3.0"]
                 [clojure-jsr-223 "0.1.0"]]
  ;; :profiles {:uberjar {:aot :all}}      
  :profiles {:test {:source-paths ["test"]
                    :resource-paths ["test-resources"]}}
  :source-paths ["src"]
  :java-source-paths ["src"])
