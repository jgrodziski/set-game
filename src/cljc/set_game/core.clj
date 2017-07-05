(ns set-game.core
  (:require [set-game.logic :as logic]))

(defn initial-game-state
  [deck, players]
  {:players (reduce (fn [acc player] (assoc acc player 0)) {} players)
   :deck deck
   :board []
   }  )


(defn draw-cards
  [state, number-of-cards]
  {
   :players (:players state)
   :deck (drop number-of-cards (:deck state))
   :board (into (:board state) (take number-of-cards (:deck state)))
  } )

(defn start-game! [players]
  (-> (shuffle logic/cards)
   (initial-game-state players) 
   (draw-cards 12)))



