package units.ships

import brols.Dimension
import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType
import systems.eventhub._
import systems.eventhub.events.{Event, JustTouchedEvent, TouchedEvent}
import systems.physic.{Box2DHelper, Physic}

/**
  * Created by julien on 22/01/17.
  */
class PlayerShip extends Ship with EventListener {

  EventHub.registerForInputs(this)

  override protected def createBody: Body =
    Box2DHelper.createBody(PlayerShip.bodyType, PlayerShip.damping, PlayerShip.width, PlayerShip.density, PlayerShip.friction, PlayerShip.restitution, PlayerShip.category, PlayerShip.mask)

  override def heyListen(event: Event) = event match {
    case touched: TouchedEvent => pos.set(touched.x, touched.y)
    case justTouched: JustTouchedEvent => pos.set(justTouched.x, justTouched.y)
    case _ => println(this + " couldn't handle " + event)
  }
}

object PlayerShip {
  def bodyType = BodyType.KinematicBody

  val dimension = new Dimension(50, 50)
  val speed = 0.5f
  val damping = 5f
  val friction = 4f
  val density = 0.05f
  val restitution = 0.6f
  val width = .3f
  val halfWidth = width / 2f
  val category = Physic.playerCategory
  val mask = Physic.playerMask
}