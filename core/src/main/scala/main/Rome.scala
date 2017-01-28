package main

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.{Game, Gdx}
import draw.GdxProvider
import systems.MyInputProcessor

object Rome extends Game {

  val screenWidth = 1280
  val screenHeight = 800
  val screenHalfWidth = screenWidth / 2
  val screenHalfHeight = screenHeight / 2
  val screenCenter = new Vector2(screenHalfWidth, screenHalfHeight)
  var time = 0f

  override def create() = {
    setScreen(new Looper(new GdxProvider {}))
    Gdx.input.setInputProcessor(new MyInputProcessor())
  }

  def widthRatio() = Gdx.graphics.getWidth / Rome.screenWidth
  def heightRatio() = Gdx.graphics.getHeight / Rome.screenHeight
}
