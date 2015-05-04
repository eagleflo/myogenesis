(ns myogenesis.core
  (:require [reagent.core :as reagent :refer [atom]]))

(enable-console-print!)
(println "Hello, myogenesis!")

;; Workout is a map of exercises to a vector of sets.
;; Starting Strength workout A:
(defonce workout (atom {"Squat" [{:weight 20 :reps 5}
                                 {:weight 20 :reps 5}
                                 {:weight 40 :reps 5}
                                 {:weight 60 :reps 3}
                                 {:weight 80 :reps 2}
                                 {:weight 100 :reps 5}
                                 {:weight 100 :reps 5}
                                 {:weight 100 :reps 5}]
                        "Bench Press" [{:weight 20 :reps 5}
                                       {:weight 20 :reps 5}
                                       {:weight 40 :reps 5}
                                       {:weight 55 :reps 3}
                                       {:weight 70 :reps 2}
                                       {:weight 80 :reps 5}
                                       {:weight 80 :reps 5}
                                       {:weight 80 :reps 5}]
                        "Deadlift" [{:weight 50 :reps 5}
                                    {:weight 50 :reps 5}
                                    {:weight 75 :reps 3}
                                    {:weight 100 :reps 2}
                                    {:weight 120 :reps 5}]}))

(defonce exercises ["Squat" "Press" "Deadlift" "Bench Press" "Power Clean"])
(defonce current-exercise (atom "Squat"))
(defonce weight {:name "Weight" :data (atom nil)})
(defonce reps {:name "Reps" :data (atom nil)})

(defn select-exercise [exercise]
  [:div {:class (str "button" (if (= @current-exercise exercise) " selected"))
         :on-click #(reset! current-exercise exercise)} exercise])

(defn input-row [{:keys [name data]}]
  [:div
   [:label {:for name} name]
   [:input {:id name
            :type "number"
            :on-change #(reset! data (-> % .-target .-value))}]])

(defn add-set! [exercise weight reps]
  (swap! workout #(update-in % [exercise] concat [{:weight @weight :reps @reps}])))

(defn set-form [exercise]
  [:div#add-set
   [input-row weight]
   [input-row reps]
   [:input {:type "button" :value "Enter"
            :on-click #(add-set! exercise (:data weight) (:data reps))}]])

(defn setlist [move sets]
  [:div.setlist
   [:p move]
   [:ol
    (for [[idx {:keys [weight reps]}] (map-indexed vector sets)]
      ^{:key (str move idx)} [:li (str weight " kg x " reps)])]])

(defn app []
  [:div
   [:h1 "Myogenesis"]
   [:div#exercises
    (for [exercise exercises]
      ^{:key (str "add-" exercise)} [select-exercise exercise])]
   [set-form @current-exercise]
   [:div#setlists
    (for [[move sets] @workout]
      ^{:key move} [setlist move sets])]])

(reagent/render [app] (.-body js/document))
