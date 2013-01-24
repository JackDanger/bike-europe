(ns main.bike
  (:import (java.util Date Timer Random)))

(defn km-between [lat1 lng1 lat2 lng2]
  (let [radians (/ 180 3.14159)
        a1 (/ lat1 radians) a2 (/ lng1 radians)
        b1 (/ lat2 radians) b2 (/ lng2 radians)
        t1 (* (Math/cos a1) (Math/cos a2) (Math/cos b1) (Math/cos b2))
        t2 (* (Math/cos a1) (Math/sin a2) (Math/cos b1) (Math/sin b2))
        t3 (* (Math/sin a1) (Math/sin b1))]
    (* 6366 (Math/cos (+ t1 t2 t3)))))

(def roads (ref #{}))

(defmacro city [name lat lng]
  `(def ~name (hash-map :name ~(str name) :lat ~lat :lng ~lng)))
(defmacro road [a b]
  (let [distance `(km-between (:lat ~a) (:lng ~a)
                              (:lat ~b) (:lng ~b))]
  (println "building road between" a "and" b)
  `(dosync
      (ref-set roads
               (conj @roads
                     (hash-map :a ~a :b ~b
                               :distance ~distance))))))
(city Berlin    52.482668 13.359275)
(city Paris     48.980405 2.2851849)
(city Milan     45.520543 9.1419459)
(city Frankfurt 50.078848 8.6349115)
(city Munich    48.166229 11.558089)
(city Zurich    47.383444 8.5254142)
(city Tours     47.413572 0.6810506)
(city Lyon      45.767122 4.8339568)
(city Vienna    48.224431 16.389240)
(city Prague    50.092396 14.436144)
(city Krakow    50.050363 19.928578)
(city Warsaw    52.254756 21.005968)
(city Hamburg   53.539699 9.9977143)
(city Antwerp   51.220613 4.3954882)
(city Torino    45.105321 7.6451957)
(city Rome      42.032845 12.390408)

(defn opposite [road city]
  "Find the city on the other end of the road from the city provided"
  (if (= city (:a road)) (:b road) (:a road)))

(defn between [city_a city_b]
  "Find the road that connects two cities"
  (some #(if (or (and (= (:a %) city_a) (= (:b %) city_b))
                 (and (= (:b %) city_a) (= (:a %) city_b))) %) @roads))

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
(road Hamburg   Berlin)
(road Hamburg   Antwerp)
(road Hamburg   Frankfurt)
(road Berlin    Warsaw)
(road Berlin    Prague)
(road Antwerp   Paris)
(road Paris     Tours)
(road Paris     Lyon)
(road Paris     Zurich)
(road Paris     Frankfurt)
(road Frankfurt Prague)
(road Krakow    Warsaw)
(road Krakow    Prague)
(road Krakow    Vienna)
(road Vienna    Munich)
(road Vienna    Prague)
(road Zurich    Milan)
(road Lyon      Torino)
(road Torino    Milan)
(road Torino    Rome)
(road Milan     Rome)
(println (between Milan Rome))
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

