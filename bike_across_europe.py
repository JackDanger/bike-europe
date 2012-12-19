from sets import Set
import math
import random

RADIANS = 180/3.14169
def kilometers_between(lat1, lng1, lat2, lng2):
    a1 = lat1 / RADIANS
    a2 = lng1 / RADIANS
    b1 = lat2 / RADIANS
    b2 = lng2 / RADIANS

    t1 = math.cos(a1) * math.cos(a2) * math.cos(b1) * math.cos(b2)
    t2 = math.cos(a1) * math.sin(a2) * math.cos(b1) * math.sin(b2)
    t3 = math.sin(a1) * math.sin(b1)

    return 6366 * math.acos(t1 + t2 + t3)

class City:

    def __init__(self, name, lat, lng):
        self.name = name
        self.lat  = lat
        self.lng  = lng


    def kilometers_to(self, other):
        return kilometers_between(self.lat, self.lng, other.lat, other.lng)

    def roads(self):
        return filter(lambda road:road.a == self or road.b == self, Roads)

    def adjacent_cities(self):
        return [road.the_city_opposite(self) for road in self.roads()]

    def __repr__(self):
        return self.name


class Road:
    def __init__(self, a, b):
        if a == b:
            raise Error("Roads must have two cities")
        self.a = a
        self.b = b
        self.distance = a.kilometers_to(b)

    def the_city_opposite(self, city):
        if city == self.a:
            return self.b
        elif city == self.b:
            return self.a
        else:
            raise "#{city} isn't connected to #{self}"


    def __repr__(self):
        return "#{a} | #{b} (" + ("%.0f" % self.distance) + " km)"


    @staticmethod
    def between(a, b):
        for road in Roads:
            if (road.a == a and road.b == b) or (road.a == b and road.b == a):
                return road
        raise "There is no road between " + str(a) + " and " + str(b)


Berlin    = City('Berlin',    52.482668, 13.359275)
Paris     = City('Paris',     48.980405, 2.2851849)
Milan     = City('Milan',     45.520543, 9.1419459)
Frankfurt = City('Frankfurt', 50.078848, 8.6349115)
Munich    = City('Munich',    48.166229, 11.558089)
Zurich    = City('Zurich',    47.383444, 8.5254142)
Tours     = City('Tours',     47.413572, 0.6810506)
Lyon      = City('Lyon',      45.767122, 4.8339568)
Vienna    = City('Vienna',    48.224431, 16.389240)
Prague    = City('Prague',    50.092396, 14.436144)
Krakow    = City('Krakow',    50.050363, 19.928578)
Warsaw    = City('Warsaw',    52.254756, 21.005968)
Hamburg   = City('Hamburg',   53.539699, 9.9977143)
Antwerp   = City('Antwerp',   51.220613, 4.3954882)
Torino    = City('Torino',    45.105321, 7.6451957)
Rome      = City('Rome',      42.032845, 12.390408)


Roads = Set()
Roads.add(Road(Hamburg,   Berlin))
Roads.add(Road(Hamburg,   Antwerp))
Roads.add(Road(Hamburg,   Frankfurt))
Roads.add(Road(Berlin,    Warsaw))
Roads.add(Road(Berlin,    Prague))
Roads.add(Road(Antwerp,   Paris))
Roads.add(Road(Paris,     Tours))
Roads.add(Road(Paris,     Lyon))
Roads.add(Road(Paris,     Zurich))
Roads.add(Road(Paris,     Frankfurt))
Roads.add(Road(Frankfurt, Prague))
Roads.add(Road(Krakow,    Warsaw))
Roads.add(Road(Krakow,    Prague))
Roads.add(Road(Krakow,    Vienna))
Roads.add(Road(Vienna,    Munich))
Roads.add(Road(Vienna,    Prague))
Roads.add(Road(Zurich,    Milan))
Roads.add(Road(Lyon,      Torino))
Roads.add(Road(Torino,    Milan))
Roads.add(Road(Torino,    Rome))
Roads.add(Road(Milan,     Rome))


Start = Rome
End   = Berlin

result = [] # make a list of cities from Rome to Berlin

total_distance = 0
for idx, city in enumerate(result):
    if idx < len(result) - 1:
        next_city = result[idx+1]
        distance = Road.between(city, next_city).distance
        total_distance += distance
        print city, " -> ", next_city, ("%.0f" % distance), "km"


print "arrived in #{result.size} steps "
print "(" + ("%.0f" % total_distance) + " km)"
print ""
print ""

