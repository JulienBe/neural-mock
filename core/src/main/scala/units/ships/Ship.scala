package units.ships

import brols.Size
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType
import systems.eventhub.events.Event
import systems.eventhub.{EventHub, EventListener}
import systems.physic.{Box2DHelper, Physic}

/**
  * Created by julien on 23/01/17.
  */
class Ship extends EventListener {

  val body = createBody

  protected def createBody = Box2DHelper.createCircle(Ship.bodyType, Ship.size.w, Ship.category, Ship.mask, this)

  EventHub.registerForCollisions(this)

  def draw(batch: ShapeRenderer) = {
    batch.circle(Box2DHelper.screenX(this) + Ship.size.w, Box2DHelper.screenY(this) + Ship.size.h, Ship.size.w)
  }

  override def heyListen(event: Event) = event match {
    case _ => println("Ouch ! " + event)
  }

}

object Ship {
  def bodyType = BodyType.KinematicBody

  val size = new Size(10, 10)
  val category = Physic.otherCategory
  val mask = Physic.otherMask
}