(ns ticktoe.lib-test
  (:require [ticktoe.lib :refer :all]
            [clojure.test :refer :all]))

(deftest test-test-row-win
  (testing "test-row-win return true if find 111 in mask"
    (is (test-row-win 3 7  ))
    (is (test-row-win 3 56 ))
    (is (test-row-win 3 448)))
  (testing "test-row-win return false if don't find 111 in mask"
    (is (not (test-row-win 3 5  )))
    (is (not (test-row-win 3 24 )))
    (is (not (test-row-win 3 320)))))

(deftest test-test-col-win
  (testing "test-col-win win case"
    (is (test-col-win 3 73 ))
    (is (test-col-win 3 146))
    (is (test-col-win 3 292)))
  (testing "test-col-win no win case"
    (is (not (test-col-win 3 0 )))
    (is (not (test-col-win 3 7 )))
    (is (not (test-col-win 3 74)))))

(deftest test-test-diag-win
  (testing "test-diag-col-win win case"
    (is (test-diag-win 3 84 ))
    (is (test-diag-win 3 273)))
  (testing "test-diag-win no win case"
    (is (not (test-diag-win 3 17)))))
