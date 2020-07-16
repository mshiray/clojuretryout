;define namespace
(ns com.clojuretryout.domainmodelling)

;define enum type with keyword
{:RETAIL 0 , :CORP 1}

;defining composite data types as records
;TODO: Move it to separate file
(defrecord Address [street city state zip])
;; => com.clojuretryout.domainmodelling.Address

(defrecord Customer [fname lname email phone type address])
;; => com.clojuretryout.domainmodelling.Customer


;create an instance of record
(def terminator (Customer. "Arny" "T2" "AT2@skynet.com" 111110011 :CORP
                  (Address. "Hasta La Vista Road" "LA" "CA" 345612)))

;Define fucntion accepting the defined type record
(defn printCustomer [customer]
  (println (:fname customer) ""
           (:lname customer) "#"
           (:phone customer) "#"
           (:type customer)
           (-> customer :address :zip)) ; note the -> & placement of dto ref
  )
;invoke function with isntance of record type
(printCustomer terminator)





