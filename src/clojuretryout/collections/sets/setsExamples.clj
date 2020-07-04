
(ns com.clojuretryout.collections.sets
  (:require clojure.set) ;has intersection, union , difference functions
  )
;Sets

(def s #{:a :b :c})

(s :c)
(s :d) ; returns nil

(#{1 2 3 4 5} 3) ;matches 3 in defined set

(get #{10 20 30 40} 60 0) ; get 0 if 60 not found

(into #{[1 2 3]} '[(1 2 3)])

(sorted-set 2 4 5 6 3) ; returns sorted set

(contains? #{1 2 4 3} 4) ; returns true for set but false for vector

(clojure.set/intersection #{:humans :fruit-bats :zombies}
                          #{:chupacabra :zombies :humans})

(clojure.set/union #{:pez :gum :dots :skor}
                   #{:pez :skor :pocky}
                   #{:pocky :gum :skor})

(clojure.set/difference #{1 2 3 4} #{3 4 5 6}) ;returns #{1,2}