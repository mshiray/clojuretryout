(ns clojuretryout.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!" args))

(defn sayHello [name]
   (println "Hello " name)
  )
