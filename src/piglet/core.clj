(ns piglet.core
  (:require [clojure.string :as str])
  (:gen-class))

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

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
