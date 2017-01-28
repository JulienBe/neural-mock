package systems.physic

import com.badlogic.gdx.math.{Matrix4, Rectangle, Vector2}
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType
import com.badlogic.gdx.physics.box2d._
import com.badlogic.gdx.utils.Array
import main.Rome
import units.ships.Ship

/**
  * Created by julien on 24/01/17.
  */
object Box2DHelper {

  private val vector2 = new Vector2()
  private val debugRenderer = new Box2DDebugRenderer()

  def toBoxUnits(f: Float) = f / Rome.ppm
  def fromBoxUnits(f: Float) = f * Rome.ppm

  def setPos(ship: Ship, x: Float, y: Float) = {
    ship.body.setTransform(toBoxUnits(x), toBoxUnits(y), 0)
  }

  def screenX(ship: Ship) = fromBoxUnits(ship.body.getPosition.x)
  def screenY(ship: Ship) = fromBoxUnits(ship.body.getPosition.y)

  def debugRender(matrix4: Matrix4) = {
    val renderMatrix = new Matrix4(matrix4)
    renderMatrix.scale(Rome.ppm, Rome.ppm, 1)
    debugRenderer.render(Physic.world, renderMatrix)
  }

  def createCircle(bodyType: BodyType, width: Float, category: Short, mask: Short, obj: Object, position: Vector2): Body = {
    createBody(bodyType, createCircleShape(width), category, mask, obj, position)
  }
  def createRectangle(bodyType: BodyType, rectangle: Rectangle, category: Short, mask: Short, obj: Object, position: Vector2) = {
    createBody(bodyType, createRectangleShape(rectangle), category, mask, obj, position)
  }
  private def createBody(bodyType: BodyType, shape: Shape, category: Short, mask: Short, obj: Object, position: Vector2) = {
    val b = Physic.world.createBody(createBodyDef(bodyType, position))
    createFixture(b, shape, category: Short, mask: Short, obj)
    shape.dispose()
    b
  }
  private def createBodyDef(bodyType: BodyType, postion: Vector2): BodyDef = {
    val bodyDef = new BodyDef()
    bodyDef.`type` = bodyType
    postion.scl(toBoxUnits(1))
    if (bodyType == BodyType.DynamicBody)
      bodyDef.position.set(postion)
    bodyDef
  }
  private def createFixture(b: Body, shape: Shape, category: Short, mask: Short, obj: Object) = {
    val fixtureDef = new FixtureDef()
    fixtureDef.shape = shape
    fixtureDef.filter.categoryBits = category
    fixtureDef.filter.maskBits = mask
    val fixture = b.createFixture(fixtureDef)
    fixture.setUserData(obj)
  }
  private def createCircleShape(width: Float): CircleShape = {
    val shape = new CircleShape()
    shape.setRadius(toBoxUnits(width))
    vector2.set(toBoxUnits(width / 2), toBoxUnits(width / 2))
    shape.setPosition(vector2)
    shape
  }

  private def createRectangleShape(rectangle: Rectangle): Shape = {
    val polygon = new PolygonShape()
    val center = new Vector2(toBoxUnits(rectangle.x + rectangle.width * 0.5f), toBoxUnits(rectangle.y + rectangle.height * 0.5f))
    polygon.setAsBox((rectangle.width * 0.5f) / Rome.ppm, (rectangle.height * 0.5f) / Rome.ppm, center, 0.0f)
    polygon
  }

  private def getCircle(radius: Float, x: Float, y: Float, ppt: Float): Shape = {
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
