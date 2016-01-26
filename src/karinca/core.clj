(ns karinca.core
  (:require
    [karinca.ants :as ants]
    [karinca.ui :as ui]))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (def ants (ants/setup))
  (send-off ui/animator ui/animation)
  (dorun (map #(send-off % ants/behave) ants))
  (send-off ants/evaporator ants/evaporation))
