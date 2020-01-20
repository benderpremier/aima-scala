package aima.core.environment.vacuum

import aima.core.agent.{Agent, Sensor}

/**
  * @author Shawn Garner
  */
class AgentLocationSensor(val agent: Agent[Vacuum, VacuumPercept, VacuumAction]) extends Sensor[Vacuum, VacuumPercept] {
  def perceive(vacuum: Vacuum): VacuumPercept =
    vacuum.map.getAgentLocation(agent).getOrElse(NoPercept)
}

class DirtSensor(val agent: Agent[Vacuum, VacuumPercept, VacuumAction]) extends Sensor[Vacuum, VacuumPercept] {
  def perceive(vacuum: Vacuum): VacuumPercept =
    vacuum.map.getDirtStatus(agent).getOrElse(NoPercept)
}
