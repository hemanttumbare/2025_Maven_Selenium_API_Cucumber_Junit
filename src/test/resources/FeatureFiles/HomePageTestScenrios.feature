Feature:This feature will test HomePage Scenarios

  Background:
    Given Initialize the Browser

  @HomePage @URLVerify
  Scenario: Verify HomePage Logo and Url are displayed
    When User Navigates to HomePage
    Then Verify HomePage Logo And URL are displayed

  @HomePage @URLVerifyFailed
  Scenario: Verify HomePage Logo and Url are displayed Correctly
    When User Navigates to HomePage
    Then Verify HomePage Logo And URL are displayed Correctly

    #Test Jenkins auto trigger one more time +9