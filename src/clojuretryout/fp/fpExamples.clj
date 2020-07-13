

(def fifth (comp first rest rest rest rest)) ; composite function
(fifth [1 2 3 4 5])
;; => 5


(defn fnth [n]
  (apply comp
         (cons first
               (take (dec n) (repeat rest)))))
((fnth 4) '[a b c d e])
;; => d

(map (comp
         keyword
         #(.toLowerCase %)
         name)
        '(a B C))
;; => (:a :b :c)


((partial + 5) 100 200) ;partially applied function
;; => 305

((complement even?) 2); reverse the truthy return value of function
;; => false





