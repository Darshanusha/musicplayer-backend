Feature: UserService

  Scenario: Find User by user id
    Given user searches with id 1
    Then "Test User" has to be returned