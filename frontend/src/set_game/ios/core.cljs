(ns set-game.ios.core
  (:require [reagent.core :as r :refer [atom]]
            [re-frame.core :refer [subscribe dispatch dispatch-sync]]
            [set-game.events]
            [set-game.subs]))

(def ReactNative (js/require "react-native"))

(def flat-list (r/adapt-react-class (.-FlatList ReactNative)))

(def app-registry (.-AppRegistry ReactNative))
(def text (r/adapt-react-class (.-Text ReactNative)))
(def view (r/adapt-react-class (.-View ReactNative)))
(def image (r/adapt-react-class (.-Image ReactNative)))
(def touchable-highlight (r/adapt-react-class (.-TouchableHighlight ReactNative)))
(def button (r/adapt-react-class (.-Button ReactNative)))

(def logo-img (js/require "./images/clojure-paris.png"))

(defn alert [title]
  (.alert (.-Alert ReactNative) title))


(defn load-image [card]
  (println "card" card)
  (condp = card
    [:diamond :green :1 :outlined] (js/require "./images/diamond-green-1-outlined.png")
    [:diamond :green :1 :solid] (js/require "./images/diamond-green-1-solid.png")
    [:diamond :green :1 :stripped] (js/require "./images/diamond-green-1-stripped.png")
    [:diamond :green :2 :outline] (js/require "./images/diamond-green-2-outline.png")
    [:diamond :green :2 :solid] (js/require "./images/diamond-green-2-solid.png")
    [:diamond :green :2 :stripped] (js/require "./images/diamond-green-2-stripped.png")
    [:diamond :green :3 :outline] (js/require "./images/diamond-green-3-outline.png")
    [:diamond :green :3 :solid] (js/require "./images/diamond-green-3-solid.png")
    [:diamond :green :3 :stripped] (js/require "./images/diamond-green-3-stripped.png")
    [:diamond :purple :1 :outline] (js/require "./images/diamond-purple-1-outline.png")
    [:diamond :purple :1 :solid] (js/require "./images/diamond-purple-1-solid.png")
    [:diamond :purple :1 :stripped] (js/require "./images/diamond-purple-1-stripped.png")
    [:diamond :purple :2 :outline] (js/require "./images/diamond-purple-2-outline.png")
    [:diamond :purple :2 :solid] (js/require "./images/diamond-purple-2-solid.png")
    [:diamond :purple :2 :stripped] (js/require "./images/diamond-purple-2-stripped.png")
    [:diamond :purple :3 :outline] (js/require "./images/diamond-purple-3-outline.png")
    [:diamond :purple :3 :solid] (js/require "./images/diamond-purple-3-solid.png")
    [:diamond :purple :3 :stripped] (js/require "./images/diamond-purple-3-stripped.png")
    [:diamond :red :1 :outline] (js/require "./images/diamond-red-1-outline.png")
    [:diamond :red :1 :outlined] (js/require "./images/diamond-red-1-outlined.png")
    [:diamond :red :1 :solid] (js/require "./images/diamond-red-1-solid.png")
    [:diamond :red :1 :stripped] (js/require "./images/diamond-red-1-stripped.png")
    [:diamond :red :2 :outline] (js/require "./images/diamond-red-2-outline.png")
    [:diamond :red :2 :outlined] (js/require "./images/diamond-red-2-outlined.png")
    [:diamond :red :2 :solid] (js/require "./images/diamond-red-2-solid.png")
    [:diamond :red :2 :stripped] (js/require "./images/diamond-red-2-stripped.png")
    [:diamond :red :3 :outline] (js/require "./images/diamond-red-3-outline.png")
    [:diamond :red :3 :outlined] (js/require "./images/diamond-red-3-outlined.png")
    [:diamond :red :3 :solid] (js/require "./images/diamond-red-3-solid.png")
    [:diamond :red :3 :stripped] (js/require "./images/diamond-red-3-stripped.png")
    [:oval :green :1 :outline] (js/require "./images/oval-green-1-outline.png")
    [:oval :green :1 :solid] (js/require "./images/oval-green-1-solid.png")
    [:oval :green :1 :stripped] (js/require "./images/oval-green-1-stripped.png")
    [:oval :green :2 :outlined] (js/require "./images/oval-green-2-outlined.png")
    [:oval :green :2 :solid] (js/require "./images/oval-green-2-solid.png")
    [:oval :green :2 :stripped] (js/require "./images/oval-green-2-stripped.png")
    [:oval :green :3 :outlined] (js/require "./images/oval-green-3-outlined.png")
    [:oval :green :3 :solid] (js/require "./images/oval-green-3-solid.png")
    [:oval :green :3 :stripped] (js/require "./images/oval-green-3-stripped.png")
    [:oval :purple :1 :outlined] (js/require "./images/oval-purple-1-outlined.png")
    [:oval :purple :1 :solid] (js/require "./images/oval-purple-1-solid.png")
    [:oval :purple :1 :stripped] (js/require "./images/oval-purple-1-stripped.png")
    [:oval :purple :2 :outlined] (js/require "./images/oval-purple-2-outlined.png")
    [:oval :purple :2 :solid] (js/require "./images/oval-purple-2-solid.png")
    [:oval :purple :2 :stripped] (js/require "./images/oval-purple-2-stripped.png")
    [:oval :purple :3 :outlined] (js/require "./images/oval-purple-3-outlined.png")
    [:oval :purple :3 :solid] (js/require "./images/oval-purple-3-solid.png")
    [:oval :purple :3 :stripped] (js/require "./images/oval-purple-3-stripped.png")
    [:oval :red :1 :outline] (js/require "./images/oval-red-1-outline.png")
    [:oval :red :1 :solid] (js/require "./images/oval-red-1-solid.png")
    [:oval :red :1 :stripped] (js/require "./images/oval-red-1-stripped.png")
    [:oval :red :2 :outline] (js/require "./images/oval-red-2-outline.png")
    [:oval :red :2 :solid] (js/require "./images/oval-red-2-solid.png")
    [:oval :red :2 :stripped] (js/require "./images/oval-red-2-stripped.png")
    [:oval :red :3 :outline] (js/require "./images/oval-red-3-outline.png")
    [:oval :red :3 :solid] (js/require "./images/oval-red-3-solid.png")
    [:oval :red :3 :stripped] (js/require "./images/oval-red-3-stripped.png")
    [:squiggle :green :1 :outline] (js/require "./images/squiggle-green-1-outline.png")
    [:squiggle :green :1 :solid] (js/require "./images/squiggle-green-1-solid.png")
    [:squiggle :green :1 :stripped] (js/require "./images/squiggle-green-1-stripped.png")
    [:squiggle :green :2 :outline] (js/require "./images/squiggle-green-2-outline.png")
    [:squiggle :green :2 :solid] (js/require "./images/squiggle-green-2-solid.png")
    [:squiggle :green :2 :stripped] (js/require "./images/squiggle-green-2-stripped.png")
    [:squiggle :green :3 :outline] (js/require "./images/squiggle-green-3-outline.png")
    [:squiggle :green :3 :solid] (js/require "./images/squiggle-green-3-solid.png")
    [:squiggle :green :3 :stripped] (js/require "./images/squiggle-green-3-stripped.png")
    [:squiggle :purple :1 :outline] (js/require "./images/squiggle-purple-1-outline.png")
    [:squiggle :purple :1 :solid] (js/require "./images/squiggle-purple-1-solid.png")
    [:squiggle :purple :1 :stripped] (js/require "./images/squiggle-purple-1-stripped.png")
    [:squiggle :purple :2 :outline] (js/require "./images/squiggle-purple-2-outline.png")
    [:squiggle :purple :2 :solid] (js/require "./images/squiggle-purple-2-solid.png")
    [:squiggle :purple :2 :stripped] (js/require "./images/squiggle-purple-2-stripped.png")
    [:squiggle :purple :3 :outline] (js/require "./images/squiggle-purple-3-outline.png")
    [:squiggle :purple :3 :solid] (js/require "./images/squiggle-purple-3-solid.png")
    [:squiggle :purple :3 :stripped] (js/require "./images/squiggle-purple-3-stripped.png")
    [:squiggle :red :1 :outline] (js/require "./images/squiggle-red-1-outline.png")
    [:squiggle :red :1 :solid] (js/require "./images/squiggle-red-1-solid.png")
    [:squiggle :red :1 :stripped] (js/require "./images/squiggle-red-1-stripped.png")
    [:squiggle :red :2 :outline] (js/require "./images/squiggle-red-2-outline.png")
    [:squiggle :red :2 :solid] (js/require "./images/squiggle-red-2-solid.png")
    [:squiggle :red :2 :stripped] (js/require "./images/squiggle-red-2-stripped.png")
    [:squiggle :red :3 :outline] (js/require "./images/squiggle-red-3-outline.png")
    [:squiggle :red :3 :solid] (js/require "./images/squiggle-red-3-solid.png")
    [:squiggle :red :3 :stripped] (js/require "./images/squiggle-red-3-stripped.png")
    (js/require "./images/unknown.png"))
  )


(defn card-view [card]
  (let [board (subscribe [:get-board])]
    [touchable-highlight {:style {:background-color (if (get @board card) "lightblue" "white") :padding 10 :border-radius 5}
                          :on-press #(dispatch [:card-clicked card])}
     [image {:source (load-image card)}]]))



(defn card-row [entry]
  (let [{:keys [item index]} entry]
    [card-view (map keyword item)]))


(defn board-view []
  (let [board (subscribe [:get-board])]
    [view {:style {:flex-direction "row"}}
     [flat-list {:numColumns 3
                 :horizontal false
                 :data (keys @board)
                 :removeClippedSubviews false
                 :keyExtractor (fn [item index] index)
                 :renderItem (fn [entry]
                               (r/as-element [card-row (js->clj entry :keywordize-keys true)]))}]]))


(defn app-root []
  (let [greeting (subscribe [:get-greeting])]
    (fn []
      [view {:style {:flex-direction "column" :margin 40 :align-items "center"}}
       [image {:source logo-img
               :style {:width 350 :height 60 :margin-bottom 30}}]
       [text {:style {:font-size 30
                      :font-family "American Typewriter"
                      :font-weight "100"
                      :margin-bottom 20 :text-align "center"}} @greeting]

       [board-view]
       [button {:title "Submit"
                :on-press #(dispatch [:submit-pressed])}]

       ])))

(defn init []
  (dispatch-sync [:initialize-db])
  (.registerComponent app-registry "SetGame" #(r/reactify-component app-root)))
