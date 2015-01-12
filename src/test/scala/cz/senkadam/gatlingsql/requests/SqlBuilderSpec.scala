package cz.senkadam.gatlingsql.requests

import org.junit.runners.JUnit4
import org.specs2.mutable._
import org.junit.runner._




/**
 * Created by senk on 12.1.15.
 */


class SqlBuilderSpec extends Specification{

  val selectQuerySQLString= "SELECT * FROM T"
  val updateQuerySQLString= "CREATE TABLE T"

  "selectQuery method in SqlBuilder" should {
    "return instance of SqlSelectStatement" in {
      SqlBuilder.sql("testSelect").selectQuery(selectQuerySQLString).build must_=== SqlSelectStatement(selectQuerySQLString)
    }
  }

  "updateQuery method in SqlBuilder" should {
    "return instance of SqlUdateStatement" in {
      SqlBuilder.sql("testUpdate").updateQuery(updateQuerySQLString).build must_=== SqlUpdateStatement(updateQuerySQLString)
    }
  }


}
