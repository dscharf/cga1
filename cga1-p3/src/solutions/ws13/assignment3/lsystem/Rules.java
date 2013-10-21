package solutions.ws13.assignment3.lsystem;

import java.util.HashMap;
import java.util.Map;

/**
 * Die Ersetzungsregeln eines {@link solutions.ws13.assignment3.lsystem.LindenmayerSystem}.
 *
 * @author Robert Giacinto
 * @since 2.0
 */
public class Rules {
    private Map<String, String> replacementRules;
    private Map<String, String> drawingRules;

    /**
     * Erzeugt eine leere Regelinstanz.
     */
    public Rules() {
        replacementRules = new HashMap<>();
        drawingRules = new HashMap<>();
    }

    /**
     * Fügt eine neue Regel dem L-System hinzu. Diese Regel wird während der Produktion des finalen L-Systems aufgerufen.
     * @param symbol das Symbol im L-System, das ersetzt werden soll
     * @param replacement das neue Symbol, das dem String hinzugefügt werden soll
     * @return das Regelobjekt
     */
    public Rules addReplacementRule(String symbol, String replacement) {
        replacementRules.put(symbol, replacement);
        return this;
    }

    /**
     * Fügt eine neue Regel hinzu, die am Ende der Produktion des L-Systems angewendet wird. Diese Regeln werden verwendet,
     * um Variablen auf Konstanten (Linksdrehung, Rechtsdrehung, etc.) abzubilden.
     * @param symbol
     * @param replacement
     * @return
     */
    public Rules addDrawingRule(String symbol, String replacement) {
        drawingRules.put(symbol, replacement);
        return this;
    }

    /**
     * Wendet eine Regel auf ein Zeichen im L-System String an. Sollte es keine passende Regel geben, wird das übergebene
     * Symbol zurückgegeben.
     * @param symbol das Symbol, für das eine Ersetzungsregel angewendet werden soll
     * @return das ersetzte Symbol
     */
    public String applyReplacementRules(String symbol) {
        return replacementRules.containsKey(symbol) ? replacementRules.get(symbol) : symbol;
    }
    /**
     * Wendet eine Regel auf ein Zeichen im L-System String an. Sollte es keine passende Regel geben, wird das übergebene
     * Symbol zurückgegeben.
     * @param symbol das Symbol, für das eine Ersetzungsregel angewendet werden soll
     * @return das ersetzte Symbol
     */
    public String applyDrawingRules(String symbol) {
        return drawingRules.containsKey(symbol) ? drawingRules.get(symbol) : symbol;
    }
}
