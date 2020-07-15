

; -> macro
;
; 1 Take the value 25.
;2 Feed it into the method Math/sqrt.
;3 Feed that result into the function int.
;4 Feed that result into the function list.

(-> 25 Math/sqrt int list)
;; => (5)

;eval
(eval (+ 21 3))
;; => 24

; eval with symbol
; symbol receives a string + and returns a symbol data type of +.
(eval (list (symbol "+") 1 2))


;use the interesting `'~v pattern to garner the value of the built
;bindings at runtime.
(defn contextual-eval [ctx expr]
  (eval
   `(let [~@(mapcat (fn [[k v]] [k `'~v]) ctx)]
      ~expr)))

(contextual-eval '{a 1, b 2} '(+ a b))
;; => 3

(contextual-eval '{a 1, b 2} '(let [b 1000] (+ a b)))
;; => 1001


;custom macro definition
(defmacro do-until [& clauses]
(when clauses
(list 'clojure.core/when (first clauses)
(if (next clauses)
  (second clauses)
(throw (IllegalArgumentException.
"do-until requires an even number of forms")))
(cons 'do-until (nnext clauses)))))
;; => #'user/do-until


;use macroexpand-1 to verify macro definition evaluations
(macroexpand-1 '(do-until true (prn 1) false (prn 2)))
;; => (do-until true (prn 1) false (prn 2))

;use macroexpand-all
(require '[clojure.walk :as walk])
(walk/macroexpand-all '(do-until true (prn 1) false (prn 2)))
;; => (if true (do (prn 1) (if false (do (prn 2) nil))))



(defmacro unless [condition & body]
  `(if (not ~condition) ; unquote condition
     (do ~@body))) ;splice the body

(unless true "nay!")
;; => nil
(unless false "aye!")
;; => "aye!"

(walk/macroexpand-all (unless true "eval"))
;; => nil








