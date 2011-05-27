(ns ratings-calculator.core
  (:gen-class))

(defn winners-rating-better-than-loser
  ([winner, loser] (>= winner loser)))

(defn calculate-new-ratings
  ([winner, loser, adjustment]
     (list (+ winner adjustment) (- loser adjustment))))

(def expected-ratings [[12 8 8]
                       [37 7 10]
                       [62 6 13]
                       [87 5 16]
                       [112 4 20]
                       [137 3 25]
                       [162 2 30]
                       [187 2 35]
                       [212 1 40]
                       [237 1 45]
                       [nil 0 50]])

(defn calculate-expected-ranking-helper
  ([winner, loser, lst]
     (cond
      (or (nil? (first (first lst)))
          (<= (- winner loser) (first (first lst))))
      (calculate-new-ratings winner loser (second (first lst)))
      :else (calculate-expected-ranking-helper winner loser (rest lst)))))

(defn calculate-expected-ranking
  ([winner, loser]
     (calculate-expected-ranking-helper winner loser expected-ratings)))


(defn calculate-upset-ranking-helper
  ([winner, loser, lst]
     (cond
      (or (nil? (first (first lst)))
          (<= (- loser winner) (first (first lst))))
      (calculate-new-ratings winner loser (nth (first lst) 2))
      :else (calculate-upset-ranking-helper winner loser (rest lst)))))

(defn calculate-upset-ranking
  ([winner, loser]
     (calculate-upset-ranking-helper winner loser expected-ratings)))


(defn adjust-ratings
  ([winner, loser] (if (winners-rating-better-than-loser winner loser)
                     (calculate-expected-ranking winner loser)
                     (calculate-upset-ranking winner loser))))

(defn -main [& args]
  (println "Welcome to the ratings calculator!")
  (println (adjust-ratings (Integer/parseInt (first args))
                           (Integer/parseInt (second args)))))


