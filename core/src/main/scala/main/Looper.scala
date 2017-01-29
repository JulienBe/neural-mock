package main

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import draw.{GdxProvider, Screener}
import systems.physic.{Box2DHelper, Physic}
import world.World

class Looper(gdxProvider: GdxProvider) extends Screener(gdxProvider) with GdxProvider {

  override def render(delta: Float) = {
    inputs
    act(delta)
    draw
    Physic.doPhysicsStep(delta)
  }

  def inputs() = {
  }

  def act(delta: Float) = {
    Rome.time += delta
    World.act()
  }

  private def draw = {
    camera.update()
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    shapeRenderer.begin()
    World.render(shapeRenderer)
    shapeRenderer.end()
    Box2DHelper.debugRender(camera.combined)
  }
}