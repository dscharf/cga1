package solutions.ws13.assignment3.lsystem;

import org.amcgala.Turtle;
import org.amcgala.TurtleState;
import org.amcgala.math.Vector3d;
import org.amcgala.shape.util.CompositeShape;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Stack;

import static java.lang.Math.*;

/**
* Ein Lindenmayer-System, das folgende Zeichnen interpretieren kann:
* - + : Rechtsdrehung
* - - : Linksdrehung
* - [ : Push
* - ] : Pop
*
* @author Robert Giacinto
* @since 2.0
*/
public class LindenmayerSystem {
    private Logger log = LoggerFactory.getLogger(LindenmayerSystem.class);

    private Rules rules;
    private Level level;
    private Stack<Turtle> turtles;
    private Turtle turtle;
    private CompositeShape shape;
    private Length length;
    private Angle angle;
    private Axiom axiom;


    /**
     * Erstellt ein neues L-System.
     *
     * @param axiom  das Startsymbol des L-Systems
     * @param rules  die Ersetzungsregeln, die auf das Axiom angewendet werden
     * @param level  die Rekursionstiefe
     * @param length die Schrittweite
     * @param angle  der Winkel, um den gedreht wird
     * @param shape  das Shape, das für die Darstellung des L-Systems verwendet werden soll
     */
    public LindenmayerSystem(Axiom axiom, Rules rules, Level level, Length length, Angle angle, CompositeShape shape) {
        this.axiom = axiom;
        this.rules = rules;
        this.level = level;
        this.length = length;
        this.angle = angle;
        this.shape = shape;
        turtles = new Stack<>();
        turtle = new Turtle(shape);
    }

    /**
     * Erstellt ein neues L-System.
     *
     * @param axiom         das Startsymbol des L-Systems
     * @param rules         die Ersetzungsregeln, die auf das Axiom angewendet werden
     * @param level         die Rekusionstiefe
     * @param length        die Schrittweite
     * @param angle         der Winkel, um den gedreht wird
     * @param shape         das Shape, das für die Darstellung des L-Systems verwendet werden soll
     * @param startPosition die Startposition der Turtle
     */
    public LindenmayerSystem(Axiom axiom, Rules rules, Level level, Length length, Angle angle, CompositeShape shape, Vector3d startPosition, float startHeading) {
        this(axiom, rules, level, length, angle, shape);
        Vector3d heading = new Vector3d(cos(toRadians(startHeading)), -sin(toRadians(startHeading)), 0).normalize();
        turtle = new Turtle(new TurtleState(startHeading, heading, startPosition), shape);
    }


    /**
     * Wendet die Regeln des L-Systems auf das Startsymbol an.
     */
    public void run() {
        String current = applyRules();

        for (char c : current.toCharArray()) {
            switch (c) {
                case '+':
                    log.debug("Right Turn: {}", angle.value);
                    turtle.turnRight(angle.value);
                    break;
                case '-':
                	log.debug("Left Turn: {}", angle.value);
                	turtle.turnLeft(angle.value);
                	break;
                case 'm':
                	log.debug("Move without line: {}", length.value);
                	turtle.up();
                	turtle.move(length.value);
                	break;
                case 'M':
                	log.debug("Move with line: {}", length.value);
                	turtle.down();
                	turtle.move(length.value);
                	break;
                case '[':
                    log.debug("Push Turtle to stack");
                    turtles.push(new Turtle(turtle.getTurtleState(), shape));
                    break;
                case ']':
                	log.debug("Remove Turtle from stack");
                	if(turtles.size() > 0){
                		turtle = turtles.pop();
                	} else
                		System.err.println("cant remove turtle from empty stack");
                	break;
		    // Das Shape-Objekt wird von der neuen Turtle-Instanz für die grafische Ausgabe benötigt.
                    //Turtle t = new Turtle(turtle.getTurtleState(), shape);
		    // TODO Vervollständigen
                 
                /*
                 * TODO Fehldende cases ergänzen
                 *
                 * Folgende cases müssen noch hinzugefügt werden:
                 *
                 * - => Turtle dreht sich links
                 *
                 * m => Turtle bewegt sich ohne eine Linie zu zeichnen
                 *
                 * M => Turtle bewegt sich und zeichnet eine Linie
                 *
                 * [ => Aktuelle Turtle wird kopiert und auf den Stack gelegt. Verwenden Sie hierzu den Stack turtles.
                 *
                 * ] => Alte Turtle wird vom Stack geholt.
                 */
            }
        }

    }

    /**
     * Anwenden der Ersetzungsregeln auf das Axiom.
     */
    private String applyRules() {
        String current = axiom.getAxiom();

        //System.err.println(current);
        
        for (int i = 0; i < level.level; i++) {
            String temp = "";
            for (char c : current.toCharArray()) {
                temp += rules.applyReplacementRules(Character.toString(c));
            }
            current = temp;
           // System.err.println(current);
        }
        
        //System.err.println(current);

        String temp = "";
        for (char c : current.toCharArray()) {
            temp += rules.applyDrawingRules(Character.toString(c));
        }
        
        //System.err.println(temp);

        current = temp;
        return current;
    }
}
