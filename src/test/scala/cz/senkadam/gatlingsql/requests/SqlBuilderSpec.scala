package cz.senkadam.gatlingsql.requests

import org.junit.runners.JUnit4
import org.specs2.mutable._
import org.junit.runner._




/**
 * Created by senk on 12.1.15.
 */


class SqlBuilderSpec extends Specification{

  "selectQuery method" should {
    "return instance of SqlSelectStatement" in {
      SqlBuilder.sql("ahoj").selectQuery("SELECT").build must_=== SqlSelectStatement("SELECT")
    }
  }


}
