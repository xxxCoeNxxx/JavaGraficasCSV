package service

import model.{Driver, RaceResult}

object StatsService {
  // Devuelve un Map con el nombre completo del piloto y la cantidad de podios (posiciones 1-3)
  def topResultsByDriver(
    drivers: List[Driver],
    results: List[RaceResult]
  ): Map[String, Int] = {
    val driverMap = drivers.map(d => d.driverId -> s"${d.forename} ${d.surname}").toMap
    results
      .filter(r => r.position.exists(p => p >= 1 && p <= 3))
      .groupBy(_.driverId)
      .view
      .mapValues(_.size)
      .toMap
      .map { case (driverId, count) =>
        driverMap.getOrElse(driverId, s"ID $driverId") -> count
      }
  }
}
