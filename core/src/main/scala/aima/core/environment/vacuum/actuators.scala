package aima.core.environment.vacuum

import aima.core.agent.{Actuator, Agent, UnreliableActuator}
import aima.core.random.DefaultRandomness

/**
  * @author Shawn Garner
  */
class SuckerActuator(val agent: Agent[Vacuum, VacuumPercept, VacuumAction]) extends Actuator[Vacuum, VacuumAction] {
  def act(action: VacuumAction, vacuum: Vacuum): Vacuum = action match {
    case Suck =>
      vacuum.copy(vacuum.map.updateStatus(agent, CleanPercept))
    case _ => vacuum
  }
}
class MoveActuator(val agent: Agent[Vacuum, VacuumPercept, VacuumAction]) extends Actuator[Vacuum, VacuumAction] {
  def act(action: VacuumAction, vacuum: Vacuum): Vacuum = action match {
    case move: MoveAction =>
      vacuum.copy(vacuum.map.moveAgent(agent, move))
    case _ => vacuum
  }
}
