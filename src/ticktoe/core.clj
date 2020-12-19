(ns ticktoe.core
  (:gen-class))

(defn reduce-board [f board]
  (reduce #(and %1 (f %2)) true board))

(defn test-row-win [row-cap board]
  (loop [r (* row-cap row-cap)]
    (let [next-row-cap (- r row-cap)
          test-res (reduce-board #(bit-test board %) (range next-row-cap r))]
      (if (and (> r row-cap) (not test-res))
        (recur (- r row-cap))
        test-res))))

(defn make-move
  "Делает ход на доску board игрока player производя xor с текущим состояние доски"
  [row-cap boards player x y]
  (let [s (+ (* row-cap x) y)
        board (get boards player)]
    (if (reduce #(and %1 (bit-test %2 s)) true boards) ;; (bit-test board s)
      (list false boards)
      (list true (assoc boards player (bit-xor board (bit-shift-left 1 s)))))))

(defn cons-board [row-cap boards players]
  (loop [p-idx 0
         idx 0
         res ""]
    (let [rc (* row-cap row-cap)
          eol (if (and (> idx 1) (= (mod idx row-cap) 0)) "\n" "")]
      (if (not= rc idx)
        (do
          (if (bit-test (get boards p-idx 0) idx)
            (recur 0 (inc idx) (str res eol (get players p-idx)))
            (if (= p-idx (count players))
              (recur 0 (inc idx) (str res eol "*"))
              (recur (inc p-idx) idx res))))
        res))))

(defn draw-board [row-cap boards players]
  (-> (cons-board row-cap boards players)
      (println)))



(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
