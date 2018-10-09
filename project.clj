(defproject piglet "0.1.0-SNAPSHOT"
  :description "Service for translating messages to and from pig latin"
  :url "https://github.com/sbauer322/trebek"
  :license {:name "MIT"
            :url "https://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [http-kit "2.2.0"]
                 [ring "1.5.0"]
                 [ring/ring-defaults "0.2.1"]
                 [ring/ring-json "0.4.0"]
                 [compojure "1.5.1"]
                 [yogthos/config "0.8"]
                 [cheshire "5.7.0"]]
  :plugins [[lein-environ "1.0.2"]]
  :ring {:handler piglet.handler/app
         :uberwar-name "piglet.war"}
  :min-lein-version "2.5.0"
  :uberjar-name "piglet.jar"
  :main piglet.core
  :clean-targets ^{:protect false}
  [:target-path "target/%s"]
  :source-paths ["src"]
  :resource-paths ["resources"]
  :profiles {:uberjar {:omit-source true
                       :aot :all
                       :uberjar-name "piglet.jar"
                       :resource-paths ["env/prod/resources"]}
             :dev [:project/dev :profiles/dev]
             :test [:project/dev :project/test :profiles/test]
             :project/dev {:resource-paths ["env/dev/resources"]}
             :project/test {:resource-paths ["env/dev/resources" "env/test/resources"]}
             :profiles/dev {}
             :profiles/test {}})

