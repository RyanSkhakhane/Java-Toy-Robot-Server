package za.co.wethinkcode.RobotClient.ClientCommands;

public abstract class ClientCommands implements ClientCommandsInterface{

    public abstract String execute();

    public static ClientCommands create(String instruction) {
        String[] args = instruction.toLowerCase().trim().split(" ");
        switch (args[0]) {
            case "launch":
                System.out.println("Launch command passed");
                return new Launch();
            case "look":
                System.out.println("look command passed");
                return new Look();
            case "state":
                System.out.println("state command passed");
                return new State();
            default:
                throw new IllegalArgumentException("Unsupported command: " + instruction);
        }
    }
}
