(ns ratings-calculator.test.core
  (:use [ratings-calculator.core])
  (:use [clojure.test]))

(defn call-adjust-rating
  ([winner-rating, loser-rating, points-exchanged]
     (let [new-winner-rating (+ winner-rating points-exchanged)
           new-loser-rating (- loser-rating points-exchanged)]
       (= (adjust-ratings winner-rating loser-rating)
          (list new-winner-rating new-loser-rating)))))

(defmacro test-same-rated-players [adjustment]
  `(deftest ~(gensym "test-")
     (is (call-adjust-rating 300 300 ~adjustment))))

(defmacro test-winner-is-higher-rated-than-loser [difference adjustment]
  `(deftest ~(gensym "test-")
     (is (call-adjust-rating 300 (- 300 ~difference) ~adjustment))))

(defmacro test-winner-is-lower-rated-than-loser [difference adjustment]
  `(deftest ~(gensym "test-")
     (is (call-adjust-rating (- 300 ~difference) 300 ~adjustment))))

(test-same-rated-players 8)

(test-winner-is-higher-rated-than-loser 1 8)
(test-winner-is-higher-rated-than-loser 12 8)
(test-winner-is-higher-rated-than-loser 13 7)
(test-winner-is-higher-rated-than-loser 37 7)
(test-winner-is-higher-rated-than-loser 38 6)
(test-winner-is-higher-rated-than-loser 62 6)
(test-winner-is-higher-rated-than-loser 63 5)
(test-winner-is-higher-rated-than-loser 87 5)
(test-winner-is-higher-rated-than-loser 88 4)
(test-winner-is-higher-rated-than-loser 112 4)
(test-winner-is-higher-rated-than-loser 113 3)
(test-winner-is-higher-rated-than-loser 137 3)
(test-winner-is-higher-rated-than-loser 138 2)
(test-winner-is-higher-rated-than-loser 162 2)
(test-winner-is-higher-rated-than-loser 163 2)
(test-winner-is-higher-rated-than-loser 187 2)
(test-winner-is-higher-rated-than-loser 188 1)
(test-winner-is-higher-rated-than-loser 212 1)
(test-winner-is-higher-rated-than-loser 213 1)
(test-winner-is-higher-rated-than-loser 237 1)
(test-winner-is-higher-rated-than-loser 238 0)


(test-winner-is-lower-rated-than-loser 1 8)
(test-winner-is-lower-rated-than-loser 12 8)
(test-winner-is-lower-rated-than-loser 13 10)
(test-winner-is-lower-rated-than-loser 37 10)
(test-winner-is-lower-rated-than-loser 38 13)
(test-winner-is-lower-rated-than-loser 62 13)
(test-winner-is-lower-rated-than-loser 63 16)
(test-winner-is-lower-rated-than-loser 87 16)
(test-winner-is-lower-rated-than-loser 88 20)
(test-winner-is-lower-rated-than-loser 112 20)
(test-winner-is-lower-rated-than-loser 113 25)
(test-winner-is-lower-rated-than-loser 137 25)
(test-winner-is-lower-rated-than-loser 138 30)
(test-winner-is-lower-rated-than-loser 162 30)
(test-winner-is-lower-rated-than-loser 163 35)
(test-winner-is-lower-rated-than-loser 187 35)
(test-winner-is-lower-rated-than-loser 188 40)
(test-winner-is-lower-rated-than-loser 212 40)
(test-winner-is-lower-rated-than-loser 213 45)
(test-winner-is-lower-rated-than-loser 237 45)
(test-winner-is-lower-rated-than-loser 238 50)



