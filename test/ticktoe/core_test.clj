(ns ticktoe.core-test
  (:require [clojure.test :refer :all]
            [ticktoe.core :refer :all]))

(deftest test-cons-board
  (testing "cons-bard should return correct string"
    (is (= "X*\n**"
           (cons-board 2 [1 0] ["X" "O"])))))

(deftest test-make-move
  (testing "make-move should return true if move is available"
    (is (first (make-move 3 [0 0] 0 0)))
    (is (= [1 0] (first (rest (make-move 3 [0 0] 0 0))))))
  (testing "make-move should return false if move is not available"
    (is (not (first (make-move 3 [1 0] 1 0))))))

(deftest test-test-row-win
  (testing "test-row-win return true if find 111 in mask"
    (is (test-row-win 3 7  ))
    (is (test-row-win 3 56 ))
    (is (test-row-win 3 448)))
  (testing "test-row-win return false if don't find 111 in mask"
    (is (not (test-row-win 3 5  )))
    (is (not (test-row-win 3 24 )))
    (is (not (test-row-win 3 320)))))
