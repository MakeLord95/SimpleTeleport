package fi.makelord95.simpleteleport;

public class TeleportRequest {
    private String sender = null;
    private String target = null;
    private String type = null;
    private static TeleportRequest instance = null;

    public static TeleportRequest getInstance() {
        if (instance == null) {
            instance = new TeleportRequest();
        }
        return instance;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
