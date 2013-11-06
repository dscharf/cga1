package solutions.ws13.assignment4;

import org.amcgala.agent.AgentMessages;
import org.amcgala.agent.AmcgalaAgent;
import org.amcgala.agent.Simulation;
import org.amcgala.agent.World;


/**
* Zeichnet eine Line von A nach B.
*/
public class BresenhamLineAgent extends AmcgalaAgent {

    private int x1;
    private int y1;
    private int dx;
    private int dy;
    private int sx;
    private int sy;
    private int x;
    private int y;
    private int err;
    private boolean draw;


    public BresenhamLineAgent(int x0, int y0, int x1, int y1) {
        spawnAt(x0, y0);

        this.x1 = x1;
        this.y1 = y1;


        dx = Math.abs(x1 - x0);
        dy = Math.abs(y1 - y0);

        sx = (x0 < x1) ? 1 : -1;
        sy = (y0 < y1) ? 1 : -1;

        this.x = x0;
        this.y = y0;
        
        this.err = dx - dy;
        draw = true;
    }

    @Override
    protected AgentMessages.AgentMessage onUpdate(Simulation.SimulationUpdate update) {
        if (draw) {
            draw = false;
            return new AgentMessages.ChangeValue(1);
        }
        if (x == x1 && y == y1) {
            success();
            return die();
        } else {
            // TODO Bresenham-Algorithmus implementieren.
        	int e2 = 2*err;
        	
        	if (e2 > -dy){
        		err -= dy;
        		x += sx;
        	}

        	if(e2 < dx){ 
        		err += dx;
        		y += sy;
        	}
        	
        	draw = true;
            World.Index point = new World.Index(x, y);
            return new AgentMessages.MoveTo(point);
        }


    }
}


