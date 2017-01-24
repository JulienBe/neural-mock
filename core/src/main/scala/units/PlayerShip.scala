package units

import events._

/**
  * Created by julien on 22/01/17.
  */
class PlayerShip extends Ship with EventListener {

  EventHub.registerForInputs(this)

  override def heyListen(event: Event) = event match {
    case touched: TouchedEvent => pos.set(touched.x, touched.y)
    case justTouched: JustTouchedEvent => pos.set(justTouched.x, justTouched.y)
    case _ => println(this + " couldn't handle " + event)
  }
}
