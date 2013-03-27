(ns main.bike
  (:import (java.util Date Timer Random)))

(defn km-between [lat1 lng1 lat2 lng2]
  (println lat1 lng1 lat2 lng2)
  (let [radians (/ 180 3.14159)
        a1 (/ lat1 radians)
        a2 (/ lng1 radians)
        b1 (/ lat2 radians)
        b2 (/ lng2 radians)
        t1 (* (Math/cos a1) (Math/cos a2) (Math/cos b1) (Math/cos b2))
        t2 (* (Math/cos a1) (Math/sin a2) (Math/cos b1) (Math/sin b2))
        t3 (* (Math/sin a1) (Math/sin b1))]
    (* 6366 (Math/cos (+ t1 t2 t3)))))

;(defrecord City [name lat lng]
;  (km-to [this other]
;    (km-between (:lat this)  (:lng this)
;                (:lat other) (:lng other)))
;  (roads [this]
;    (filter #(or (= (first %1) this) (= (last %1) this))
;            @roads))
;  (adjacent [this]
;    (map #(.opposite % this) (.roads this)))
;  (toString [this]
;    (:name this)))
(def roads (ref #{}))

(defmacro city [name lat lng]
  `(def ~name (hash-map :name ~(str name) :lat ~lat :lng ~lng)))
(defmacro road [a b]
  (list 'let '[distance '(km-between (:lat ~a) (:lng ~b)
                              (:lat ~b) (:lng ~b))]
        '(println distance)))
;    `(dosync
;      (ref-set roads
;               (conj @roads
;                     (hash-map :a ~a :b ~b
;                               :distance distance))))))
(println (macroexpand '(city Rome 2.3 4.5)))
(city Berlin 2.3 4.5)
(city Rome 8.3 2.5)
(println (km-between (:lat Rome)   (:lng Rome)
                     (:lat Berlin) (:lng Berlin)))
(road Rome Berlin)
(println Berlin)
(println @roads)
;(def Berlin    (->City "Berlin"    52.482668 13.359275))
;(def Paris     (->City "Paris"     48.980405 2.2851849))
;(def Milan     (->City "Milan"     45.520543 9.1419459))
;(def Frankfurt (->City "Frankfurt" 50.078848 8.6349115))
;(def Munich    (->City "Munich"    48.166229 11.558089))
;(def Zurich    (->City "Zurich"    47.383444 8.5254142))
;(def Tours     (->City "Tours"     47.413572 0.6810506))
;(def Lyon      (->City "Lyon"      45.767122 4.8339568))
;(def Vienna    (->City "Vienna"    48.224431 16.389240))
;(def Prague    (->City "Prague"    50.092396 14.436144))
;(def Krakow    (->City "Krakow"    50.050363 19.928578))
;(def Warsaw    (->City "Warsaw"    52.254756 21.005968))
;(def Hamburg   (->City "Hamburg"   53.539699 9.9977143))
;(def Antwerp   (->City "Antwerp"   51.220613 4.3954882))
;(def Torino    (->City "Torino"    45.105321 7.6451957))
;(def Rome      (->City "Rome"      42.032845 12.390408))

;class Road
;
;  require 'set'
;  ALL = Set.new
;
;  attr_reader :a, :b, :distance
;
;  def initialize(a, b)
;    raise "Roads must have two cities" if a == b
;    @a = a
;    @b = b
;    @distance = a.kilometers_to(b)
;  end
;
;  def the_city_opposite(city)
;    raise "#{city} isn't connected to #{self}" unless a == city || b == city
;    city == a ? b : a
;  end
;
;  def to_s
;    "#{a} | #{b} (#{"%.0f" % distance} km)"
;  end
;
;  def self.between(a, b)
;    ALL.detect do |road|
;      return road if (road.a == a && road.b == b) || (road.a == b && road.b == a)
;    end
;    raise "There is no road between " + a.to_s + " and " + b.to_s
;  end
;
;  ALL.add new(City::Hamburg,   City::Berlin)
;  ALL.add new(City::Hamburg,   City::Antwerp)
;  ALL.add new(City::Hamburg,   City::Frankfurt)
;  ALL.add new(City::Berlin,    City::Warsaw)
;  ALL.add new(City::Berlin,    City::Prague)
;  ALL.add new(City::Antwerp,   City::Paris)
;  ALL.add new(City::Paris,     City::Tours)
;  ALL.add new(City::Paris,     City::Lyon)
;  ALL.add new(City::Paris,     City::Zurich)
;  ALL.add new(City::Paris,     City::Frankfurt)
;  ALL.add new(City::Frankfurt, City::Prague)
;  ALL.add new(City::Krakow,    City::Warsaw)
;  ALL.add new(City::Krakow,    City::Prague)
;  ALL.add new(City::Krakow,    City::Vienna)
;  ALL.add new(City::Vienna,    City::Munich)
;  ALL.add new(City::Vienna,    City::Prague)
;  ALL.add new(City::Zurich,    City::Milan)
;  ALL.add new(City::Lyon,      City::Torino)
;  ALL.add new(City::Torino,    City::Milan)
;  ALL.add new(City::Torino,    City::Rome)
;  ALL.add new(City::Milan,     City::Rome)
;end
;
;Start = City::Rome
;End   = City::Berlin
;
;result = [] # make a list of cities from Rome to Berlin
;
;total_distance = 0
;result.each_with_index do |city, idx|
;  print city
;  if next_city = result[idx+1]
;    distance = Road.between(city, next_city).distance
;    total_distance += distance
;    puts " -> #{"%.0f" % distance}"
;  end
;end
;print "arrived in #{result.size} steps "
;print "(#{"%.0f" % total_distance} km)"
;puts ""
;puts ""