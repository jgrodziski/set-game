(ns set-game.logic
  (:require [clojure.math.combinatorics :refer [combinations]]))

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

(defn deck []
  (take 12 cards))

(defn identical-or-distinct? [p1 p2 p3]
  (not= (count (set [p1 p2 p3])) 2))

(defn set-of-cards? [[[c1-shape c1-color c1-number c1-shading]
                      [c2-shape c2-color c2-number c2-shading]
                      [c3-shape c3-color c3-number c3-shading]]]
  (and
    (identical-or-distinct? c1-shape   c2-shape   c3-shape  )
    (identical-or-distinct? c1-color   c2-color   c3-color  )
    (identical-or-distinct? c1-number  c2-number  c3-number )
    (identical-or-distinct? c1-shading c2-shading c3-shading)))

(defn SETs [deck]
  (filter set-of-cards? (combinations deck 3)))