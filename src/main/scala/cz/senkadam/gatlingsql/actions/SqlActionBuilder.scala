package cz.senkadam.gatlingsql.actions

import akka.actor.{ActorRef, Props}
import cz.senkadam.gatlingsql.requests.SqlRequestBuilder
import io.gatling.core.action.builder.ActionBuilder
import io.gatling.core.config.Protocols


/**
 * Created by senk on 5.1.15.
 */


class SqlActionBuilder(requestName: String, requestBuilder: SqlRequestBuilder) extends ActionBuilder {

  /**
   * Something todo with the action chain. Creates a new instance of our builder with a new
   * next action point.
   */
  def withNext(next: ActorRef) = new SqlActionBuilder(requestName, requestBuilder)

  /**
   * Contract new SQLActions and wires them up with the Actor stuff.
   */
  def build(next: ActorRef, protocolConfigurationRegistry: Protocols) = {
    system.actorOf(Props(new SqlAction(requestName, next, requestBuilder) with ConnectionReuse))
  }
}