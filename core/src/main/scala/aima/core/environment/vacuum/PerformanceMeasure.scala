package aima.core.environment.vacuum

import aima.core.agent.Agent
import aima.core.agent.Simulation.PerformanceMeasure

object PerformanceMeasure {
  //This measure only cares about the state of the vacuum world
  val cleanliness: PerformanceMeasure[VacuumEnvironment, VacuumPercept, VacuumAction] =
    (vacuumEnvironment: VacuumEnvironment, _: Agent[VacuumEnvironment, VacuumPercept, VacuumAction]) =>
      vacuumEnvironment.map.nodes.collect(_.dirtStatus == CleanPercept).size
}
