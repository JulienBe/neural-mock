package units.ships

import brols.Dimension
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType
import systems.eventhub.events.Event
import systems.eventhub.{EventHub, EventListener}
import systems.physic.{Box2DHelper, Physic}
import units.Collider

/**
  * Created by julien on 23/01/17.
  */
class Ship extends Collider with EventListener {

  val pos = new Vector2()
  val body = createBody

  protected def createBody =
    Box2DHelper.createBody(Ship.bodyType, Ship.damping, Ship.width, Ship.density, Ship.friction, Ship.restitution, Ship.category, Ship.mask)

  EventHub.registerForCollisions(this)

  def draw(batch: ShapeRenderer) = {
    batch.rect(pos.x + Ship.dimension.halfWidth, pos.y - Ship.dimension.halfHeight, Ship.dimension.width, Ship.dimension.height)
  }

  override def heyListen(event: Event) = event match {
    case _ => println("Ouch ! " + event)
  }

}

object Ship {
  def bodyType = BodyType.DynamicBody

  val dimension = new Dimension(50, 50)
  val speed = 0.5f
  val damping = 5f
  val friction = 4f
  val density = 0.05f
  val restitution = 0.6f
  val width = .3f
  val halfWidth = width / 2f
  val category = Physic.otherCategory
  val mask = Physic.otherMask
}