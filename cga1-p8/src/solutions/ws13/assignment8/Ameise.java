package solutions.ws13.assignment8;

import static solutions.ws13.assignment8.PayloadUtils.isHoney;

import java.util.Map.Entry;

import org.amcgala.agent.Agent;
import org.amcgala.agent.Agent.Pheromone;
import org.amcgala.agent.AgentMessages;
import org.amcgala.agent.AmcgalaAgent;
import org.amcgala.agent.Directions;
import org.amcgala.agent.Simulation;
import org.amcgala.agent.World;
import org.amcgala.agent.World.InformationObject;
import org.amcgala.agent.World.JCell;
import org.amcgala.agent.World.JCellWithIndex;



class WrongPheromone implements InformationObject { //, Pheromone {
	public float strength() {
		return 1;
	}

	public float decayRate() {
		return 0.66f;
	}
	
	public float spreadRate() {
		return 0.09f;
	}
	
	public String toString(){
		return "WrongPheromone " + strength();
	}
}

public class Ameise extends AmcgalaAgent {
	
	private static boolean init = true;
	private static boolean spawnStrategy = true;
	private boolean searchHigh = spawnStrategy;
	private boolean hasHoney = false;
	private boolean firstRoundAfterHoney = false;
	private JCellWithIndex lastCell = null;

	private int phase = 0;
	
	
	private static float valOf(JCellWithIndex c){
		return c.cell().value();
	}
	
	
	private static float getPheromoneLevel(JCell c){
		float sum = 0;
		if(c != null)
			for (World.InformationObject iObj : c.informationObjects())
	        	if (iObj instanceof WrongPheromone) 
	        		sum += ((WrongPheromone) iObj).strength();
		return sum;
	}
	
	private void logLast(Simulation.SimulationUpdate u){
		lastCell = new JCellWithIndex(u.currentPosition(), u.currentCell());
	}
	
    @Override
    protected AgentMessages.AgentMessage onUpdate(Simulation.SimulationUpdate update) {
    	switch(phase = (phase + 1) % 2){
    	case 0:
    		if(init){
    			//spawnChild();
    			init = false;
    			lastCell = new JCellWithIndex(update.currentPosition(), update.currentCell());
    		}
    		
    		if(!hasHoney)
	        for (Agent.Payload payload : update.currentCell().payloadObjects()) {
	        	System.err.println(payload);
	        	if(isHoney(payload)) {
	                hasHoney = true;
	                firstRoundAfterHoney = true;
	        		spawnChild();
	                return takePayload(payload);
	            }
	        }
	        
	        if(hasHoney)
	        	if(firstRoundAfterHoney){
		        	firstRoundAfterHoney = false;
		        	searchHigh = !searchHigh;
		        	logLast(update);
		        	return moveTo(lastCell.index());
	        	} /*else {
	        		JCellWithIndex pheroHigh = new JCellWithIndex(null, null);

        			for(JCellWithIndex ci : update.neighbours().values()){
            			System.err.println(ci.index() + "  " + getPheromoneLevel(ci.cell()));
	        			if(getPheromoneLevel(ci.cell()) > getPheromoneLevel(pheroHigh.cell()) 
	        					&& ci.index() != lastCell.index())
	        				pheroHigh = ci;
	        		}
        			System.err.println("moveTo" + pheroHigh.index());
        			logLast(update);
	        		return moveTo(pheroHigh.index());
	        	}*/
	        

	        JCellWithIndex current = new JCellWithIndex(update.currentPosition(), update.currentCell());
	        JCellWithIndex low = current;
	        JCellWithIndex high = current;
	        
	        for(Entry<World.Index,World.JCellWithIndex> entry: update.neighbours().entrySet()){
	        	if(valOf(entry.getValue()) >= valOf(high) )
	        		high = entry.getValue();
	        	if(valOf(entry.getValue()) <= valOf(low) )
	        		low = entry.getValue();
	        }

	        if(searchHigh && valOf(high) == valOf(current)
	        	|| valOf(low) == valOf(current)){
        		searchHigh = !searchHigh;
        		//spawnStrategy = searchHigh;
        		
        		System.err.println("CHANGE STRATEGY: " + ((!searchHigh) ? "low" : "high")  + " to " + ((searchHigh) ? "low" : "high"));
        		
        		//spawnChild(getNeighbourIndex(Directions.DOWN(), update));
	        	//spawnChild(getNeighbourIndex(Directions.LEFT(), update));
	        	//spawnChild(getNeighbourIndex(Directions.RIGHT(), update));
	        	//spawnChild(getNeighbourIndex(Directions.UP(), update));

        		
        		JCellWithIndex pheroHigh = new JCellWithIndex(null, null);
        		JCellWithIndex pheroLow = new JCellWithIndex(null, null);
        		for(JCellWithIndex ci : update.neighbours().values()){
        			if(getPheromoneLevel(ci.cell()) > getPheromoneLevel(pheroHigh.cell()) 
        					&& ci != lastCell )
        				pheroHigh = ci;
        			if(getPheromoneLevel(ci.cell()) < getPheromoneLevel(pheroLow.cell()) 
        					&& ci != lastCell )
        				pheroLow = ci;
        		}

        		//System.err.println(pheroHigh);
        		
        		JCellWithIndex rnd = getNeighbour(Directions.DOWN(), update);
        		while(true){
        			rnd = getRandomNeighbour(update);
        			//System.err.println(rnd);
        			if(rnd.index() != lastCell.index()) //&& getPheromoneLevel(rnd.cell()) <= getPheromoneLevel(low.cell()))
        				break;
        		}
        	
	        	//spawnChild();
        		//System.err.println("RND" + update.currentPosition());
        		//System.err.println("RND" + rnd.index());
        		
        		
    			if((getPheromoneLevel(pheroHigh.cell()) > 2 && Math.random() > 0.4)
    				|| (hasHoney && Math.random() > 0.2)){
    				System.err.println("CHOOSE PHEROMONE DIRECTION @ PLVL" + getPheromoneLevel(pheroHigh.cell()));
    				logLast(update);
    				return moveTo(pheroHigh.index());
	        	} else {
	        		System.err.println("CHOOSE RANDOM DIRECTION");
	        		logLast(update);
    				return moveTo(rnd.index());    				
    			}
	        }
	        
	        if(searchHigh){
	        	logLast(update);
	        	return moveTo(high.index());
	        } else {
	        	logLast(update);
	        	return moveTo(low.index());
	        }
    	case 1:
	        return putInformationObjectTo(update.currentPosition(), new WrongPheromone());
    	default:
			return die();
    	}
    }
}
