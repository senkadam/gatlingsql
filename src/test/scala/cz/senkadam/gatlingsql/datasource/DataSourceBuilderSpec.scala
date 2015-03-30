package cz.senkadam.gatlingsql.datasource

import org.specs2.mutable._
import io.gatling.core.Predef._

/**
 * Created by senk on 21.1.15.
 */
class DataSourceBuilderSpec extends Specification{

  /* "DataSourceBuilder" should{
    "build the correctly looking List of Map" in {
      io.gatling.core.config.GatlingConfiguration.setUpForTest(scala.collection.mutable.Map("gatling.core.directory.data"->"resources/data"))

      val source=DataSourceBuilder.dataSource(csv("testData.csv"))
      (source.data(0))("test1") must_== "A"
      (source.data(1))("test2") must_== "b"
      (source.data(2))("test3") must_== "3"
    }

    "build randomly ordered data when source is random" in {
      io.gatling.core.config.GatlingConfiguration.
        setUpForTest(scala.collection.mutable.Map("gatling.core.directory.data"->"resources/data"))
      var boolean=false
      for(i<-1 to 10){
        val source2=DataSourceBuilder.randomizedDataSource(csv("testData.csv"))
        
        if(!boolean && (source2.data(0))("test1") != "A"){
          boolean = true
        }
      }
      boolean must beTrue
    }
  }*/

}
