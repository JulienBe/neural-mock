package main

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import draw.{GdxProvider, Screener}
import systems.physic.{Box2DHelper, Physic}
import units.ships.{Enemy, PlayerShip}
import world.Wall

import scala.collection.mutable

class Looper(gdxProvider: GdxProvider) extends Screener(gdxProvider) with GdxProvider {

  var playerShips: mutable.MutableList[PlayerShip] = mutable.MutableList()
  var enemyShips: mutable.MutableList[Enemy] = mutable.MutableList()

  Wall.surround(1, 1, Rome.screenWidth - 1, Rome.screenHeight - 1)

  override def render(delta: Float) = {
    inputs
    act(delta)
    draw
    Physic.doPhysicsStep(delta)
  }

  def inputs() = {
    if (playerShips.size == 0)
      playerShips.+=(new PlayerShip)
    if (enemyShips.size == 0)
      enemyShips.+=(Enemy.get())
  }

  def act(delta: Float) = {
    Rome.time += delta
    enemyShips.foreach(_.act())
  }

  private def draw = {
    camera.update()
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    shapeRenderer.begin()
    playerShips.foreach(_.draw(shapeRenderer))
    enemyShips.foreach(_.draw(shapeRenderer))
    shapeRenderer.end()
    Box2DHelper.debugRender(camera.combined)
  }
}