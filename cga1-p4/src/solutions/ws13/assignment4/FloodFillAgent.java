package solutions.ws13.assignment4;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import org.amcgala.agent.AgentMessages;
import org.amcgala.agent.AmcgalaAgent;
import org.amcgala.agent.Simulation;
import org.amcgala.agent.World;
import org.amcgala.agent.AgentMessages.SpawnAt;
import org.amcgala.agent.World.CellWithIndex;

/**
* Fuellt ein Polygon mit einem Flood Fill Algorithmus.
*/
public class FloodFillAgent extends AmcgalaAgent {
	static private Queue<CellWithIndex> pos = new LinkedList<>();
	int step = 0;
	static int count = 0;
    @Override
    protected AgentMessages.AgentMessage onUpdate(Simulation.SimulationUpdate update) {
        
    	if (update.currentCell().value() == 0) {
            // TODO Flood-Fill Operation implementieren.

        	//int x = update.currentPosition().x();
        	//int y = update.currentPosition().y();
        	
        	//this.spawnChild(new World.Index(x + 1, y));
        	//this.spawnChild(new World.Index(x - 1, y));
        	//this.spawnChild(new World.Index(x, y + 1));
        	//this.spawnChild(new World.Index(x, y - 1));
     
    		
    		for(CellWithIndex i : update.neighbours().values())
        		if(i.cell().value() == 0)
        			//pos.add(i);
        			this.spawnChild(i.index());
    		
    		
    		
        	return new AgentMessages.ChangeValue(1);
       /* } else if(!pos.isEmpty()) {
        	CellWithIndex c = pos.poll();
        	if(!pos.isEmpty() && count < 4){
        		count++;
        		this.spawnChild(pos.poll().index());
        	}
        	return new AgentMessages.MoveTo(c.index());
        */} else {
        	count--;
            return die();
        }
    }
}

