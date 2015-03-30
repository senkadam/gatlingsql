package cz.senkadam.gatlingsql.datasource

import io.gatling.core.feeder.RecordSeqFeederBuilder

import scala.util.Random

/**
 * Class providing data for preparedStatements
 * Created by senk on 21.1.15.
 */
object DataSourceBuilder {
  /**
   * Returning data in order defined in source file
   * @param file source data file
   * @return List of Maps with data to be filed in
   */
  def dataSource(file:RecordSeqFeederBuilder[String]):Source=OrderDataSource(file)

  /**
   * Returning data in random order
   * @param file source data file
   * @return List of Maps with data to be filed in
   */
  def randomizedDataSource(file:RecordSeqFeederBuilder[String]):Source=RandomDataSource(file)
}

abstract class Source(){
  def data(position:Int):Map[String,String]
}

case class EmptySource() extends Source{
  override def data(position:Int):Map[String,String]=Map()
}

case class ListDataSource(list:List[Map[String,String]]) extends Source{
  override def data(position:Int)=list(position % list.length)
}

abstract class DataSource(file:RecordSeqFeederBuilder[String]) extends Source(){
  private[datasource] val privateData=(for (map <- file.random.records.seq) yield map.seq).toList
}

case class OrderDataSource(orderFile:RecordSeqFeederBuilder[String]) extends DataSource(orderFile){
  var positionLocal=0
    override def data(position:Int)=
    {
      positionLocal+=1
      privateData(positionLocal % privateData.length)
    }
}

case class RandomDataSource(randomFile:RecordSeqFeederBuilder[String]) extends DataSource(randomFile){
    override def data(position:Int)=privateData(Random.nextInt(privateData.length))
}

