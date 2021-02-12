(ns ticktoe.core
  (:gen-class)
  (:require [ticktoe.lib :as lib]))

(defn make-move
  "Делает ход на доску board игрока player производя xor с текущим состояние доски"
  [s row-cap boards player]
  (let [board  (get boards player)
        boardA (get boards 0)
        boardB (get boards 1)]
    (if (and (not (bit-test boardA s)) (not (bit-test boardB s)))
      ;; (reduce #(and %1 (bit-test %2 s)) true boards) ;; (bit-test board s)
      (list true (assoc boards player (bit-xor board (bit-shift-left 1 s))))
      (list false boards))))

(defn cons-board
  "Возвращает доску как строку где:
  row-cap - ширина и длинна доски
  boards  - состояние ходов игроков
  players - предстваление игрков символами например X или O
  "
  [row-cap boards players]
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

(defn draw-board [players row-cap boards]
  (-> (cons-board row-cap boards players)
      (println)))

(defn -main
  [& args]
  (let [board [0 0]
        row-cap 3
        players ["X" "O"]]
    (draw-board players row-cap board)
    (Thread/sleep 1000)
    (draw-board players
                row-cap
                (-> (lib/cons-position row-cap 0 0)
                    (make-move row-cap board 0)
                    (rest)
                    (first)))))
