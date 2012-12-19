module Util
  RADIANS = 180/3.14169
  def self.kilometers_between(lat1, lng1, lat2, lng2)
    a1 = lat1 / RADIANS
    a2 = lng1 / RADIANS
    b1 = lat2 / RADIANS
    b2 = lng2 / RADIANS

    t1 = Math.cos(a1) * Math.cos(a2) * Math.cos(b1) * Math.cos(b2)
    t2 = Math.cos(a1) * Math.sin(a2) * Math.cos(b1) * Math.sin(b2)
    t3 = Math.sin(a1) * Math.sin(b1)

    6366 * Math.acos(t1 + t2 + t3)
  end
end

class City

  attr_reader :lat, :lng

  def initialize(name, lat, lng)
    @name, @lat, @lng = name, lat, lng
  end

  Berlin    = new('Berlin',    52.482668, 13.359275)
  Paris     = new('Paris',     48.980405, 2.2851849)
  Milan     = new('Milan',     45.520543, 9.1419459)
  Frankfurt = new('Frankfurt', 50.078848, 8.6349115)
  Munich    = new('Munich',    48.166229, 11.558089)
  Zurich    = new('Zurich',    47.383444, 8.5254142)
  Tours     = new('Tours',     47.413572, 0.6810506)
  Lyon      = new('Lyon',      45.767122, 4.8339568)
  Vienna    = new('Vienna',    48.224431, 16.389240)
  Prague    = new('Prague',    50.092396, 14.436144)
  Krakow    = new('Krakow',    50.050363, 19.928578)
  Warsaw    = new('Warsaw',    52.254756, 21.005968)
  Hamburg   = new('Hamburg',   53.539699, 9.9977143)
  Antwerp   = new('Antwerp',   51.220613, 4.3954882)
  Torino    = new('Torino',    45.105321, 7.6451957)
  Rome      = new('Rome',      42.032845, 12.390408)

  def kilometers_to(other)
    Util.kilometers_between(lat, lng, other.lat, other.lng)
  end

  def roads
    @roads ||= Road::ALL.select {|road| road.a == self || road.b == self }
  end

  def adjacent_cities
    roads.map {|road| road.the_city_opposite(self) }
  end

  def to_s
    @name
  end
end

class Road

  require 'set'
  ALL = Set.new

  attr_reader :a, :b, :distance

  def initialize(a, b)
    raise "Roads must have two cities" if a == b
    @a = a
    @b = b
    @distance = a.kilometers_to(b)
  end

  def the_city_opposite(city)
    raise "#{city} isn't connected to #{self}" unless a == city || b == city
    city == a ? b : a
  end

  def to_s
    "#{a} | #{b} (#{"%.0f" % distance} km)"
  end

  def self.between(a, b)
    ALL.detect do |road|
      return road if (road.a == a && road.b == b) || (road.a == b && road.b == a)
    end
    raise "There is no road between " + a.to_s + " and " + b.to_s
  end

  ALL.add new(City::Hamburg,   City::Berlin)
  ALL.add new(City::Hamburg,   City::Antwerp)
  ALL.add new(City::Hamburg,   City::Frankfurt)
  ALL.add new(City::Berlin,    City::Warsaw)
  ALL.add new(City::Berlin,    City::Prague)
  ALL.add new(City::Antwerp,   City::Paris)
  ALL.add new(City::Paris,     City::Tours)
  ALL.add new(City::Paris,     City::Lyon)
  ALL.add new(City::Paris,     City::Zurich)
  ALL.add new(City::Paris,     City::Frankfurt)
  ALL.add new(City::Frankfurt, City::Prague)
  ALL.add new(City::Krakow,    City::Warsaw)
  ALL.add new(City::Krakow,    City::Prague)
  ALL.add new(City::Krakow,    City::Vienna)
  ALL.add new(City::Vienna,    City::Munich)
  ALL.add new(City::Vienna,    City::Prague)
  ALL.add new(City::Zurich,    City::Milan)
  ALL.add new(City::Lyon,      City::Torino)
  ALL.add new(City::Torino,    City::Milan)
  ALL.add new(City::Torino,    City::Rome)
  ALL.add new(City::Milan,     City::Rome)
end

Start = City::Rome
End   = City::Berlin

result = [] # make a list of cities from Rome to Berlin

total_distance = 0
result.each_with_index do |city, idx|
  print city
  if next_city = result[idx+1]
    distance = Road.between(city, next_city).distance
    total_distance += distance
    puts " -> #{"%.0f" % distance}"
  end
end
print "arrived in #{result.size} steps "
print "(#{"%.0f" % total_distance} km)"
puts ""
puts ""

