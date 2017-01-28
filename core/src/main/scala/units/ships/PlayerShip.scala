package units.ships

import com.badlogic.gdx.physics.box2d.Body
import systems.eventhub._
import systems.eventhub.events.{Event, JustTouchedEvent, TouchedEvent}
import systems.physic.{Box2DHelper, Physic}

/**
  * Created by julien on 22/01/17.
  */
class PlayerShip extends Ship with EventListener {

  EventHub.registerForInputs(this)

  override protected def createBody: Body =
    Box2DHelper.createBody(Ship.bodyType, Ship.size.w, PlayerShip.category, PlayerShip.mask, this)

  override def heyListen(event: Event): Unit = {
    event match {
      case touched: TouchedEvent => Box2DHelper.setPos(this, touched.x, touched.y); return
      case justTouched: JustTouchedEvent => Box2DHelper.setPos(this, justTouched.x, justTouched.y); return
      case _ => println(this + " couldn't handle " + event)
    }
    super.heyListen(event)
  }
}

object PlayerShip {
  val category = Physic.playerCategory
  val mask = Physic.playerMask
}