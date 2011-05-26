(ns ratings-calculator.core
  (:gen-class))

(defn winners-rating-better-than-loser
  ([winner, loser] (>= winner loser)))

(defn calculate-new-ratings
  ([winner, loser, adjustment] (list (+ winner adjustment) (- loser adjustment))))

(defn calculate-expected-ranking
  ([winner, loser]
     (cond (<= (- winner loser) 12)  (calculate-new-ratings winner loser 8)
           (<= (- winner loser) 37)  (calculate-new-ratings winner loser 7)
           (<= (- winner loser) 62)  (calculate-new-ratings winner loser 6)
           (<= (- winner loser) 87)  (calculate-new-ratings winner loser 5)
           (<= (- winner loser) 112) (calculate-new-ratings winner loser 4)
           (<= (- winner loser) 137) (calculate-new-ratings winner loser 3)
           (<= (- winner loser) 162) (calculate-new-ratings winner loser 2)
           (<= (- winner loser) 187) (calculate-new-ratings winner loser 2)
           (<= (- winner loser) 212) (calculate-new-ratings winner loser 1)
           (<= (- winner loser) 237) (calculate-new-ratings winner loser 1)
           :else (list winner loser))))

(defn calculate-upset-ranking
  ([winner, loser]
     (cond (<= (- loser winner) 12)  (calculate-new-ratings winner loser 8)
           (<= (- loser winner) 37)  (calculate-new-ratings winner loser 10)
           (<= (- loser winner) 62)  (calculate-new-ratings winner loser 13)
           (<= (- loser winner) 87)  (calculate-new-ratings winner loser 16)
           (<= (- loser winner) 112) (calculate-new-ratings winner loser 20)
           (<= (- loser winner) 137) (calculate-new-ratings winner loser 25)
           (<= (- loser winner) 162) (calculate-new-ratings winner loser 30)
           (<= (- loser winner) 187) (calculate-new-ratings winner loser 35)
           (<= (- loser winner) 212) (calculate-new-ratings winner loser 40)
           (<= (- loser winner) 237) (calculate-new-ratings winner loser 45)
           :else (calculate-new-ratings winner loser 50))))

(defn adjust-ratings
  ([winner, loser] (if (winners-rating-better-than-loser winner loser)
                     (calculate-expected-ranking winner loser)
                     (calculate-upset-ranking winner loser))))

(defn -main [& args]
  (println "Welcome to the ratings calculator!")
  (println (adjust-ratings (Integer/parseInt (first args))
                           (Integer/parseInt (second args)))))


