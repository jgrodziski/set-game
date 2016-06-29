(ns set-game.core.cljc)

(def shapes  #{:oval :diamond :squiggle})
(def colors  #{:red :purple :green})
(def numbers #{:1 :2 :3})
(def shadings #{:solid :stripped :outline})

(def cards (-> (for [shape shapes
                     color colors
                     number numbers
                     shading shadings] [shape color number shading])
               shuffle
               set))

(def deck (take 12 cards))





