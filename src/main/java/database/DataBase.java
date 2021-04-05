package database;

import java.util.HashMap;
import java.util.Map;

public class DataBase<UT, PT> {
    private final Map<UT, PT> registeredUsers = new HashMap<>();

    public boolean login(Credentials<UT, PT> credentials) {
        UT username = credentials.getUsername();
        PT password = credentials.getPassword();
        return password.equals(registeredUsers.computeIfPresent(username, (user, pass) -> pass));
    }
}
