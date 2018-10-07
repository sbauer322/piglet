(ns piglet.translate
  (:require [clojure.string :as str]))

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