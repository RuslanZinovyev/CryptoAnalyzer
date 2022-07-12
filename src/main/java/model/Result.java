package model;

public class Result {
    private String message;
    private int securityKey;

    public Result(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getSecurityKey() {
        return securityKey;
    }

    public void setSecurityKey(int securityKey) {
        this.securityKey = securityKey;
    }
}
