package main.airport;

import main.airport.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PassengerTest {
    Flight economy, business, premium;
    Passenger usual, vip;

    @BeforeEach
    void init() {
        economy = new EconomyFlight(1L, 2000);
        business = new BusinessFlight(2L, 1500);
        premium = new PremiumFlight(3L, 15000);
        usual = new Passenger(1L, "apple", "apple", 123, 123, false);
        vip = new Passenger(2L, "vip", "vip", 123, 123, true);
    }

    @DisplayName("Given we have usual passenger")
    @Nested
    class UsualPassengerTest {
        @DisplayName("When we have any flight associated with him")
        @Nested
        public class AnyFlightTest {
            @DisplayName("Then we can calculate his points")
            @Test
            void usualPassengerAnyFlightTest() {
                var points = usual.getPoints();
                assertEquals(0, points);
                assertTrue(economy.addPassenger(usual));
                assertEquals(economy.getMiles() / 20, usual.getPoints());
                economy.removePassenger(usual);
                assertEquals(0, usual.getPoints());
            }
        }

        @DisplayName("When we have economy flight")
        @Nested
        class EconomyFlightTest {
            @DisplayName("Then we can add and remove it from passenger")
            @Test
            void usualPassengerEconomyFlightAddRemoveTest() {
                assertTrue(usual.addFlight(economy));
                assertAll("Verify if bidirectional association is correct",
                        () -> assertTrue(usual.getFlights().contains(economy)),
                        () -> assertTrue(economy.getPassengerSet().contains(usual))
                );
                assertTrue(usual.removeFlight(economy));
                assertAll("Verify if bidirectional association is correct",
                        () -> assertFalse(usual.getFlights().contains(economy)),
                        () -> assertFalse(economy.getPassengerSet().contains(usual))
                );
            }
        }

        @DisplayName("When we have business flight")
        @Nested
        class BusinessFlightTest {
            @DisplayName("Then we cannot add it to passenger")
            @Test
            void usualPassengerBusinessFlightAddRemoveTest() {
                assertFalse(usual.addFlight(business));
                assertAll("Verify if bidirectional association is correct",
                        () -> assertFalse(usual.getFlights().contains(business)),
                        () -> assertFalse(business.getPassengerSet().contains(usual))
                );
                assertFalse(usual.removeFlight(business));
            }
        }

        @DisplayName("When we have premium flight")
        @Nested
        class PremiumFlightTest {

            @DisplayName("Then we cannot add or remove it from passenger")
            @Test
            void usualPassengerPremiumFlightAddRemoveTest() {
                assertFalse(usual.addFlight(premium));
                assertAll("Verify if bidirectional association is correct",
                        () -> assertFalse(usual.getFlights().contains(premium)),
                        () -> assertFalse(premium.getPassengerSet().contains(usual))
                );
                assertFalse(usual.removeFlight(premium));
            }
        }
    }

    @DisplayName("Given we have vip passenger")
    @Nested
    class VipPassengerTest {
        @DisplayName("When we have any flight associated with him")
        @Nested
        public class AnyFlightTest {
            @DisplayName("Then we can calculate his points")
            @Test
            void usualPassengerAnyFlightTest() {
                var points = vip.getPoints();
                assertEquals(0, points);
                assertTrue(premium.addPassenger(vip));
                assertEquals(premium.getMiles() / 10, vip.getPoints());
                premium.removePassenger(vip);
                assertEquals(0, vip.getPoints());
            }
        }

        @DisplayName("When we have economy flight")
        @Nested
        class EconomyFlightTest {
            @DisplayName("Then we can add but cannot remove it from passenger")
            @Test
            void usualPassengerEconomyFlightAddRemoveTest() {
                assertTrue(vip.addFlight(economy));
                assertAll("Verify if bidirectional association is correct",
                        () -> assertTrue(vip.getFlights().contains(economy)),
                        () -> assertTrue(economy.getPassengerSet().contains(vip))
                );
                assertFalse(vip.removeFlight(economy));
                assertAll("Verify if bidirectional association is correct",
                        () -> assertTrue(vip.getFlights().contains(economy)),
                        () -> assertTrue(economy.getPassengerSet().contains(vip))
                );
            }
        }

        @DisplayName("When we have business flight")
        @Nested
        class BusinessFlightTest {
            @DisplayName("Then we can add but cannot remove it from passenger")
            @Test
            void vipPassengerBusinessFlightAddRemoveTest() {
                assertTrue(vip.addFlight(business));
                assertAll("Verify if bidirectional association is correct",
                        () -> assertTrue(vip.getFlights().contains(business)),
                        () -> assertTrue(business.getPassengerSet().contains(vip))
                );
                assertFalse(vip.removeFlight(business));
                assertAll("Verify if bidirectional association is correct",
                        () -> assertTrue(vip.getFlights().contains(business)),
                        () -> assertTrue(business.getPassengerSet().contains(vip))
                );
            }
        }

        @DisplayName("When we have premium flight")
        @Nested
        class PremiumFlightTest {

            @DisplayName("Then we can add and remove it from passenger")
            @Test
            void vipPassengerPremiumFlightAddRemoveTest() {
                assertTrue(vip.addFlight(premium));
                assertAll("Verify if bidirectional association is correct",
                        () -> assertTrue(vip.getFlights().contains(premium)),
                        () -> assertTrue(premium.getPassengerSet().contains(vip))
                );
                assertTrue(vip.removeFlight(premium));
            }
        }
    }
}