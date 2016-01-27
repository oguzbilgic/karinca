(ns karinca.core
  (:require
    [karinca.ants :as ants]
    [karinca.web :as web]
    [karinca.ui :as ui]))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (def ants (ants/setup))
  (web/start-web)
  (send-off ui/animator ui/animation)
  (dorun (map #(send-off % ants/behave) ants))
  (send-off ants/evaporator ants/evaporation))
