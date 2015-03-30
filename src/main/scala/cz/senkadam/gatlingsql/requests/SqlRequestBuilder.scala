package cz.senkadam.gatlingsql.requests

import cz.senkadam.gatlingsql.actions.SqlActionBuilder
import io.gatling.core.action.builder.ActionBuilder

/**
 * Created by senk on 7.1.15.
 */

/**
 *
 * @param requestName human readable request name
 * @param sqlStatement SQL statement which will be executed in SQL action
 */
class SqlRequestBuilder(val requestName: String, val sqlStatement: SqlStatement) {

  /**
   * This guy handles building the instance using all the parameters we've passed in - not so much right now
   */
  private[gatlingsql] def build = sqlStatement match {
    case a:SqlPreparedSelectStatement => a.next
    case _ => sqlStatement
  }

  /**
   * Method converting this builder into instance of a SqlActionBuilder.
   */
  private[gatlingsql] def toActionBuilder = new SqlActionBuilder(requestName, this)
}

/**
 * Just setup the implicit conversion here. This is used by the exec method of Gatling
 * to convert our SqlRequestBuilder into a SqlActionBuilder.
 */
object SqlRequestBuilder {
  implicit def toActionBuilder(requestBuilder: SqlRequestBuilder): ActionBuilder = new SqlActionBuilder(requestBuilder.requestName, requestBuilder)
}
