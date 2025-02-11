Feature:This feature will test HomePage Scenarios

  Background:
    Given Initialize the Browser

  @HomePage
  Scenario: Verify HomePage Logo and Url are displayed
    When User Navigates to HomePage
    Then Verify HomePage Logo And URL are displayed

    #Test Jenkins auto trigger one more time +6