package solutions.ws13.assignment4;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import org.amcgala.agent.AgentMessages;
import org.amcgala.agent.AmcgalaAgent;
import org.amcgala.agent.Simulation;
import org.amcgala.agent.World;
import org.amcgala.agent.AgentMessages.SpawnAt;

/**
* Fuellt ein Polygon mit einem Flood Fill Algorithmus.
*/
public class FloodFillAgent extends AmcgalaAgent {
	private Queue<int[]> pos = new LinkedList<>();
	int step = 0;
    @Override
    protected AgentMessages.AgentMessage onUpdate(Simulation.SimulationUpdate update) {
        
    	if (update.currentCell().value() == 0) {
            // TODO Flood-Fill Operation implementieren.

        	int x = update.currentPosition().x();
        	int y = update.currentPosition().y();
        	this.spawnChild(new World.Index(x + 1, y));
        	this.spawnChild(new World.Index(x - 1, y));
        	this.spawnChild(new World.Index(x, y + 1));
        	this.spawnChild(new World.Index(x, y - 1));
     
        	return new AgentMessages.ChangeValue(1);
        }else {

            return die();
        }
    }
}

