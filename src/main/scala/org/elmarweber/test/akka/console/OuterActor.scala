package org.elmarweber.test.akka.console

import akka.actor._
import com.typesafe.atmos.trace.Tracer

class OuterActor(id: String) extends Actor with ActorLogging {
  import OuterActor._
  import InnerActor._

  val innerActor = context.actorOf(Props[InnerActor], "inner")
  var resultCount = 0

  override def receive = {
    case Start(sleep1, sleep2) =>
      Tracer(context.system).markStart("outer")
      innerActor ! Calc1(sleep1)
      innerActor ! Calc2(sleep2)
    case Result(value) =>
      resultCount += 1
      if (resultCount == 2) {
        log.info("Finished " + id)
        Tracer(context.system).markEnd("outer")
        context.stop(self)
      }
  }
}

object OuterActor {
  sealed trait OuterActorProtocol
  case class Start(sleep1: Long, sleep2: Long)
}
