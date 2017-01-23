package draw

import brols.Timer
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import units.PlayerShip

import scala.collection.mutable

class Looper(gdxProvider: GdxProvider) extends Screener(gdxProvider) with GdxProvider {

  val timer = new Timer(90)
  var finished = false
  var playerShips: mutable.MutableList[PlayerShip] = mutable.MutableList()
  val batch = gdxProvider.getShapeRenderer()

  def inputs() = {
    if (playerShips.size == 0) {
      playerShips.+=(new PlayerShip)
    }
    playerShips.foreach(_.inputs)
  }

  override def render(delta: Float) = {
    draw
    inputs
  }

  private def draw = {
    camera.update()
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    batch.begin()
    playerShips.foreach(_.draw(batch))
    batch.end()
  }
}