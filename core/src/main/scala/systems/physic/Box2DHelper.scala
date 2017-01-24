package systems.physic

import com.badlogic.gdx.math.{Rectangle, Vector2}
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType
import com.badlogic.gdx.physics.box2d._
import com.badlogic.gdx.utils.Array

/**
  * Created by julien on 24/01/17.
  */
object Box2DHelper {
  def createBody(bodyType: BodyType, damping: Float, width: Float, density: Float, friction: Float, restitution: Float, category: Short, mask: Short): Body = {
    val b = Physic.world.createBody(createBodyDef(bodyType, damping))
    val shape: Shape = createShape(width)
    createFixture(b, shape, density: Float, friction: Float, restitution: Float, category: Short, mask: Short)
    shape.dispose()
    b
  }
  def createBodyDef(bodyType: BodyType, damping: Float): BodyDef = {
    val bodyDef = new BodyDef()
    bodyDef.`type` = bodyType
    bodyDef.linearDamping = damping
    bodyDef
  }
  def createFixture(b: Body, shape: Shape, density: Float, friction: Float, restitution: Float, category: Short, mask: Short) = {
    val fixtureDef = new FixtureDef()
    fixtureDef.shape = shape
    fixtureDef.density = density
    fixtureDef.friction = friction
    fixtureDef.restitution = restitution
    fixtureDef.filter.categoryBits = category
    fixtureDef.filter.maskBits = mask
    val fixture = b.createFixture(fixtureDef)
    fixture.setUserData(this)
  }
  def createShape(width: Float): CircleShape = {
    val shape = new CircleShape()
    shape.setRadius(width)
    shape
  }

  def getRectangle(rectangle: Rectangle, ppt: Float): Shape = {
    val polygon = new PolygonShape()
    val center = new Vector2((rectangle.x + rectangle.width * 0.5f) / ppt, (rectangle.y + rectangle.height * 0.5f) / ppt)
    polygon.setAsBox((rectangle.width * 0.5f) / ppt, (rectangle.height * 0.5f) / ppt, center, 0.0f)
    polygon
  }

  def getCircle(radius: Float, x: Float, y: Float, ppt: Float): Shape = {
    val circleShape = new CircleShape()
    circleShape.setRadius(radius / ppt)
    circleShape.setPosition(new Vector2((x + radius) / ppt, (y + radius) / ppt))
    circleShape
  }

  def getPolygon(vertices: scala.Array[Float]): Shape = {
    val polygon = new PolygonShape()
    polygon.set(vertices)
    polygon
  }

  // even are supposed to be horizontal and the following is the corresponding vertical
  // will slice the rectangle into 4 shapes
  // Could more efficiently sliced by sharing walls, but seems easier to understand. tbd if it works with polygon shape vertices
  // 7\----------------/5
  // | 8--------------6 |
  // | |              | |
  // | |              | |
  // | 4______________3 |
  // 1/________________\2
  def getPolygons(r: Rectangle, ppt: Float, offset: Float): Array[Shape] = {
    val x = r.x / ppt
    val y = r.y / ppt
    val w = r.width / ppt
    val h = r.height / ppt
    val p1 = (x,                  y)
    val p2 = (x + w,              y)
    val p3 = (x + w * (1-offset), y + h * offset)
    val p4 = (x + w * offset,     y + h * offset)
    val p5 = (x + w,              y + h)
    val p6 = (x + w * (1-offset), y + h * (1-offset))
    val p7 = (x,                  y + h)
    val p8 = (x + w * offset,     y + h * (1-offset))
    getPolygons(
      scala.Array(
        p1._1, p1._2, p2._1, p2._2, p3._1, p3._2, p4._1, p4._2,
        p2._1, p2._2, p5._1, p5._2, p6._1, p6._2, p3._1, p3._2,
        p5._1, p5._2, p7._1, p7._2, p8._1, p8._2, p6._1, p6._2,
        p7._1, p7._2, p1._1, p1._2, p4._1, p4._2, p8._1, p8._2
      ),
      ppt
    )
  }

  def getPolygons(vertices: scala.Array[Float], ppt: Float): Array[Shape] = {
    val shapes = new Array[Shape]()
    for (i <- 0 until vertices.length by 8) {
      val polygon = new PolygonShape()
      polygon.set(scala.Array(
        vertices(i), vertices(i + 1), vertices(i + 2), vertices(i + 3),
        vertices(i + 4), vertices(i + 5), vertices(i + 6), vertices(i + 7)
      ))
      shapes.add(polygon)
    }
    shapes
  }
}
