
;hash map which is an unordered map
(def lang (hash-map :c "Clojure", :j "Java", :s "Scala", :h "Haskell",:p "Python"))
;; => {:s "Scala",
;;     :c "Clojure",
;;     :j "Java",
;;     :h "Haskell",
;;     :p "Python"}

;Common map operations
(get lang :c)
;; => "Clojure"

(contains? lang :s)
;; => true

(find lang :j)
;; => [:j "Java"]

(keys lang)
;; => (:s :c :j :h :p)

(vals lang)
;; => ("Scala" "Clojure" "Java" "Haskell" "Python")

;returns the map excluding the passed key entry
(dissoc lang :j)
;; => {:s "Scala", :c "Clojure", :h "Haskell", :p "Python"}

;return new map by merging 2+ passed maps
(merge lang
       (hash-map :l "Lisp", :e "Erlang")
       (hash-map :g "Go" :r "Rust"))
;; => {:r "Rust",
;;     :e "Erlang",
;;     :s "Scala",
;;     :l "Lisp",
;;     :g "Go",
;;     :c "Clojure",
;;     :j "Java",
;;     :h "Haskell",
;;     :p "Python"}


;Rest of the maps conj-ed into first map by mapping supplied function (+)
;Resulting map is the union of all maps with function applied on any of the recurring elements
(merge-with + (hash-map :a 1, :b 3, :c 5) (hash-map :a 4, :d 6, :e 7) (hash-map :c 1, :f 16, :d 12) )
;; => {:e 7, :c 6, :b 3, :d 18, :f 16, :a 5}

;return map of elements for the supplied list of keys
(select-keys lang [:j :s])
;; => {:j "Java", :s "Scala"}

;return new hashmap with renamed keys as passed in pairs of original-new
(ns rn
  (:require [clojure.set :as set]))
(set/rename-keys (hash-map :a 1,:b 2, :c 3) {:a :p, :b :q, :c :r})
;; => {:q 2, :r 3, :p 1}

;invert keys-vals of the map so that vals become keys
(set/map-invert (hash-map :a 1, :b 2, :c 3))
;; => {3 :c, 2 :b, 1 :a}




;heterogeneous keys
(

 let [m {
         :a         1, ;keyword
         1         :b, ;integer
         'c'    "char" ;character
         [:fruits :veggies :meat]  "Apple Spinach chicken"
        }
      ]

  [
   (get m :a)
   (get m 'c')
   (get m [:fruits :veggies :meat])
   ]
)
;; => [1 "char" "Apple Spinach chicken"]

;passing map to sequence creates sequence of map pairs
(def mapsq (seq {:1 "one", :2 "two" :3 "three"}))
;; => ([:1 "one"] [:2 "two"] [:3 "three"])



;convert sequence with map pairs back into map
(into {} mapsq)
;; => {:1 "one", :2 "two", :3 "three"}



; using apply, ungrouped pairs are grouped in hash map if they are
; laid out in sequence
(apply hash-map [:a 1 :b 2 :c 3])
;; => {:b 2, :a 1}


;zip map to create new map out of two sequences..
;one as keys & another as values
(zipmap  [:a :b :c] [1 2 3])
;; => {:a 1, :b 2, :c 3}

;sorted map with default key based compare function order guaranty
(sorted-map :Abby 90 :Dabby 45 :Shabby 33 :Booby 29)
;; => {:Abby 90, :Booby 29, :Dabby 45, :Shabby 33}


; sorted-map-by to supply custom sorting order compare function
; IN this example, compare sorts string keys by no. of characters in them.
(sorted-map-by #(compare (count %1) (count %2)) "longstring" 10 "string" 6 "str" 3)
;; => {"str" 3, "string" 6, "longstring" 10}


;arraymap that ensures insertion order
 (array-map :a 1, :b 2, :c 3)
;; => {:a 1, :b 2, :c 3}

;vs. the lack of insertion order guaranty
(hash-map :a 1, :b 2, :c 3)
;; => {:c 3, :b 2, :a 1}

























