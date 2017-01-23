package units

import brols.Dimension
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2

/**
  * Created by julien on 23/01/17.
  */
class Ship {
  val dimension = new Dimension(50, 50)
  val pos = new Vector2()

  def draw(batch: ShapeRenderer) = {
    batch.rect(pos.x + dimension.halfWidth, pos.y - dimension.halfHeight, dimension.width, dimension.height)
  }
}
