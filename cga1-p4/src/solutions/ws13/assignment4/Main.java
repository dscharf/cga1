package solutions.ws13.assignment4;

import org.amcgala.agent.AgentClient;
import org.amcgala.agent.AgentServer;


public class Main {
    public static void main(String[] args) throws Exception {
        new AgentServer("assignment4");
        Thread.sleep(2000);
        new AgentClient("assignment4");
    }
}
