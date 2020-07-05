
(ns com.clojuretryout.collections.sets
  (:require clojure.set) ;has intersection, union , difference functions
)

(def s #{:a :b :c})
;; => #'com.clojuretryout.collections.sets/s


(s :c)
;; => :c

(s :d)
;; => nil


(#{1 2 3 4 5} 3) ;matches 3 in defined set
;; => 3


(get #{10 20 30 40} 60 0)  ; get 0 if 60 not found
;; => 0


(into #{[1 2 3]} '[(1 2 3)])

(sorted-set 2 4 5 6 3) ; returns sorted set

(contains? #{1 2 4 3} 4) ; returns true for set r
(contains? [1 2 3 4] 4) ;but false for vector

(clojure.set/intersection #{ 1 2 3 4 5 6 7 8 9 10 }
                          #{ 2 4 6 8 10})
                          ;; => #{4 6 2 10 8}


(clojure.set/union #{1 2 3 4 5 6 7 8 9 10}
                   #{3 5 7 100}
                   #{1 10 12})
;; => #{7 1 4 100 6 3 12 2 9 5 10 8}


(clojure.set/difference #{1 2 3 4} #{3 4 5 6})
;; => #{1 2}
