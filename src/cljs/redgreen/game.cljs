(ns redgreen.game)



(defn init-plateau
  [n]
  (as-> (repeat 0) x
    (take (dec n) x)
    (into [1] x)))

(defn flip
  [x]
  (cond
    (= x 0) 1
    (= x 1) 0))

(defn tour
  [plateau]
  (let [n (count plateau)
        r (rand-int n)]
    (update plateau r flip)))