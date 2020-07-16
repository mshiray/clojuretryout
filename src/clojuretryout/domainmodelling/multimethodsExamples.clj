

;UDP: Universal Design Pattern


;Prototyping maps
(ns joy.udp
  (:refer-clojure :exclude [get]))

(defn beget [this proto]
  (assoc this ::prototype proto))

(beget {:sub 0} {:super 1})

;; => {:sub 0, :joy.udp/prototype {:super 1}}


(defn get [m k]
  (when m
    (if-let [[_ v] (find m k)]
      v
      (recur (::prototype m) k))))

(get (beget {:sub 0} {:super 23}) :super)
;; => 23

; example use case
(def catt {:likes-dogs true, :ocd-bathing true})
(def morris (beget {:likes-9lives true} catt))
(def post-traumatic-morris (beget {:likes-dogs nil} morris))

(get catt :likes-dogs)
;; => true

(get morris :likes-dogs)
;; => true

(get post-traumatic-morris :likes-dogs)
;; => nil

(get post-traumatic-morris :likes-9lives)
;; => true

(def put assoc)

;multimethod example with prototype map

;multimethod named compiler
(defmulti compiler :os)
(defmethod compiler ::unix [m] (get m :c-compiler))
(defmethod compiler ::osx [m] (get m :llvm-compiler))

;define prototype map for unix & osx OSs
(def clone (partial beget {}))

(def unix {:os ::unix, :c-compiler "cc", :home "/home", :dev "/dev"})

(def osx (-> (clone unix)
             (put :os ::osx)
             (put :llvm-compiler "clang")
             (put :home "/Users")))

;call multimethod with proto maps
(compiler unix)
;; => "cc"

(compiler osx)
;; => "clang"

(defmulti home :os)
(defmethod home ::unix [m] (get m :home))

(home unix)
;; => "/home"

;derive/extend osx from unix to have :home in it
(derive ::osx ::unix)
;; => nil

;if diamond problem encountered, preferred method can be specified
(prefer-method home ::unix ::bsd)
;; => #multifn[home 0x23173c15]

(home osx)
;; => "/Users"

;Query the derivation heirarchy

(parents ::osx)
;; => #{:joy.udp/unix}


(ancestors ::osx)
;; => #{:joy.udp/unix}


(descendants ::unix)
;; => #{:joy.udp/osx}


(isa? ::osx ::unix)
;; => true


(isa? ::unix ::osx)
;; => false
