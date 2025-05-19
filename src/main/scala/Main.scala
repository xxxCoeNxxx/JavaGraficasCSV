
import service.{DataLoader, StatsService, ChartService, StopsService, StopsChartService}
import scala.io.StdIn

object Main extends App {
  val drivers = DataLoader.loadDrivers("resources/drivers.csv")
  val results = DataLoader.loadResults("resources/results.csv")
  val stops = DataLoader.loadStopsResults("resources/pit_stops.csv")

  var salir = false
  while (!salir) {
    println("¿Qué quieres hacer?")
    println("1. Mostrar los 10 pilotos con más podios")
    println("2. Mostrar los 8 pilotos con más paradas")
    println("3. Salir")

    StdIn.readLine() match {
      case "1" =>
        val topResults = StatsService.topResultsByDriver(drivers, results)
        ChartService.showBarChart(topResults)
      case "2" =>
        val showStops = StopsService.stopsNumberByDriver(drivers, stops)
        StopsChartService.showBarChart(showStops)
      case "3" =>
        salir = true
      case _ =>
        println("Opción no válida, por favor elige una opción correcta.")
    }
  }
}