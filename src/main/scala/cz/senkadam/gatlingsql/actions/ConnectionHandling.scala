package cz.senkadam.gatlingsql.actions

import java.sql.{Connection, DriverManager}

import io.gatling.core.config.GatlingConfiguration.configuration

/**
 * Created by senk on 8.1.15.
 */

/**
 * Holder of references to connection and time values
 * @param connection SQL connection to a database
 * @param time time in miliseconds
 */
case class ConnectionAndTimes(connection: Connection, time: Long)

/**
 * Mixin providing function returning proper Connection and time
 */
trait ConnectionHandling {
  /**
   * Function
   * @return SQL Connection and time
   */
  def getConnectionAndTimes: ConnectionAndTimes
}

/**
 * Mixin providing function returning proper time including creation of SQL connection
 */
trait ConnectionTimeIncluded extends ConnectionHandling {
  /**
   * Function
   * @return SQL Connection and time starting before connection creation
   */
  override def getConnectionAndTimes = {
    val time = System.currentTimeMillis()
    val connection = ConnectionFactory.connection

    new ConnectionAndTimes(connection, time)
  }
}

/**
 * Mixin providing function returning proper time including creation of SQL connection
 */
trait ConnectionTimeExcluded extends ConnectionHandling {
  /**
   * Function
   * @return SQL Connection and time starting after connection creation
   */
  override def getConnectionAndTimes = {
    val connection = ConnectionFactory.connection
    val time = System.currentTimeMillis()

    new ConnectionAndTimes(connection, time)
  }
}

/**
 * Factory object creating a SQL connection
 */
object ConnectionFactory {

  def connection: Connection = DriverManager.getConnection(
    configuration.data.jdbc.db.url,
    configuration.data.jdbc.db.username,
    configuration.data.jdbc.db.password)
}
