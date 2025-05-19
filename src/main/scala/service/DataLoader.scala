
package service

import model.{Driver, RaceResult, PitStops}
import org.apache.commons.csv.{CSVFormat, CSVParser}
import java.nio.file.{Files, Paths}
import scala.jdk.CollectionConverters._

object DataLoader {
  def loadDrivers(path: String): List[Driver] = {
    val reader = Files.newBufferedReader(Paths.get(path))
    val csv = CSVParser.parse(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())
    csv.getRecords.asScala.toList.map { rec =>
      Driver(
        rec.get("driverId").toInt,
        rec.get("forename"),
        rec.get("surname")
      )
    }
  }

  def loadResults(path: String): List[RaceResult] = {
    val reader = Files.newBufferedReader(Paths.get(path))
    val csv = CSVParser.parse(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())
    csv.getRecords.asScala.toList.map { rec =>
      RaceResult(
        rec.get("resultId").toInt,
        rec.get("raceId").toInt,
        rec.get("driverId").toInt,
        // Puede que la posición sea "\N", por eso Option
        if (rec.get("position") == "\\N") None else Some(rec.get("position").toInt)
      )
    }
  }

  def loadStopsResults(path: String): List[PitStops] = {
    val reader = Files.newBufferedReader(Paths.get(path))
    val csv = CSVParser.parse(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())
    csv.getRecords.asScala.toList.map { rec =>
      PitStops(
        rec.get("raceId").toInt,
        rec.get("driverId").toInt,
        rec.get("stop").toInt
      )
    }
  }
}

