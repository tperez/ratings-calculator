(ns ratings-calculator.core
  (:use clojure.contrib.math))

(defn calculate-new-ratings
  ([winner, loser, adjustment]
     (list (+ winner adjustment) (- loser adjustment))))

(def rankings [[12 8 8]
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

(defn calculate-ranking-helper
  ([winner, loser, lst, nth-position]
     (cond
      (or (nil? (first (first lst)))
          (<= (abs (- winner loser)) (first (first lst))))
      (calculate-new-ratings winner loser (nth (first lst) nth-position))
      :else (calculate-ranking-helper winner loser (rest lst) nth-position))))

(defn calculate-expected-ranking
  ([winner, loser]
     (calculate-ranking-helper winner loser rankings 1)))

(defn calculate-upset-ranking
  ([winner, loser]
     (calculate-ranking-helper winner loser rankings 2)))

(defn winners-rating-better-than-loser
  ([winner, loser] (>= winner loser)))

(defn adjust-ratings
  ([winner, loser] (if (winners-rating-better-than-loser winner loser)
                     (calculate-expected-ranking winner loser)
                     (calculate-upset-ranking winner loser))))

(defn -main [& args]
  (println "Welcome to the ratings calculator!")
  (println (adjust-ratings (Integer/parseInt (first args))
                           (Integer/parseInt (second args)))))

