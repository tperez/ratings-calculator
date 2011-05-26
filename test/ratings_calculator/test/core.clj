(ns ratings-calculator.test.core
  (:use [ratings-calculator.core])
  (:use [clojure.test]))

(defn call-adjust-rating
  ([winner-rating, loser-rating, points-exchanged]
     (let [new-winner-rating (+ winner-rating points-exchanged)
           new-loser-rating (- loser-rating points-exchanged)]
       (is (= (adjust-ratings winner-rating loser-rating)
              (list new-winner-rating new-loser-rating))))))

(deftest test-winner-has-same-rating-as-loser
  (is (call-adjust-rating 300 300 8)))

(deftest test-winner-higher-ranked-than-loser
  (is (call-adjust-rating 300 (- 300 12)  8))
  (is (call-adjust-rating 300 (- 300 13)  7))
  (is (call-adjust-rating 300 (- 300 37)  7))
  (is (call-adjust-rating 300 (- 300 38)  6))
  (is (call-adjust-rating 300 (- 300 62)  6))
  (is (call-adjust-rating 300 (- 300 63)  5))
  (is (call-adjust-rating 300 (- 300 87)  5))
  (is (call-adjust-rating 300 (- 300 88)  4))
  (is (call-adjust-rating 300 (- 300 112) 4))
  (is (call-adjust-rating 300 (- 300 113) 3))
  (is (call-adjust-rating 300 (- 300 137) 3))
  (is (call-adjust-rating 300 (- 300 138) 2))
  (is (call-adjust-rating 300 (- 300 162) 2))
  (is (call-adjust-rating 300 (- 300 163) 2))
  (is (call-adjust-rating 300 (- 300 187) 2))
  (is (call-adjust-rating 300 (- 300 188) 1))
  (is (call-adjust-rating 300 (- 300 212) 1))
  (is (call-adjust-rating 300 (- 300 213) 1))
  (is (call-adjust-rating 300 (- 300 237) 1))
  (is (call-adjust-rating 300 (- 300 238) 0)))


(deftest test-winner-lower-ranked-than-loser
  (is (call-adjust-rating (- 300 12)  300 8))
  (is (call-adjust-rating (- 300 13)  300 10))
  (is (call-adjust-rating (- 300 37)  300 10))
  (is (call-adjust-rating (- 300 38)  300 13))
  (is (call-adjust-rating (- 300 62)  300 13))
  (is (call-adjust-rating (- 300 63)  300 16))
  (is (call-adjust-rating (- 300 87)  300 16))
  (is (call-adjust-rating (- 300 88)  300 20))
  (is (call-adjust-rating (- 300 112) 300 20))
  (is (call-adjust-rating (- 300 113) 300 25))
  (is (call-adjust-rating (- 300 137) 300 25))
  (is (call-adjust-rating (- 300 138) 300 30))
  (is (call-adjust-rating (- 300 162) 300 30))
  (is (call-adjust-rating (- 300 163) 300 35))
  (is (call-adjust-rating (- 300 187) 300 35))
  (is (call-adjust-rating (- 300 188) 300 40))
  (is (call-adjust-rating (- 300 212) 300 40))
  (is (call-adjust-rating (- 300 213) 300 45))
  (is (call-adjust-rating (- 300 237) 300 45))
  (is (call-adjust-rating (- 300 238) 300 50)))
