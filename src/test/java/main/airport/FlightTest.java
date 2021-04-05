package main.airport;

import main.airport.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class FlightTest {

    Flight economy;
    Flight business;
    Flight premium;
    Passenger usual;
    Passenger vip;

    @BeforeEach
    void init() {
        economy = new EconomyFlight(1L, 1000);
        business = new main.airport.BusinessFlight(1L, 500);
        premium = new PremiumFlight(1L, 1500);
        usual = new Passenger(1L, "apple", "usual", null, null, false);
        vip = new Passenger(1L, "apple", "vip", null, null, true);
    }

    @DisplayName("Given there is a any flight")
    @Nested
    class AnyFlight {
        @DisplayName("When we have any passenger")
        @Nested
        class AnyPassengerTest {
            @DisplayName("Then we cannot disassociate passenger that was not associated with this flight")
            @Test
            void anyFlightAnyPassanger() {
                assertFalse(economy.removePassenger(usual));
            }
        }
    }


    @DisplayName("Given there is a economy flight")
    @Nested
    class EconomyFlightTest {

        @DisplayName("When we have usual passenger")
        @Nested
        class UsualPassengerTest {

            @DisplayName("Then we can add and remove him from flight")
            @Test
            public void economyFlightUsualPassengerAddRemoveTest() {
                economy.addPassenger(usual);
                assertAll("Verify if bidirectional association is correct",
                        () -> assertTrue(economy.getPassengerSet().contains(usual)),
                        () -> assertTrue(usual.getFlights().contains(economy))
                );
                assertTrue(economy.removePassenger(usual));
                assertAll("Verify if bidirectional association is correct",
                        () -> assertFalse(economy.getPassengerSet().contains(usual)),
                        () -> assertFalse(usual.getFlights().contains(economy))
                );
            }

            @DisplayName("Then we can add him only once")
            @RepeatedTest(2)
            public void economyFlightUsualPassengerAddOnlyOnceTest(RepetitionInfo repetitionInfo) {
                for (var i = 0; i < repetitionInfo.getCurrentRepetition(); i++) {
                    economy.addPassenger(usual);
                }
                assertAll("Verify usual passenger can be add only one",
                        () -> assertEquals(1, economy.getPassengerSet().size()),
                        () -> assertTrue(economy.getPassengerSet().contains(usual))
                );

            }
        }

        @DisplayName("When we have vip passenger")
        @Nested
        class VIPPassengerTest {

            @DisplayName("Then we can add him but cannot remove him from the flight")
            @Test
            public void economyFlightVipPassengerAddRemoveTest() {
                assertTrue(economy.addPassenger(vip));
                assertAll("Verify if bidirectional association is correct",
                        () -> assertTrue(economy.getPassengerSet().contains(vip)),
                        () -> assertTrue(vip.getFlights().contains(economy))
                );
                assertFalse(economy.removePassenger(vip));
            }

            @DisplayName("Then we can add him only once")
            @RepeatedTest(2)
            public void economyFlightVipPassengerAddOnlyOnceTest(RepetitionInfo repetitionInfo) {
                for (var i = 0; i < repetitionInfo.getCurrentRepetition(); i++) {
                    economy.addPassenger(vip);
                }
                assertAll("Verify vip passenger can be add only one",
                        () -> assertEquals(1, economy.getPassengerSet().size()),
                        () -> assertTrue(economy.getPassengerSet().contains(vip))
                );
            }

            @DisplayName("Then we can calculate his flight points")
            @Test
            public void economyFlightVipPassengerCalculatePointsTest() {
                var points = vip.getPoints();
                assertEquals(0, points);
                economy.addPassenger(vip);
                assertEquals(economy.getMiles() / 10, vip.getPoints());
            }
        }
    }

    @DisplayName("Given there is a business flight")
    @Nested
    class BusinessFlight {

        @DisplayName("When we have usual passenger")
        @Nested
        class UsualPassengerTest {
            @DisplayName("Then we cannot add him")
            @Test
            public void businessFlightUsualPassengerTest() {
                assertFalse(business.addPassenger(usual));
                assertAll("Verify if bidirectional association is correct",
                        () -> assertFalse(economy.getPassengerSet().contains(vip)),
                        () -> assertFalse(vip.getFlights().contains(economy))
                );
            }

            @DisplayName("Then we can calculate his flight points")
            @Test
            public void businessFlightUsualPassengerCalculatePointsTest() {
                var points = usual.getPoints();
                assertEquals(0, points);
                business.addPassenger(usual);
                assertEquals(0, usual.getPoints());
            }
        }

        @DisplayName("When we have vip passenger")
        @Nested
        class VIPPassengerTest {
            @DisplayName("Then we can add him but cannot remove him")
            @Test
            public void businessFlightVipPassengerAddRemoveTest() {
                assertTrue(business.addPassenger(vip));
                assertEquals(1, business.getPassengerSet().size());
                assertFalse(business.removePassenger(vip));
                assertAll("Verify if bidirectional association is correct",
                        () -> assertTrue(business.getPassengerSet().contains(vip)),
                        () -> assertTrue(vip.getFlights().contains(business))
                );
            }

            @DisplayName("Then we can add him only once")
            @RepeatedTest(2)
            public void businessFlightVipPassengerAddOnlyOnceTest(RepetitionInfo repetitionInfo) {
                for (var i = 0; i < repetitionInfo.getCurrentRepetition(); i++) {
                    business.addPassenger(vip);
                }
                assertAll("Verify usual passenger can be add only one",
                        () -> assertEquals(1, business.getPassengerSet().size()),
                        () -> assertTrue(business.getPassengerSet().contains(vip))
                );
            }

            @DisplayName("Then we can calculate his flight points")
            @Test
            public void businessFlightVIPPassengerCalculatePointsTest() {
                var points = vip.getPoints();
                assertEquals(0, points);
                business.addPassenger(vip);
                assertAll("Verify if bidirectional association is correct",
                        () -> assertTrue(business.getPassengerSet().contains(vip)),
                        () -> assertTrue(vip.getFlights().contains(business))
                );
                assertEquals(business.getMiles() / 10, vip.getPoints());
            }
        }
    }

    @DisplayName("Given there is a premium flight")
    @Nested
    class PremiumFlightTest {

        @DisplayName("When we have usual passenger")
        @Nested
        class UsualPassengerTest {
            @DisplayName("Then we cannot add or remove him from the flight")
            @Test
            void premiumFlightUsualPassengerAddRemoveTest() {
                assertAll("Verify all condition for premium flight and usual passenger",
                        () -> assertFalse(premium.addPassenger(usual)),
                        () -> assertEquals(0, premium.getPassengerSet().size()),
                        () -> assertFalse(premium.removePassenger(usual)),
                        () -> assertEquals(0, premium.getPassengerSet().size())
                );
            }

            @DisplayName("Then we can calculate his flight points")
            @Test
            public void premiumFlightUsualPassengerCalculatePointsTest() {
                var points = usual.getPoints();
                assertEquals(0, points);
                premium.addPassenger(usual);
                assertEquals(0, usual.getPoints());
            }
        }

        @DisplayName("When we have vip passenger")
        @Nested
        class VipPassengerTest {
            @DisplayName("Then we can add him or remove him from the flight")
            @Test
            void premiumFlightVipPassengerAddRemoveTest() {
                assertAll("Verify all condition for premium flight and vip passenger",
                        () -> assertTrue(premium.addPassenger(vip)),
                        () -> assertEquals(1, premium.getPassengerSet().size())
                );
                assertAll("Verify if bidirectional association is correct",
                        () -> assertTrue(premium.getPassengerSet().contains(vip)),
                        () -> assertTrue(vip.getFlights().contains(premium))
                );
                assertAll(
                        () -> assertTrue(premium.removePassenger(vip)),
                        () -> assertEquals(0, premium.getPassengerSet().size())
                );
                assertAll("Verify if bidirectional association is correct",
                        () -> assertFalse(premium.getPassengerSet().contains(vip)),
                        () -> assertFalse(vip.getFlights().contains(premium))
                );
            }

            @DisplayName("Then we can add him only once")
            @RepeatedTest(2)
            public void businessFlightVipPassengerAddOnlyOnceTest(RepetitionInfo repetitionInfo) {
                for (var i = 0; i < repetitionInfo.getCurrentRepetition(); i++) {
                    premium.addPassenger(vip);
                }
                assertAll("Verify usual passenger can be add only one",
                        () -> assertEquals(1, premium.getPassengerSet().size()),
                        () -> assertTrue(premium.getPassengerSet().contains(vip))
                );
            }

            @DisplayName("Then we can calculate his flight points")
            @Test
            public void premiumFlightVIPPassengerCalculatePointsTest() {
                var points = vip.getPoints();
                assertEquals(0, points);
                premium.addPassenger(vip);
                assertAll("Verify if bidirectional association is correct",
                        () -> assertTrue(premium.getPassengerSet().contains(vip)),
                        () -> assertTrue(vip.getFlights().contains(premium))
                );
                assertEquals(premium.getMiles() / 10, vip.getPoints());
            }
        }
    }
}