(ns redgreen.core
  (:require
   [reagent.core :as reagent]
   [re-frame.core :as re-frame]
   [redgreen.events :as events]
   [redgreen.routes :as routes]
   [redgreen.views :as views]
   [redgreen.config :as config]))



(defn dev-setup []
  (when config/debug?
    (enable-console-print!)
    (println "dev mode")))

(defn mount-root []
  (re-frame/clear-subscription-cache!)
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn ^:export init []
  (routes/app-routes)
  (re-frame/dispatch-sync [::events/reset 6])
  (dev-setup)
  (mount-root))
