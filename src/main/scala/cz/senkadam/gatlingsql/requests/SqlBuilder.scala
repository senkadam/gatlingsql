package cz.senkadam.gatlingsql.requests

/**
 * Created by senk on 7.1.15.
 */

/**
 * Companion object for the start of building SQL scenario
 */
object SqlBuilder {
  /**
   * DSL chain starts from here.
   */
  def sql(requestName: String) = new SqlBuilder(requestName)
}

/**
 * This class contains specific kinds of SQL queries you can make.
 */
class SqlBuilder(val requestName: String) {

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

  def preparedQuery(query: String) = new SqlRequestBuilder(requestName, SqlSelectStatement(query))

  def updateQuery(query: String) = new SqlRequestBuilder(requestName, SqlSelectStatement(query))

}
