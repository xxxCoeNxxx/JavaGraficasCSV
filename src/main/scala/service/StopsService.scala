package service

import model.{Driver, PitStops}

object StopsService {
    def stopsNumberByDriver(
        drivers: List[Driver],
        stops: List[PitStops]
    ): Map[String,Int] = {
        val stopsMap = drivers.map(d => d.driverId -> s"${d.forename} ${d.surname}").toMap
        stops
          .groupBy(_.driverId)
          .view
          .mapValues(_.size)
          .toMap
          .map { case (driverId, count) =>
            stopsMap.getOrElse(driverId, s"ID $driverId") -> count
            }
    }
}