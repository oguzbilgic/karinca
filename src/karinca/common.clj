(ns karinca.common
  (:use [karinca.constants :only [cell dim]]))

;world is a 2d vector of refs to cells
(def world
     (apply vector
            (map (fn [_]
                   (apply vector (map (fn [_] (ref (struct cell 0 0)))
                                      (range dim))))
                 (range dim))))

(defn place [[x y]]
  (-> world (nth x) (nth y)))
