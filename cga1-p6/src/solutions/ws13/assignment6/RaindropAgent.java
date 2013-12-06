package solutions.ws13.assignment6;

import java.util.Random;

import org.amcgala.agent.AgentMessages;
import org.amcgala.agent.AmcgalaAgent;
import org.amcgala.agent.Simulation;
import org.amcgala.agent.World.JCellWithIndex;


public class RaindropAgent extends AmcgalaAgent {
	private static Random rnd = new Random();



    private int phase = 0;
    private int maxCells = 3 * (5 + rnd.nextInt(10));
    private float abtrag = 0;
    private JCellWithIndex min;

	@Override
    protected AgentMessages.AgentMessage onUpdate(Simulation.SimulationUpdate update) {
    	switch(phase++ % 3){
    	case 0:
    		return changeValue(Math.min(1, update.currentCell().value() + abtrag));
    	case 1:
    		min = new JCellWithIndex(update.currentPosition(), update.currentCell());
    		
    		for(JCellWithIndex jcwi : update.neighbours().values())
    			if(jcwi.cell().value() < min.cell().value())
    				min = jcwi;
    			
    		abtrag = .4f * (update.currentCell().value() - min.cell().value());
    		if(abtrag < 0)
    			return die();
    		return changeValue(Math.max(0, update.currentCell().value() - abtrag));
    	case 2:
    		if(phase > maxCells 
    				|| min.index() == update.currentPosition()
    				|| update.currentPosition().x() == 0)
    			return die();
    		else {
    			phase = 0;
    			return moveTo(min.index());
    		}
    	default:
    		System.err.println("default error");
    		return die();
    	}
    }
}
