package cz.senkadam.gatlingsql.requests

/**
 * Created by senk on 7.1.15.
 */
object Predef {
  /**
   * This method is starting point for SQL gatling DSAL
   *
   * By convention this method just delegates to a factory method on the main
   * Builder class.
   *
   * @param requestName Human readable name of defined request
   * @return A SqlRequestBuilder instance, which is used to pick a correct type of SQL request.
   */
  def sql(requestName: String) = SqlBuilder.sql(requestName)
}
