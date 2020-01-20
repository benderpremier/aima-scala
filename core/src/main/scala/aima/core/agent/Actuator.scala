package aima.core.agent

/**
  * @author Shawn Garner
  */
trait Actuator[ENVIRONMENT, ACTION] {
  def act(action: ACTION, e: ENVIRONMENT): Option[ENVIRONMENT]
}
