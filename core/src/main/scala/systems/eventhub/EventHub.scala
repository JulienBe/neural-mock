package systems.eventhub

import systems.eventhub.events.{CollisionEvent, JustTouchedEvent, TouchedEvent}

import scala.collection.mutable

/**
  * Created by julien on 23/01/17.
  */
object EventHub {

  private val inputsListener: mutable.MutableList[EventListener] = mutable.MutableList()
  private val collisionsListener: mutable.MutableList[EventListener] = mutable.MutableList()

  def registerForInputs(eventListener: EventListener) = addListener(eventListener, inputsListener)
  def registerForCollisions(eventListener: EventListener) = addListener(eventListener, collisionsListener)
  private def addListener(eventListener: EventListener, mutableList: mutable.MutableList[EventListener]) = mutableList.+=(eventListener)

  def tell(obj: Any) = obj match {
    case touched: TouchedEvent => inputsListener.foreach(_.heyListen(touched))
    case justTouched: JustTouchedEvent => inputsListener.foreach(_.heyListen(justTouched))
  }

  def mouseMoved(x: Int, y: Int) = tell(new TouchedEvent(x, y))
  def justTouched(x: Int, y: Int) = tell(new JustTouchedEvent(x, y))

  def shipCollision(event: CollisionEvent) = {

  }

}