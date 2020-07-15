


(in-ns 'joy.ns)
(def authors ["Chouser"])

(in-ns 'your.ns)
(clojure.core/refer 'joy.ns)
joy.ns/authors
;; => ["Chouser"]

(in-ns 'joy.ns)
(def authors ["Chouser" "Fogus"])

(in-ns 'your.ns)
joy.ns/authors
;; => ["Chouser" "Fogus"]


(clojure.core/refer 'clojure.core)
(def b (create-ns 'bonobo))
;; => #'user/b


((ns-map b) 'String)
;; => java.lang.String

(intern b 'x 9)
;; => #'bonobo/x

(intern b 'reduce clojure.core/reduce)
;; => #'bonobo/reduce

(in-ns 'tempns)
(def a 5)
(clojure.core/refer 'clojure.core)
(ns-unmap 'tempns 'a)
;; => nil

(remove-ns 'tempns)
;; => nil


;list all namespaces
;(all-ns)