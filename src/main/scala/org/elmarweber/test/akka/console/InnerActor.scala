package org.elmarweber.test.akka.console

import akka.actor._
import com.typesafe.atmos.trace.Tracer

class InnerActor extends Actor with ActorLogging {
  import InnerActor._

  override def receive = {
    case Calc1(sleep) =>
      Tracer(context.system).markStart("Calc1")
      Thread.sleep(sleep)
      log.info("Slept for " + sleep + " millis")
      Tracer(context.system).markEnd("Calc1")
      sender ! Result(sleep)
    case Calc2( sleep) =>
      Tracer(context.system).markStart("Calc2")
      Thread.sleep(sleep)
      log.info("Slept for " + sleep + " millis")
      Tracer(context.system).markEnd("Calc2")
      sender ! Result(sleep)
  }
}

object InnerActor {
  sealed trait InnerActorProtocol
  case class Calc1(sleep: Long) extends InnerActorProtocol
  case class Calc2(sleep: Long) extends InnerActorProtocol
  case class Result(result: Long) extends InnerActorProtocol
}
