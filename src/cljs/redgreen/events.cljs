(ns redgreen.events
  (:require
    [re-frame.core :as re-frame]
    [redgreen.game :as game]))



(re-frame/reg-event-db
 ::set-active-panel
 (fn [db [_ active-panel]]
   (assoc db :active-panel active-panel)))


(re-frame/reg-event-db
 ::tour
 (fn [db _]
   (as-> db x
    (update-in x [:plateau] game/tour)
    (update-in x [:tour] inc))))


(re-frame/reg-event-db
  ::reset
  (fn [db [_ size]]
    (as-> db x
     (assoc-in x [:size] size)
     (assoc-in x [:plateau] (game/init-plateau size))
     (assoc-in x [:tour] 0))))


