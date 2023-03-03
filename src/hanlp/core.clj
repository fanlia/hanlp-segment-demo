(ns hanlp.core
  (:import [com.hankcs.hanlp HanLP])
  (:gen-class))

(defn count-word [m word] (count (get m word)))

(defn counter [m word] {:word word :count (count-word m word)})

(defn -main
  "hanlp segment"
  [text]
  (let [
    ; text to terms
    terms (HanLP/segment text)
    ; terms to string list
    words (map #(.word %) terms)
    ; group by word
    m (group-by identity words)
    ; count by word
    wc (map #(counter m %) (keys m))
    ; sort by count
    result (reverse (sort-by :count wc))
  ]
  (println result))
)
