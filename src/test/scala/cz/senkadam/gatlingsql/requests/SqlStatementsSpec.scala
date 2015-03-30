package cz.senkadam.gatlingsql.requests

import cz.senkadam.gatlingsql.datasource.ListDataSource
import org.specs2.mutable._

/**
 * Created by senk on 21.1.15.
 */
class SqlStatementsSpec extends Specification{

  val testedPreparedStatement1="SELECT * FROM ${table}$"
  val testedPreparedStatement2="SELECT * FROM ${table}$ WHERE ID=1"
  val testedPreparedStatement3="SELECT * FROM T_${table}$ WHERE ID=1"
  val testedPreparedStatement4="SELECT * FROM T_${table}$_T WHERE ID=1"
  val dataSource=ListDataSource(List(Map("table"->"TABLE1"),Map("table"->"TABLE2"),Map("table"->"TABLE3")))

  "SqlPreparedSelectStatement" should {
    "replace part of the string wrapped with ${}$ correctly" in {
      val statement=SqlPreparedSelectStatement(testedPreparedStatement1,dataSource)
      println(statement.query)
      statement.query must_== "SELECT * FROM TABLE1"
    }
    "replace part of the string wrapped with ${}$ correctly even " +
      "when it occurs in the middle of string" in {
      val statement=SqlPreparedSelectStatement(testedPreparedStatement2,dataSource)
      println(statement.query)
      statement.query must_== "SELECT * FROM TABLE1 WHERE ID=1"
    }

    "replace part of the string wrapped with ${}$ correctly even " +
      "when the start of wrapped word is concatened to another word" in{
      val statement=SqlPreparedSelectStatement(testedPreparedStatement3,dataSource)
      println(statement.query)
      statement.query must_== "SELECT * FROM T_TABLE1 WHERE ID=1"

    }
    "replace part of the string wrapped with ${}$ correctly even " +
      "when it occurs in the middle of another word" in{
      val statement=SqlPreparedSelectStatement(testedPreparedStatement4,dataSource)
      println(statement.query)
      statement.query must_== "SELECT * FROM T_TABLE1_T WHERE ID=1"

    }
    "rotate Maps stored in dataSource list when method next is called" in {
      val statement=SqlPreparedSelectStatement(testedPreparedStatement1,dataSource)
      statement.next.query must_== "SELECT * FROM TABLE2"
      statement.next.next.query must_== "SELECT * FROM TABLE3"
      statement.next.next.next.query must_== "SELECT * FROM TABLE1"
    }
  }

}
