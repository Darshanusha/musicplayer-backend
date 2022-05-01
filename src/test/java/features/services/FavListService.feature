Feature: Favourite song list feature
  Scenario Outline: Add song to favorite list
    Given List of favorite song(s)
    When Add song "<musicid> <music>" to List of songs
    Then List of favorite song(s) should contain "<music>"
    Examples:
    |musicid| music    |
    |1      | Test_song|
