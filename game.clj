(ns game)

(defn renderTarget []
  (let [target (.getElementById js/document "target")]
    {
      :context (.getContext target "2d") 
      :width (. target -width)
      :height (. target -height)
    }
  )
)

(defn clearScreen [rt]
  (set! (. (:context rt) -fillStyle) "#FFF")
  (.clearRect (:context rt) 0 0 (:width rt) (:height rt))
)

(defn drawSquare [rt x y w h]
  (set! (. (:context rt) -fillStyle) "#000")
  (.fillRect (:context rt) x y w h) 
)


(defn tick [x]
  (let [rt (renderTarget)] 
    (clearScreen rt) 
    (drawSquare rt x 0 100 100)  
    (if (<= x 1000) 
      (js/setTimeout (fn []
        (tick (inc x)) 
      ) 33  )
    )
  )
)



(defn ^:export init []
  (tick 0) 
)
