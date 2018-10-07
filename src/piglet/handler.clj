(ns piglet.handler
  (:require [clojure.string :as str]
            [compojure.core :refer [GET POST defroutes context]]
            [compojure.route :refer [not-found]]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
            [ring.middleware.json :refer [wrap-json-response wrap-json-body]]
            [ring.util.response :refer [response]]))

(def vowels #{\a \e \i \o \u})

(defn first-consonant-cluster
  [word]
  (reduce str "" (take-while #(not (contains? vowels %)) word)))

(defn pig-latin-word
  [word]
  (if (contains? vowels (first word))
    ; if true, starts with vowel
    (str word "way")
    ; else... grab first consonant cluster
    (let [cluster (first-consonant-cluster word)]
      (str (subs word (count cluster)) cluster "ay"))))

(defn pig-latin
  [msg]
  (str/join " " (map pig-latin-word (str/split msg #" "))))

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
                                :pig-latin (pig-latin msg)})))))))

(defroutes routes
           api-routes
           (not-found "Not Found"))

(def app
  (wrap-defaults #'routes api-defaults))
