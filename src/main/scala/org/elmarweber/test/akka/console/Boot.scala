package org.elmarweber.test.akka.console

import akka.actor._
import scala.concurrent.duration._
import java.util.UUID

object Boot extends App {
  import OuterActor._

  implicit val system = ActorSystem("akka-console-request-tracking-test")
  implicit val exCtx = system.dispatcher

  system.scheduler.schedule(1 second, 1 second, new Runnable {
    override def run() = {
      val id = UUID.randomUUID().toString
      val outerActor = system.actorOf(Props(new OuterActor(id)), "outer-" + id)
      outerActor ! Start(500, 500)
    }
  })
}
