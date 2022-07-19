package za.co.wethinkcode.robotServer.RobotWorld.ClientCommands;

public class ErrorResponseJson {

    String result;
    Forward.DataJson data;

    public ErrorResponseJson(String result, Forward.DataJson data){
        this.result = result;
        this.data = data;
    }
}
