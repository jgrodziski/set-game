(ns set-game.core
  (:require [set-game.logic :as logic]))

(defn initial-game-state
  [players, deck]
  {:players (map (fn [player] {:name player, :score 0}) players)
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

(defn start-game! [players] (draw-cards (initial-game-state players (shuffle logic/cards)) 12))



