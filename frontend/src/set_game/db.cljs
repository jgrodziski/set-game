(ns set-game.db
  (:require [clojure.spec.alpha :as s]))

;; spec of app-db
(s/def ::greeting string?)
(s/def ::app-db
  (s/keys :req-un [::greeting]))

;; initial state of app-db
(def app-db {:greeting "SET Game"
             :board (into {} (zipmap (take 12 (repeatedly #(rand-nth set-game.subs/all-cards)))
                               (take 12 (repeatedly #(identity false)))))})
