package service
import scala.jdk.CollectionConverters._
import org.knowm.xchart.{CategoryChart, CategoryChartBuilder, SwingWrapper, BitmapEncoder}
import org.knowm.xchart.BitmapEncoder.BitmapFormat

object ChartService {
  def showBarChart(data: Map[String, Int]): Unit = {
    val chart: CategoryChart = new CategoryChartBuilder()
      .width(1200).height(1300)
      .title("Podios por piloto")
      .xAxisTitle("Piloto")
      .yAxisTitle("Podios (top 3)")
      .build()

    val (names, counts) = data.toSeq.sortBy(-_._2).unzip
    chart.addSeries("Podios", names.take(10).toList.asJava, counts.take(10).toList.map(_.asInstanceOf[Number]).asJava)
    new SwingWrapper(chart).displayChart()
    Thread.sleep(20000)

    BitmapEncoder.saveBitmap(chart, "grafica_podios.png", BitmapFormat.PNG)

  }
}
