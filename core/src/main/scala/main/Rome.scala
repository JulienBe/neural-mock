package main

import brols.MyInputProcessor
import com.badlogic.gdx.{Game, Gdx}
import draw.GdxProvider

object Rome extends Game {

  val screenWidth = 160
  val screenHeight = 100

  override def create() = {
    setScreen(new Looper(new GdxProvider {}))
    Gdx.input.setInputProcessor(new MyInputProcessor())
  }

}
