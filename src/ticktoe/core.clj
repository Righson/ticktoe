(ns ticktoe.core
  (:gen-class))


(defn make-move
  "Делает ход на доску board игрока player производя xor с текущим состояние доски"
  [boards, row-cap, player, x, y]
  (let [s (+ (* row-cap x) y)
        board (get boards player)]
    (if (reduce #(and %1 (bit-test %2 s)) true boards) ;; (bit-test board s)
      (list false boards)
      (list true (assoc boards player (bit-xor board (bit-shift-left 1 s)))))))

(defn cons-board [boards row-cap players]
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

(defn draw-board [boards row-cap players]
  (-> (cons-board boards row-cap players)
      (println)))



(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
