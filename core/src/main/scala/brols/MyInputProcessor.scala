package brols

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.{Gdx, InputAdapter, InputProcessor}
import events.EventHub
import main.Rome

/**
  * Created by julien on 23/01/17.
  */
class MyInputProcessor extends InputAdapter {

  private def y(screenY: Int) = Rome.screenHeight - screenY

  // assuming this is equivalent to touched ?
  override def mouseMoved(screenX: Int, screenY: Int): Boolean = {
    EventHub.mouseMoved(screenX, y(screenY))
    true
  }

  // assuming this is equivalent to justTouched and mouseMoved won't be also triggered ?
  override def touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean = {
    EventHub.justTouched(screenX, y(screenY))
    true
  }

}