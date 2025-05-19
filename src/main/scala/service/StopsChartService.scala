package service
import scala.jdk.CollectionConverters._
import org.knowm.xchart.{CategoryChart, CategoryChartBuilder, SwingWrapper, BitmapEncoder}
import org.knowm.xchart.BitmapEncoder.BitmapFormat

object StopsChartService {
  def showBarChart(data: Map[String, Int]): Unit = {
    val chart: CategoryChart = new CategoryChartBuilder()
      .width(1200).height(1300)
      .title("Paradas por piloto")
      .xAxisTitle("Piloto")
      .yAxisTitle("Paradas")
      .build()

    val (names, counts) = data.toSeq.sortBy(-_._2).unzip
    chart.addSeries("Paradas", names.take(8).toList.asJava, counts.take(8).toList.map(_.asInstanceOf[Number]).asJava)
    new SwingWrapper(chart).displayChart()
    Thread.sleep(20000)

    BitmapEncoder.saveBitmap(chart, "grafica_paradas.png", BitmapFormat.PNG)

  }
}
