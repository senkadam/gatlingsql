package cz.senkadam.gatlingsql.requests

import cz.senkadam.gatlingsql.datasource.EmptySource
import cz.senkadam.gatlingsql.datasource.Source
/**
 * Created by senk on 7.1.15.
 */

/**
 * Companion object for the start of building SQL scenario
 */
object SqlBuilder {
  /**
   * DSL chain starts from here.
   *
   */
  def sql=new SqlBuilder()

  def sql(requestName: String) = new SqlBuilder(requestName)

  def sql(requestName:String, dataSource:Source)=new SqlBuilder(requestName,dataSource)
}

/**
 * This class contains specific kinds of SQL queries you can make.
 */
class SqlBuilder(val requestName: String="NO NAME", val dataSource:Source=EmptySource()) {

  //TODO COMMENTS UPDATE
  /**
   * From here we launch our specific DSL. Again, it's a bit contrived in this
   * example.
   *
   * We create an instance of the PingUrlBuilder here, which is our *actual*
   * DSL class. These two methods are purely illustrative, in the http library
   * they return different builders.
   *
   * Defaults are supplied for parameters, which can later be overridden by using
   * methods on the builder instances.
   */
  def selectQuery(query: String) = new SqlRequestBuilder(requestName, SqlSelectStatement(query))

  def preparedQuery(query: String)= new SqlRequestBuilder(requestName, SqlPreparedSelectStatement(query,dataSource))

  def updateQuery(query: String) = new SqlRequestBuilder(requestName, SqlUpdateStatement(query))

  def selectQuery(requestName:String, query: String) = new SqlRequestBuilder(requestName, SqlSelectStatement(query))

  def preparedQuery(requestName:String,query: String)= new SqlRequestBuilder(requestName, SqlPreparedSelectStatement(query,dataSource))

  def updateQuery(requestName:String,query: String) = new SqlRequestBuilder(requestName, SqlUpdateStatement(query))

}


