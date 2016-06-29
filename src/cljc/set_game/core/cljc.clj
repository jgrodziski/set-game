(ns set-game.core.cljc
  (:require [clojure.math.combinatorics :as combo]))

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

(defn all-identical? [p1 p2 p3]
  (= p1 p2 p3))

(defn all-different? [p1 p2 p3]
  (= (count (set [p1 p2 p3])) 3))

(defn set-of-cards? [[[c1-shape c1-color c1-number c1-shading]
                      [c2-shape c2-color c2-number c2-shading]
                      [c3-shape c3-color c3-number c3-shading]]]
  (and
    (or (all-different? c1-shape c2-shape c3-shape) (all-identical? c1-shape c2-shape c3-shape))
    (or (all-different? c1-color c2-color c3-color) (all-identical? c1-color c2-color c3-color))
    (or (all-different? c1-number c2-number c3-number) (all-identical? c1-number c2-number c3-number))
    (or (all-different? c1-shading c2-shading c3-shading) (all-identical? c1-shading c2-shading c3-shading))))

(defn solution []
  (filter set-of-cards? (combo/combinations deck 3)))



