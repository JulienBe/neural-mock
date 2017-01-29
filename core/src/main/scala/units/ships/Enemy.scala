package units.ships

import com.badlogic.gdx.math.Vector2
import main.Rome
import systems.Creator
import systems.eventhub.events.{Event, ShipCollisionEvent}
import systems.physic.Physic
import world.World

/**
  * Created by julien on 23/01/17.
  */
class Enemy extends Ship {

  val dir = new Vector2(Vector2.X).rotate(Creator.degree).scl(80)
  var nextImpulse = 0f

  def act() = {
    if (nextImpulse < Rome.time) {
      body.applyForceToCenter(dir, true)
      dir.rotate(Creator.degree)
      nextImpulse = Rome.time + Enemy.impulseFreq
    }
  }

  override def heyListen(event: Event): Unit = {
    event match {
      case shipCollision: ShipCollisionEvent =>
        Physic.bodyToClean(body)
        World.removeEnemy(this)
        return
    }
    super.heyListen(event)
  }
}

object Enemy {

  val impulseFreq = 1f

  def get() = {
    new Enemy
  }
}