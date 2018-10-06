(defproject piglet "0.1.0-SNAPSHOT"
  :description "Service for translating messages to and from pig latin"
  :url "https://github.com/sbauer322/trebek"
  :license {:name "MIT"
            :url "https://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.9.0"]]
  :main ^:skip-aot piglet.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
