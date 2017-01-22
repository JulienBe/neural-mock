package brols

import scala.util.Random

/**
  * Created by julien on 10/07/16.
  */
object Creator {

  def float = Random.nextFloat

  def boolean = Random.nextBoolean

  def int(max: Int) = Random.nextInt(max)

  def floatInBounds(min: Float, max: Float) = min + (Random.nextFloat() * (max - min))

  def intInBounds(min: Int, max: Int) = min + Random.nextInt(max)

  def positiveValueInBounds(min: Float, max: Float) = {
    if (min < 0) floatInBounds(0, max)
    else floatInBounds(min, max)
  }

  def valueInBoundsWithCheck(min: Float, max: Float, checkMin: Float, checkMax: Float) = {
    floatInBounds(if (min < checkMin) checkMin else min, if (max > checkMax) checkMax else max)
  }

  /**
    * >= min && < max
    */
  def testBound(min: Float, max: Float, value: Float) = value >= min && value < max

  def randomIntNot(ceiling: Int, not: Int) = {
    var rep = Random.nextInt(ceiling)
    if (rep == not)
      rep = (rep + 1) % ceiling
    rep
  }

}
