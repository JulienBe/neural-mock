package world

import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType
import com.badlogic.gdx.utils.Array
import systems.Creator
import systems.physic.{Box2DHelper, Physic}

/**
  * Created by julien on 28/01/17.
  */
class Wall(val rectangle: Rectangle) {
  val body = createBody

  protected def createBody = Box2DHelper.createRectangle(Wall.bodyType, rectangle, Wall.category, Wall.mask, this, Creator.vectorInScreen())
}

object Wall {

  val category = Physic.otherCategory
  val mask = Physic.otherMask
  val width = 2
  val bodyType = BodyType.StaticBody

  def surround(x: Float, y: Float, width: Float, height: Float): Array[Wall] = {
    val bottom =  new Rectangle(x,              y - Wall.width, width,      Wall.width)
    val top =     new Rectangle(x,              height,         width,      Wall.width)
    val left =    new Rectangle(x - Wall.width, y,              Wall.width, height)
    val right =   new Rectangle(width,          y,              Wall.width, height)

    val array = new Array[Wall]()
    array.add(new Wall(bottom))
    array.add(new Wall(top))
    array.add(new Wall(left))
    array.add(new Wall(right))
    array
  }

}
