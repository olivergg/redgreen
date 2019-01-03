(ns redgreen.views
  (:require
   [re-frame.core :as re-frame :refer [dispatch subscribe] :rename {dispatch >evt subscribe <sub}]
   [redgreen.subs :as subs]
   [redgreen.events :as events]))

(defn verre-svg
  "SVG render of a glass (full or empty) "
  [full]
  [:img {:src (case full
                0 "svg/verre_empty.svg"
                "svg/verre.svg")}])

(defn board-panel
  "Main page that contains the board"
  []
  (let [plateau (<sub [::subs/plateau])
        size (<sub [::subs/size])
        tour (<sub [::subs/tour])
        winning (<sub [::subs/iswinning])]
    [:div
     [:h1 "Click on tour to step into the game !"]
     [:h2 (str "Tour n°=" @tour)]
     (when @winning [:div.winning "You win !! "])
     (into [:div] (map #(verre-svg %) @plateau))
     [:span (when-not @winning [:button {:on-click #(>evt [::events/tour])} "Tour !"])
            [:button {:on-click #(>evt [::events/reset @size])} "Reset"]]
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
    :home-panel [board-panel]
    :about-panel [about-panel]
    [:div]))

(defn show-panel [panel-name]
  [panels panel-name])

(defn main-panel []
  (let [active-panel (re-frame/subscribe [::subs/active-panel])]
    [show-panel @active-panel]))
