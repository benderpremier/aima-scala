package aima.core.environment.vacuum

import aima.core.agent.{Actuator, Agent}

/**
  * @author Shawn Garner
  */
class SuckerActuator(val agent: Agent[Vacuum, VacuumPercept, VacuumAction]) extends Actuator[Vacuum, VacuumAction] {
  def act(action: VacuumAction, vacuum: Vacuum): Option[Vacuum] = action match {
    case Suck =>
      Some(vacuum.copy(vacuum.map.updateStatus(agent, CleanPercept)))
    case _ => None
  }
}
class MoveActuator(val agent: Agent[Vacuum, VacuumPercept, VacuumAction]) extends Actuator[Vacuum, VacuumAction] {
  def act(action: VacuumAction, vacuum: Vacuum): Option[Vacuum] = action match {
    case move: MoveAction =>
      Some(vacuum.copy(vacuum.map.moveAgent(agent, move)))
    case _ => None
  }
}
