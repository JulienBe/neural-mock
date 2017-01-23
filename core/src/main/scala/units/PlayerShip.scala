package units

import events.{Event, EventHub, EventListener, TouchedEvent}

/**
  * Created by julien on 22/01/17.
  */
class PlayerShip extends Ship with EventListener {

  EventHub.registerForInputs(this)

  override def heyListen(event: Event) = event match {
    case touched: TouchedEvent => pos.set(touched.x, touched.y)
  }
}
