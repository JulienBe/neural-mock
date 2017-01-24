package systems.eventhub.events

import units.Collider
import units.ships.Ship

/**
  * Created by julien on 24/01/17.
  */
class CollisionEvent(val colliderA: Collider, val colliderB: Collider) extends Event
class ShipCollisionEvent(colliderA: Ship, colliderB: Ship) extends CollisionEvent(colliderA, colliderB)
