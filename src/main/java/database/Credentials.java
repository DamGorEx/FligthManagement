package database;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class Credentials<UT, PT> {
    private UT username;
    private PT password;

    public Credentials(UT username, PT password) {
        this.username = username;
        this.password = password;
    }
}

