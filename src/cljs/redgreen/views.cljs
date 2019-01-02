(ns redgreen.views
  (:require
   [re-frame.core :as re-frame]
   [redgreen.subs :as subs]
   [redgreen.events :as events]))



(defn verre-svg
  "Rendu d'un verre (plein ou vide) en svg"
  [^{:doc "Si full = 0, verre vide. Sinon, verre plein"} full]
  [:img {:src (case full
                0 "svg/verre_empty.svg"
                "svg/verre.svg")}])


(defn home-panel []
  (let [plateau (re-frame/subscribe [::subs/plateau])
        size (re-frame/subscribe [::subs/size])
        tour (re-frame/subscribe [::subs/tour])
        winning (re-frame/subscribe [::subs/iswinning])]
    [:div
     [:h1 (str "Click on tour to step into the game !")]
     [:h2 (str "Tour n°=" @tour)]
     (when @winning [:div.winning "You win !! "])
     (into [:div] (map #(verre-svg %) @plateau))
     [:span (when-not @winning [:button {:on-click #(re-frame/dispatch [::events/tour])} "Tour !"])
            [:button {:on-click #(re-frame/dispatch [::events/reset @size])} "Reset"]]
     [:div
      [:a {:href "#/about"}
       "go to About Page"]]]))


;; about

(defn about-panel []
  [:div
   [:h1 "Redgreen game aka Killer"]

   [:div
    [:a {:href "#/"}
     "go to Home Page"]]])


;; main

(defn- panels [panel-name]
  (case panel-name
    :home-panel [home-panel]
    :about-panel [about-panel]
    [:div]))

(defn show-panel [panel-name]
  [panels panel-name])

(defn main-panel []
  (let [active-panel (re-frame/subscribe [::subs/active-panel])]
    [show-panel @active-panel]))
