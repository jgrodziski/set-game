(ns set-game.examples)

(def actions #{:claim-set-on-board :no-set-on-board})
(def move-result #{:set-on-board-ok :set-on-board-ko
                   :no-set-on-board-ok :no-set-on-board-ko})

(def input-move-set-on-board-ok
  {:player "player1"
   :game-id #uuid "579091b6-1844-48b2-8b6b-e874fba9006f"
   :action [:set-on-board [[:oval :purple :3 :stripped]
                           [:squiggle :green :3 :solid]
                           [:diamond :red :3 :outline]]]})

(def input-move-with-failure
  {:player "player1"
   :game-id #uuid "579091b6-1844-48b2-8b6b-e874fba9006f"
   :action [:set-on-board [[:oval :purple :3 :stripped]
                           [:oval :green :2 :solid]
                           [:oval :red :3 :outline]]]})

(def input-move-not-set
  {:player "player1"
   :game-id #uuid "579091b6-1844-48b2-8b6b-e874fba9006f"
   :action [:no-set-on-board]})

(def output-move-set-on-board-ok
  {:result :set-on-board-ok
   :move {:player "player1"
          :action [:set-on-board  [[:oval :purple :3 :stripped]
                                   [:squiggle :green :3 :solid]
                                   [:diamond :red :3 :outline]]]}
   :game-id #uuid "579091b6-1844-48b2-8b6b-e874fba9006f"})


