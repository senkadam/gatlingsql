package cz.senkadam.gatlingsql.requests

import java.sql.Connection
import cz.senkadam.gatlingsql.datasource.{ListDataSource, Source}

/**
 * Created by senk on 7.1.15.
 */

/**
 * Represents various type of JDBC statements holding SQL queries
 * @param query SQL query
 */
abstract class SqlStatement(queryString: String) {
  //def next=this
  def query:String=queryString
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

case class SqlPreparedSelectStatement(preparedQuery:String, dataSource:Source,position:Int=0) extends SqlStatement(preparedQuery){
  private val map=dataSource.data(position)
  //private val map=list(position)
  private val splitQuery = preparedQuery.split("""(\$\{)|(\}\$)""")
  private val queryString= splitQuery.map(x =>if(map.contains(x)) map(x) else x).reduce(_ + _)

  override def query=queryString

  override def executeQuery(connection:Connection)= try (connection.prepareStatement(queryString).execute()) catch {
    case _ => false
  }

  def next:SqlPreparedSelectStatement=SqlPreparedSelectStatement(preparedQuery,dataSource,position+1)

}
