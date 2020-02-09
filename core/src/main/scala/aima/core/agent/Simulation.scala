package aima.core.agent

import aima.core.agent.Simulation._

import scala.collection.immutable.Map

/**
  * @author Damien Favre
  */
final case class Simulation[ENVIRONMENT, PERCEPT, ACTION](
    environment: ENVIRONMENT,
    agents: List[Agent[ENVIRONMENT, PERCEPT, ACTION]],
    performanceMeasure: PerformanceMeasure[ENVIRONMENT, PERCEPT, ACTION]
) {
  def run(): (Simulation[ENVIRONMENT, PERCEPT, ACTION], SimulationRunSummary[ENVIRONMENT, PERCEPT, ACTION]) = {
    val (newEnv, simulationRunSummary) =
      agents.foldLeft((environment, emptySimulationRunSummary[ENVIRONMENT, PERCEPT, ACTION])) {
        case ((env, simulationRunSummary), agent) =>
          val (newEnv, agentRunSummary) = agent.run(env)
          val agentPerformance          = performanceMeasure(newEnv, agent)
          val agentSimulationRunSummary: AgentSimulationRunSummary[ENVIRONMENT, PERCEPT, ACTION] =
            AgentSimulationRunSummary(agentRunSummary, agentPerformance)
          (newEnv, simulationRunSummary + (agent -> agentSimulationRunSummary))
      }
    (
      Simulation(newEnv, agents, performanceMeasure),
      simulationRunSummary
    )
  }

}

object Simulation {

  type PerformanceMeasure[ENVIRONMENT, PERCEPT, ACTION] =
    (ENVIRONMENT, Agent[ENVIRONMENT, PERCEPT, ACTION]) => Double
  type SimulationRunSummary[ENVIRONMENT, PERCEPT, ACTION] =
    Map[Agent[ENVIRONMENT, PERCEPT, ACTION], AgentSimulationRunSummary[ENVIRONMENT, PERCEPT, ACTION]]
  def emptySimulationRunSummary[ENVIRONMENT, PERCEPT, ACTION]: SimulationRunSummary[ENVIRONMENT, PERCEPT, ACTION] =
    Map.empty
}

final case class AgentSimulationRunSummary[ENVIRONMENT, PERCEPT, ACTION](
    agentRunSummary: AgentRunSummary[PERCEPT, ACTION],
    performance: Double
)
