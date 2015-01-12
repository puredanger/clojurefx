(defproject clojurefx "0.0.13"
  :description "Helper functions and probably a wrapper to simplify usage of JavaFX in Clojure.

  This is meant to be used with Java 8. If you add JavaFX 2.2 to your classpath it might still work, but that isn't tested.
  
  [This Project On GitHub](https://www.github.com/zilti/clojurefx)

**Installation: `[clojurefx \"0.0.13\"]`**

Navigation
----------

 * <a href=\"#contentcreation\">Content creation / the 'fx' macro</a>
 * <a href=\"#contentmodification\">Content modification / the 'swap-content!' macro</a>
 * <a href=\"#databinding\">Data binding / the 'bind-property!'/'bidirectional-bind-property!' macros</a>
 * <a href=\"#events\">Event handling / the 'set-listener!' macro</a> "
  :url "https://www.github.com/zilti/clojurefx"
  :lein-release {:deploy-via :clojars}
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]]
  :plugins [[lein-marginalia "0.7.1"]
            [lein-midje "3.1.3-RC2"]
            [lein-release "1.0.5"]]
  :profiles {:dev {:dependencies [[midje "1.6-beta1"]
                                  [troncle "0.1.2-SNAPSHOT"]
                                  [org.clojure/tools.trace "0.7.6"]]}})
