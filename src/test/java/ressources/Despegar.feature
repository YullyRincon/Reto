Feature: Despegar reservation page
  Scenario: Do a flight reservation
    Given The website is ready and loaded
    When Departure is " MDE" and Destination is " BOG" and dates Between the 15 and 25
    Then Despegar should select the first flight available and goes to the pay page