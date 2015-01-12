package cz.senkadam.gatlingsql.actions

import java.lang.System._

import akka.actor.ActorRef
import cz.senkadam.gatlingsql.requests.SqlRequestBuilder
import io.gatling.core.Predef.Session
import io.gatling.core.action.Action
import io.gatling.core.result.message.Status
import io.gatling.core.result.writer.DataWriterClient

/**
 * Created by senk on 5.1.15.
 */

abstract class SqlAction(requestName: String, next: ActorRef, requestBuilder: SqlRequestBuilder)
  extends Action with ConnectionHandling with DataWriterClient {
  /**
   * Execute our ping request, or whatever request you're actually doing. This
   * uses the PingUrlBuilder to build the 3rd party Pinger instance; then
   * it executes the ping and records the result.
   */
  def execute(session: Session) {
    val sqlStatement = requestBuilder.build // build our 3rd party instance

    val connectionAndTime = getConnectionAndTimes
    val requestStartDate = connectionAndTime.time

    val requestEndDate = requestStartDate

    val connection = connectionAndTime.connection

    val requestResult = if (sqlStatement.executeQuery(connection)) {
      Status("OK")
    } else {
      Status("KO")
    }

    val responseStartDate = currentTimeMillis()
    val responseEndDate = responseStartDate


    // This is an important line. This actually records the request and it's result. Without this call
    // you won't see any data. Customise the parameters to your heart's content.
    writeRequestData(session, "Request " + requestName, requestStartDate, requestEndDate, responseStartDate, responseEndDate, requestResult)

    connection.close()
    //DataWriter.logRequest(session.scenarioName, session.userId, "Request " + requestName, requestStartDate, responseEndDate, endOfRequestSendingDate, endOfRequestSendingDate, requestResult, requestMessage)
    // This is also an important line. This passes the focus onto the next action in the chain.
    // Without this line your pipeline will just hang indefinitely.
    next ! session
  }
}
