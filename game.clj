(ns game)

(defn context []
  (let [target (.getElementById js/document "target")]
    [
      (.getContext target "2d") 
      (. target -width)
      (. target -height)
    ]
  )
)

(defn clearScreen [[ctx width height]]
  (set! (. ctx -fillStyle) "#FFF")
  (.clearRect ctx 0 0 width height) 
)

(defn drawSquare [[ctx width height] x y w h]
  (set! (. ctx -fillStyle) "#000")
  (.fillRect ctx x y w h) 
)


(defn tick [frame]
  (let [ctx (context)] 
    (clearScreen ctx) 
    (drawSquare ctx frame 0 100 100)
  )
)


(defn slowMap [f coll delay]
  (when (seq coll)
    (f (first coll))
    (js/setTimeout #(slowMap f (rest coll) delay) delay)
  )
)

(defn ^:export init []
  (slowMap #(tick %1) (range 1000) 33)
)
