package solutions.ws13.assignment5;

import org.amcgala.agent.AgentMessages.AgentMessage;
import org.amcgala.agent.AmcgalaAgent;
import org.amcgala.agent.Directions;
import org.amcgala.agent.Simulation.SimulationUpdate;
import org.amcgala.agent.World;

public class FloydSteinbergAgent extends AmcgalaAgent {
	int phase = 0;
	float quantErr = 0;

	@Override
	protected AgentMessage onUpdate(SimulationUpdate update) {
		switch (phase++) {
		case 0:
			if (checkVisited(update.currentCell()))
				return die();
			else {
				float quantErrSum = 0;
				for (World.InformationObject informationObject : update
						.currentCell().informationObjects()) {
					if (informationObject instanceof World.QuantisationError) {
						World.QuantisationError e = (World.QuantisationError) informationObject;
						quantErrSum += e.value();
					}
				}

				float oldVal = update.currentCell().value() + quantErrSum;
				float newVal = Math.round(oldVal);
				quantErr = oldVal - newVal;
				return changeValue(newVal);
			}
		case 1:
			return putInformationObjectTo(
					getNeighourIndex(Directions.RIGHT(), update),
					new World.QuantisationError(quantErr * 7f / 16f));
		case 2:
			return putInformationObjectTo(
					getNeighourIndex(Directions.DOWN_RIGHT(), update),
					new World.QuantisationError(quantErr * 1f / 16f));
		case 3:
			return putInformationObjectTo(
					getNeighourIndex(Directions.DOWN(), update),
					new World.QuantisationError(quantErr * 5f / 16f));
		case 4:
			if(update.currentPosition().x() != 0)
				spawnChild(getNeighourIndex(Directions.DOWN_LEFT(), update));
			return putInformationObjectTo(
					getNeighourIndex(Directions.DOWN_LEFT(), update),
					new World.QuantisationError(quantErr * 3f / 16f));
		case 5:
			return putVisitedObject(update);
		case 6:
			phase = 0;
			return moveTo(getNeighourIndex(Directions.RIGHT(), update));
		default:
			return die();
		}
	}

}
