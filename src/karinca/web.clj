(ns karinca.web
  (:use [karinca.constants :only [dim food-scale pher-scale]]
        [karinca.world :only [place]]
        [hiccup.core :only [html]]
        [org.httpkit.server :only [run-server]]))

(defn cell-style [p]
  (let [color (cond
                (pos? (:food p)) (str "rgba(255,0,0," (/ (:food p) food-scale) ")")
                (pos? (:pher p)) (str "rgba(0,255,0," (/ (:pher p) pher-scale) ")")
                (:ant p) "gray")]
    (str "background-color: " color ";width: 10px;height: 10px;display: inline-block;")))

(defn render-cell [p]
  [:div {:style (cell-style p)}])

(defn render-row [y]
  (for [x (range dim)]
    (render-cell @(place [x y]))))

(defn render-world []
  (html (for [y (range dim)]
          [:div (render-row y)])))

(defn handler [request]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (render-world)})

(defn start-web []
  (run-server handler {:port 3000}))
