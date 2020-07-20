
(ns com.clojuretryout.domainmodelling.trp
  (:import com.clojuretryout.domainmodelling.Customer
           com.clojuretryout.domainmodelling.Address))
;defreocrds

(defrecord TreeNode [val left right])
;; => user.TreeNode

(def leftNode (TreeNode. 25 nil nil))
(def rightNode (TreeNode. 12 nil nil))

(def tnode (TreeNode. 23 leftNode rightNode))
;; => {:val 23, :left 1, :right 2}

;access val property of tnode TreeNode instance
(:val tnode)
;; => 23
(:left tnode)
;; => {:val 25, :left nil, :right nil}
(:right tnode)
;; => {:val 12, :left nil, :right nil}

(defn xconj [t v]
  (cond
    (nil? t) (TreeNode. v nil nil)
    (< v (:val t)) (TreeNode. (:val t) (xconj (:l t) v) (:r t))
    :else (TreeNode. (:val t) (:l t) (xconj (:r t) v))))

(defn xseq [t]
  (when t
    (concat (xseq (:l t)) [(:val t)] (xseq (:r t)))))
(def sample-tree
  (reduce xconj nil [3 5 2 4 6]))
(xseq sample-tree)
;; => (3)



;create an instance of record type imported from another work space
(def terminator1 (Customer. "Arny" "T2" "AT2@skynet.com" 111110011 :CORP
                            (Address. "Hasta La Vista Road" "LA" "CA" 345612)))
;; => #'com.clojuretryout.domainmodelling/terminator1

(:fname terminator1)
;; => "Arny"

;protocols

(defprotocol NPrinter
  (myprint [n])
  ) ;define protocol/ interface type with abstract method


(extend-type TreeNode ; add created protocol method to create mixin type
  NPrinter
  (myprint [n]
    (println "This:"  (:val n))
    (println "left Node:" (-> n :left :val))
    (println "right Node:" (-> n :right :val))))

(myprint tnode)
;;This: 23
;;left Node: 25
;;right Node: 12


;deftype example which do not implement any other methods
;but only implements Iseq.seq method

(deftype InfiniteConstant [i]
  clojure.lang.ISeq
  (seq [this]
    (lazy-seq (cons i (seq this)))))
;; => com.clojuretryout.domainmodelling.trp.InfiniteConstant


(take 3 (InfiniteConstant. 5))
;; => (5 5 5)























