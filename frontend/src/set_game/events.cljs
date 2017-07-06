(ns set-game.events
  (:require
    [re-frame.core :refer [reg-event-db reg-event-fx after]]
    [clojure.spec.alpha :as s]
    [set-game.db :as db :refer [app-db]]))

;; -- Interceptors ------------------------------------------------------------
;;
;; See https://github.com/Day8/re-frame/blob/master/docs/Interceptors.md
;;
(defn check-and-throw
  "Throw an exception if db doesn't have a valid spec."
  [spec db [event]]
  (when-not (s/valid? spec db)
    (let [explain-data (s/explain-data spec db)]
      (throw (ex-info (str "Spec check after " event " failed: " explain-data) explain-data)))))

(def validate-spec
  (if goog.DEBUG
    (after (partial check-and-throw ::db/app-db))
    []))

;; -- Handlers --------------------------------------------------------------

(reg-event-db
  :initialize-db
  validate-spec
  (fn [_ _]
    app-db))

(reg-event-db
  :set-greeting
  validate-spec
  (fn [db [_ value]]
    (assoc db :greeting value)))


(reg-event-db
  :card-clicked
  (fn [db [_ card]]
    (let [board        (get db :board)
          selected     (filter #(let [[k v] %] v) board)
          db-after-sel (update-in db [:board card] not)
          new-selected (filter #(let [[k v] %] v) (get db-after-sel :board))]
      (if (> (count new-selected) 3)
        db
        db-after-sel))))

(reg-event-db
  :my-event
  (fn [db [_ value]]
    (assoc db :message value)))

(reg-event-fx
  :submit-pressed
  (fn [{:keys [db]} _]
    (js/alert "Submitted")))