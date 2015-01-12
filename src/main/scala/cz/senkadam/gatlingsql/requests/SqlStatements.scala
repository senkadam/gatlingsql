package cz.senkadam.gatlingsql.requests

import java.sql.Connection

/**
 * Created by senk on 7.1.15.
 */

/**
 * Represents various type of JDBC statements holding SQL queries
 * @param query SQL query
 */
abstract class SqlStatement(query: String) {
  def executeQuery(connection: Connection): Boolean
}

case class SqlSelectStatement(selectQuery: String) extends SqlStatement(selectQuery) {
  override def executeQuery(connection: Connection) = try (connection.prepareStatement(selectQuery).execute()) catch {
    case _ => false
  }
}

case class SqlUpdateStatement(updateQuery: String) extends SqlStatement(updateQuery) {
  override def executeQuery(connection: Connection) = try (connection.prepareStatement(updateQuery).executeUpdate() >= 0) catch {
    case _ => false
  }
}

//case class SqlPreparedStatement(preparedQuery:String) extends SqlStatement(preparedQuery){
//override def executeQuery(connection:Connection)=throw UnsupportedOperationException
//}
