(ns set-game.core
  (:require [set-game.logic :as logic]))

(defn initial-game-state
  [players, deck]
  {:players (map (fn [player] {:name player, :score 0}) players)
   :deck deck;sequence
   :board #{};this is a set, because order has no meaning
   }  )


(defn draw-cards-from-deck
  [state, number-of-cards]
  (let [[board deck] (split-at number-of-cards (:deck state))]
    (-> state
        (assoc :board (into (:board state) board))
        (assoc :deck deck))))


(defn start-game! [players] (draw-cards (initial-game-state players (shuffle logic/cards)) 12))

(defn score [state player set-of-cards]
  state)

(defn remove-set-of-cards-from-board [state set-of-cards])

(defn claim [state player set-of-cards]
  (let [set? (logic/set-of-cards? set-of-cards)]
    (if set?
      (-> state
          (remove-set-of-cards-from-board set-of-cards)
          (draw-cards-from-deck 3)
          (score player set-of-cards))
      )))
      ))
  )

