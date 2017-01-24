package events

import scala.collection.mutable

/**
  * Created by julien on 23/01/17.
  */
object EventHub {

  private val inputsListener: mutable.MutableList[EventListener] = mutable.MutableList()

  def registerForInputs(eventListener: EventListener) = inputsListener.+=(eventListener)

  def mouseMoved(x: Int, y: Int) = tell(new TouchedEvent(x, y))
  def justTouched(x: Int, y: Int) = tell(new JustTouchedEvent(x, y))

  def tell(obj: Any) = obj match {
    case touched: TouchedEvent => inputsListener.foreach(_.heyListen(touched))
    case justTouched: JustTouchedEvent => inputsListener.foreach(_.heyListen(justTouched))
  }
}