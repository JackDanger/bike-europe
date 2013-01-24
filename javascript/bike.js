Util = {
  RADIANS: 180/3.14169,

  kilometersBetween: function(lat1, lng1, lat2, lng2) {
    var a1 = lat1 / Util.RADIANS,
        a2 = lng1 / Util.RADIANS,
        b1 = lat2 / Util.RADIANS,
        b2 = lng2 / Util.RADIANS,
        t1 = Math.cos(a1) * Math.cos(a2) * Math.cos(b1) * Math.cos(b2),
        t2 = Math.cos(a1) * Math.sin(a2) * Math.cos(b1) * Math.sin(b2),
        t3 = Math.sin(a1) * Math.sin(b1)

    return 6366 * Math.acos(t1 + t2 + t3)
  }
}

City = function(name, lat, lng) {
  this.name = name
  this.lat  = lat
  this.lng  = lng
}
City.prototype.kilometersTo = function(other) {
  return Util.kilometersBetween(
    this.lat,  this.lng,
    other.lat, other.lng
  )
}
City.prototype.roads = function() {
  if (this._roads) return this._roads
  var roads = [],
      self = this
  Road.All.forEach(function(road) {
    if (road.a == self || road.b == self)
      roads.push(road)
  })
  this._roads = roads
  return roads
}
City.prototype.adjacentCities = function() {
  if (this._cities) return this._cities
  var cities = [],
      roads = this.roads()
  for (var i in roads)
    cities.push(roads[i].theCityOpposite(this))
  this._cities = cities
  return cities
}
City.prototype.toString = function() {
  return this.name
}

Berlin    = new City('Berlin',    52.482668, 13.359275)
Paris     = new City('Paris',     48.980405, 2.2851849)
Milan     = new City('Milan',     45.520543, 9.1419459)
Frankfurt = new City('Frankfurt', 50.078848, 8.6349115)
Munich    = new City('Munich',    48.166229, 11.558089)
Zurich    = new City('Zurich',    47.383444, 8.5254142)
Tours     = new City('Tours',     47.413572, 0.6810506)
Lyon      = new City('Lyon',      45.767122, 4.8339568)
Vienna    = new City('Vienna',    48.224431, 16.389240)
Prague    = new City('Prague',    50.092396, 14.436144)
Krakow    = new City('Krakow',    50.050363, 19.928578)
Warsaw    = new City('Warsaw',    52.254756, 21.005968)
Hamburg   = new City('Hamburg',   53.539699, 9.9977143)
Antwerp   = new City('Antwerp',   51.220613, 4.3954882)
Torino    = new City('Torino',    45.105321, 7.6451957)
Rome      = new City('Rome',      42.032845, 12.390408)

Road = function(a, b) {
  this.a = a
  this.b = b
  this.distance = a.kilometersTo(b)
}
Road.prototype.theCityOpposite = function(city) {
  return city == this.a ? this.b : this.a
}
Road.prototype.toString = function() {
  return this.a.toString() + " | "
       + this.b.toString() + " ("
       + this.distance.toFixed(3) + " km)"
}
Road.All = []
Road.All.contains = function(x) {
  // mimicking a Set here
  return this.hasOwnProperty(x.toString())
}
Road.All.add = function(element) {
  if (!this.contains(element)) {
    this.push(element)
    this[element.toString()] = true
  }
}
Road.between = function(a, b) {
  for (var i in Road.All) {
    var r = Road.All[i]
    console.log("checking: ", r)
    console.log(r.a.name, r.b.name)
    var road = Road.All[i]
    if (   (road.a == this.a && road.b == this.b)
        || (road.a == this.b && road.b == this.a)) {
      console.log(i, "returning road: ", road)
      return road 
    }

  }
  return "No road between " + a + " and " + b + "!"
}

Road.All.add(new Road(Hamburg,   Berlin))
Road.All.add(new Road(Hamburg,   Antwerp))
Road.All.add(new Road(Hamburg,   Frankfurt))
Road.All.add(new Road(Berlin,    Warsaw))
Road.All.add(new Road(Berlin,    Prague))
Road.All.add(new Road(Antwerp,   Paris))
Road.All.add(new Road(Paris,     Tours))
Road.All.add(new Road(Paris,     Lyon))
Road.All.add(new Road(Paris,     Zurich))
Road.All.add(new Road(Paris,     Frankfurt))
Road.All.add(new Road(Frankfurt, Prague))
Road.All.add(new Road(Krakow,    Warsaw))
Road.All.add(new Road(Krakow,    Prague))
Road.All.add(new Road(Krakow,    Vienna))
Road.All.add(new Road(Vienna,    Munich))
Road.All.add(new Road(Vienna,    Prague))
Road.All.add(new Road(Zurich,    Milan))
Road.All.add(new Road(Lyon,      Torino))
Road.All.add(new Road(Torino,    Milan))
Road.All.add(new Road(Torino,    Rome))
Road.All.add(new Road(Milan,     Rome))

Start = Rome
End   = Berlin



//result = your_code()
// Your job: make `result` a list of cities from Rome to Berlin

visited = {}
function travel(start) {
  frontier = [[start]]
  while (frontier.length) {
    var path = frontier.shift(),
        city = path[path.length-1]
    console.log(path)
    visited[city] = true
    if (city == End)
      return path
    for (var i in city.adjacentCities()) {
      var newCity = city.adjacentCities()[i],
          newPath = path.slice(0)
      if (visited.hasOwnProperty(newCity)) {
        console.log("skipping ", city)
      } else {
        console.log("pushing " + newCity)
        newPath.push(newCity)
        frontier.push(newPath)
      }
    }
  }
}
result = travel(Start)


totalDistance = 0
for (var i in result) {
  var city = result[i]
  var nextCity = result[parseInt(i)+1]
  console.log(i, result.length, city)
  console.log("nextcity: ", nextCity)

  if (nextCity) {
    var road = Road.between(city, nextCity),
        distance = road.distance
    console.log(road, distance)
    totalDistance += distance
    console.log(" -> " + distance.toFixed(3))
  }
  else {
    console.log('no nextCity')
  }
}
console.log("arrived in " + result.length + " steps ")
console.log("(" + totalDistance.toFixed(3) + " km)")
