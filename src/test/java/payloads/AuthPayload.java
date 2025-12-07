package payloads;

public class AuthPayload {
    public String username;
    public String password;

    public AuthPayload() {}

    public AuthPayload(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
