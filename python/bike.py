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
        return filter(lambda road:road.a is self or road.b is self, Roads)

    def adjacent_cities(self):
        return [road.the_city_opposite(self) for road in self.roads()]

    def __repr__(self):
        return self.name


class Road:
    def __init__(self, a, b):
        if a is b:
            raise Exception("Roads must have two distinct cities")
        self.a = a
        self.b = b
        self.distance = a.kilometers_to(b)

    def the_city_opposite(self, city):
        if city is self.a:
            return self.b
        elif city is self.b:
            return self.a
        else:
            raise Exception("%s isn't connected to %s" % (city, self))


    def __repr__(self):
        return "%s - %s (%.0f) km" % (a, b, self.distance)


    @staticmethod
    def between(a, b):
        for road in Roads:
            if (road.a is a and road.b is b) or (road.a is b and road.b is a):
                return road
        raise Exception("There is no road between %s and %s" % (a, b))


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


Roads = set()
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
        print "%s - %s (%.0f) km" % (city, next_city, distance)


print "arrived in %d steps (%.0f km)" % (len(result), total_distance)
print ""
print ""

