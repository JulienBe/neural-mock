package events

/**
  * Created by julien on 23/01/17.
  */
trait EventListener {
  def heyListen(event: Event)
}
