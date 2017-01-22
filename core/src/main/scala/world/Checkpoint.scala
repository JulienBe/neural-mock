package world

import brols.Creator
import com.badlogic.gdx.math.{Circle, Vector2}

case class Checkpoint(pos: Vector2) {
  val circle = new Circle(pos.x, pos.y, Checkpoint.radius)
}

object Checkpoint {
  val diameter = 600
  val radius = diameter / 2
  val minCheckpoints = 2
  val maxCheckpoints = 12

  def createCheckpoints(nb: Int, width: Int, height: Int) = {
    if (nb < minCheckpoints || nb > maxCheckpoints)
      throw new IllegalArgumentException("Invalid number of checkpoints : " + nb + " min is : " + minCheckpoints + " max is : " + maxCheckpoints)
    List.tabulate(nb)(i =>
      new Checkpoint(new Vector2(Creator.int(width), Creator.int(height)))
    )
  }
}
