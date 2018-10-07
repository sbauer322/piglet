(ns piglet.handler
  (:require [piglet.translate :refer [pig-latin]]
            [compojure.core :refer [GET POST defroutes context]]
            [compojure.route :refer [not-found]]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
            [ring.middleware.json :refer [wrap-json-response wrap-json-body]]
            [ring.util.response :refer [response]]))

(defn tap-log
  [x]
  (println "logging: " x)
  x)

(defroutes api-routes
           (wrap-json-response
             (wrap-json-body
               (context "/api/v1.0" []
                 (POST "/pig-latin" req
                   (let [msg (get (:body req) "msg")]
                     (response {:original msg
                                :translation (pig-latin msg)})))))))

(defroutes routes
           api-routes
           (not-found "Not Found"))

(def app
  (wrap-defaults #'routes api-defaults))
