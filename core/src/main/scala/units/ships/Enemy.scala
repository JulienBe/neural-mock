package units.ships

import com.badlogic.gdx.math.Vector2
import main.Rome
import systems.Creator

/**
  * Created by julien on 23/01/17.
  */
class Enemy extends Ship {

  val dir = new Vector2(Vector2.X).rotate(Creator.degree)
  var nextImpulse = 0f

  def act() = {
    if (nextImpulse < Rome.time) {
      body.applyForceToCenter(dir, true)
      dir.rotate(Creator.degree)
      nextImpulse = Rome.time + Enemy.impulseFreq
    }
  }

}

object Enemy {

  val impulseFreq = 1f

  def get() = {
    new Enemy
  }
}