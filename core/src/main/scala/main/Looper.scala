package main

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import draw.{GdxProvider, Screener}
import systems.physic.Physic
import units.ships.{Enemy, PlayerShip}

import scala.collection.mutable

class Looper(gdxProvider: GdxProvider) extends Screener(gdxProvider) with GdxProvider {

  var playerShips: mutable.MutableList[PlayerShip] = mutable.MutableList()
  var enemyShips: mutable.MutableList[Enemy] = mutable.MutableList()
  val batch = gdxProvider.getShapeRenderer()

  def inputs() = {
    if (playerShips.size == 0)
      playerShips.+=(new PlayerShip)
    if (enemyShips.size == 0)
      enemyShips.+=(new Enemy)
  }

  override def render(delta: Float) = {
    draw
    inputs
    Physic.doPhysicsStep(delta)
  }

  private def draw = {
    camera.update()
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    batch.begin()
    playerShips.foreach(_.draw(batch))
    enemyShips.foreach(_.draw(batch))
    batch.end()
  }
}