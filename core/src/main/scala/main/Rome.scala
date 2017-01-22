package main

import com.badlogic.gdx.Game
import draw.{Looper, GdxProvider}

object Rome extends Game {

  val screenWidth = 160
  val screenHeight = 100

  override def create() = {
    setScreen(new Looper(new GdxProvider {}))
  }

}
