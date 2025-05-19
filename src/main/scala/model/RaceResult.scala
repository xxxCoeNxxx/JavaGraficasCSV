package model

case class RaceResult(
  resultId: Int,
  raceId: Int,
  driverId: Int,
  position: Option[Int]
)
