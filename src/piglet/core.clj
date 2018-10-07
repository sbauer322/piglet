(ns piglet.core
  (:require [piglet.handler :refer [app]]
            [config.core :refer [env]]
            [org.httpkit.server :refer [run-server]])
  (:gen-class))

(defonce server (atom nil))

(defn stop-server []
  (when-not (nil? @server)
    (@server :timeout 100)
    (reset! server nil)))

(defn -main [& args]
  (let [port (Integer/parseInt (:port env 3000))]
    (run-server #'app {:port port})
    (println "Server started at port " port)))
