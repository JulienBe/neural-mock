package draw

import brols.Timer
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20

class Looper(gdxProvider: GdxProvider) extends Screener(gdxProvider) with GdxProvider {

  val timer = new Timer(90)
  var finished = false

  override def render(delta: Float) = {
    camera.update()
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
  }

}