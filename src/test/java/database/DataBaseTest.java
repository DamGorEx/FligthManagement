package database;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@DisplayName("Given Database")
@ExtendWith(MockitoExtension.class)
class DataBaseTest {

    @Spy
    DataBase<String, String> dataBase = new DataBase<>();

    Credentials<String, String> credentials = new Credentials<>("user1", "pass");

    @DisplayName("When having credentials")
    @Nested
    class CredentialTest {

        @DisplayName("Then we can log in successfully with the same credentials")
        @Test
        void databaseCredentialsSuccessfulLoginTest() {
            when(dataBase.login(credentials)).thenReturn(true);
            Credentials<String, String> credentials2 = new Credentials<>("user1", "pass");
            assertTrue(dataBase.login(credentials2));
        }

        @DisplayName("Then we can log in successfully with the same credentials")
        @Test
        void databaseCredentialsUnSuccessfulLoginTest() {
            Credentials<String, String> credentials2 = new Credentials<>("user1123", "pass");
            assertFalse(dataBase.login(credentials2));
            Credentials<String, String> credentials3 = new Credentials<>("user1", "pass123");
            assertFalse(dataBase.login(credentials3));
        }
    }

}