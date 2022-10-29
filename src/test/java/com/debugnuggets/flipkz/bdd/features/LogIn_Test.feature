@Authorization
Feature: Login Action

@LogIn
Scenario Outline:Successful Login with Valid Credentials
  Given user is on home page
  When user navigate to log in page
  And user enters username <username>
  And user enters password <password>
  And user clicks login button
  Then user name is displayed

Examples:
  | username        | password |
  | 8 705 296 60 64 | testPassword123 |

@LogOut
Scenario Outline: Successful LogOut after log in
  Given user is on home page
  When user navigate to log in page
  And user enters username <username>
  And user enters password <password>
  And user clicks login button
  And user clicks log out button
  Then user name is displayed

    Examples:
      | username        | password |
      | 8 705 296 60 64 | testPassword123 |