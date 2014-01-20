package solutions.ws13.assignment8;

import org.amcgala.agent.AgentClient;

/**
 * 
 Diese Aufgabe ist bis zum achtn Praktikumstermin von Ihnen zu erledigen, auf den Rechnern im "CGA-Pool" im Raum 3.204 vorzuführen und zu erläutern.

Erstellen Sie einen Agenten AmeiSe der das Verhalten einer Ameise simuliert, die nach einem Honigtopf sucht. Der Honigtopf steht auf dem höchsten Berg der virtuellen Landschaft, die Ihnen vom Server übergeben wird. Die Ameise wird vom Framework an einer anderen Stelle der Landschaft, dem Nest, initialisiert. Ihre Ameise hat folgende Möglichkeiten:

    Bewegung auf ein Nachbarfeld - kostet zehn Gebühreneinheiten.
    Erzeugen einer neuen Ameise an der ursprünglichen Ausgangsposition - kostet zwanzig Gebühreneinheiten.
    Erzeugen einer neuen Ameise an einer beliebigen, angebbaren Position - kostet zwanzig Gebühreneinheiten pro übersprungener Zelle
    Ablegen eines Informationsobjekts, das von allen Ameisen von den benachbarten Feldern ausgelesen werden kann - kostet eine Gebühreneinheit (InformationObjects können die aus der Dithering-Aufgabe bekannten sein, oder Sie können sich selber welche definieren).

Eine Ameise, die beim Honigtopf eintrifft, erhält ein spezielles InformationObject Honey, das sie zum Ausgangspunkt zurück transportieren soll. Die Simulation endet nach Ablauf einer festgelegten Zeit (z.B. 3 Minuten).

Ziel der Simulation ist es, möglichst viel Honig mit den geringsten Gesamtkosten zu transportieren. Eine Honig-Einheit ist dabei hundert Gebühreneinheiten wert.
 */
public class Main {
    public static void main(String[] args) {
        new AgentClient("assignment8");
    }
}
