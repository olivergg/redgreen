(ns redgreen.subs
  (:require
   [re-frame.core :as re-frame]))


(re-frame/reg-sub
 ::plateau
 (fn [db] (:plateau db)))

(re-frame/reg-sub
  ::iswinning
  (fn  [db] (= 0 (reduce + (:plateau db)))))

(re-frame/reg-sub
  ::size
  (fn [db] (:size db)))

(re-frame/reg-sub
  ::tour
  (fn [db] (:tour db)))



(re-frame/reg-sub
 ::active-panel
 (fn [db _]
   (:active-panel db)))
