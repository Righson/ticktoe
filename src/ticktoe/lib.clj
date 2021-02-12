(ns ticktoe.lib)

(defn reduce-board [f board]
  (reduce #(and %1 (f %2)) true board))

(defn pow
  ([base x] (pow base x 1))
  ([base x acc]
   (if (= x 0)
     acc
     (pow base (dec x) (* acc base)))))

(defn cons-position [row-cap x y]
  (+ (* row-cap x) y))

(defn cons-origins [row-cap]
  (map #(ticktoe.lib/pow 2 %) (range 0 row-cap)))

(defn cons-col-positions
  ([col limit] (cons-col-positions col limit limit [col]))
  ([col limit adder acc]
   (if-not (= limit 1)
     (let [next-pos   (+ adder (last acc))
           next-limit (dec limit)]
     (cons-col-positions col next-limit adder (conj acc next-pos)))
     acc)))

(defn cons-diag-position-left [_ limit]
  (map #(+ %1 %2) (ticktoe.lib/cons-col-positions 0 limit) (range 0 limit)))

(defn cons-diag-position-right [_ limit]
  (map #(+ %1 %2) (ticktoe.lib/cons-col-positions 0 limit) (range (dec limit) -1 -1)))
;; --------------------- TEST WIN ---------------------
(defn test-row-win [row-cap board]
  (loop [r (* row-cap row-cap)]
    (let [next-row-cap (- r row-cap)
          test-res (reduce-board #(bit-test board %) (range next-row-cap r))]
      (if (and (> r row-cap) (not test-res))
        (recur (- r row-cap))
        test-res))))

(defn test-win [row-cap board func]
  (loop [cols (range 0 row-cap)]
    (if-not (empty? cols)
      (let [col (first cols)
            test-res (reduce-board #(bit-test board %) (func col row-cap))]
        (if-not test-res
          (recur (rest cols))
          true))
      false)))

(defn test-col-win [row-cap board]
  (test-win row-cap board cons-col-positions))

(defn test-diag-win [row-cap board]
  (or (test-win row-cap board cons-diag-position-left)
      (test-win row-cap board cons-diag-position-right)))
