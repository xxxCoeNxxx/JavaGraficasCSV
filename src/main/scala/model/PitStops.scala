package model

import java.time.LocalTime

case class PitStops(
    raceId: Int,
    driverId: Int,
    stop: Int
)

/* 
case class PitStops(
    raceId: Int,
    driverId: Int,
    stop: Int,
    lap: Int,
    time: LocalTime,
    duration: Float,
    milliseconds: Int
) */

