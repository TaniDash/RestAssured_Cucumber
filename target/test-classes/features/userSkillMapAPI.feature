@userSkillMapAPI
Feature: Testing UserSkillMapAPI Using CRUD Method

  Scenario: User should not be able to get all user-skill data by sending GET request with no authetication
    Given user provides baseUri with no authetication details
    When user sends GET request with valid endpoint for userSKillMapAPI
    Then user should receive response status code as 401

  Scenario: User should be able to get all user-skill data by sending GET request with valid authetication
    Given user provides baseUri with valid authetication details
    When user sends GET request with valid endpoint for userSKillMapAPI
    Then user should receive response status code as 200
    And user receives response content-type as "aplication/json"

  Scenario: User should be able to get user-skill data by sending GET request with valid single user skill id
    Given user provides baseUri with valid authetication details
    When user sends GET request with valid user skill id as path parameter
    Then user should receive response status code as 200
    And user should be able to validate json schema for userSkillMapAPI

  Scenario: User should not be able to get user-skill data by sending GET request with invalid single user skill id
    Given user provides baseUri with valid authetication details
    When user sends GET request with invalid user skill id as path parameter
    Then user should receive response status code as 404

  Scenario: User should be able to create a new user skill mapping detail by sending POST request with valid data
    Given user provides baseUri with valid authetication details
    When user sends POST request with valid Json body and header for userSkillMapAPI
    Then user should receive response status code as 201
    And user should be able to validate the response body for userSkillMapAPI
    And user should also receive the message "Successfully Created !!"

  Scenario: User should not be able to create a new user skill mapping detail by sending POST request with invalid data
  Given user provides baseUri with valid authetication details
  When user sends POST request with invalid Json body and header for userSkillMapAPI
  Then user should receive response status code as 404
  
  Scenario: User should not able to update an existing user skill mapping detail by sending PUT request
    Given user provides baseUri with valid authetication details
    When user sends PUT request with valid users skill id as path parameter
    Then user should receive response status code as 201
    And user should also receive the message "Successfully Updated !!"
    
      Scenario: User should not able to update an existing user skill mapping detail by sending PUT request
    Given user provides baseUri with valid authetication details
    When user sends PUT request with invalid path parameter
    Then user should receive response status code as 404
    
  Scenario: User should be able to send DELETE request for exising user skill mapping detail
    Given user provides baseUri with valid authetication details
    When user sends DELETE request for specific users skill id
   Then user should receive response status code as 200
    And user should also receive the message "The record has been deleted !!"
       
   Scenario: User should not be able to get already deleted or non-existing user id
    Given user provides baseUri with valid authetication details
     When user sends DELETE request for already deleted or non-existing users skill id
   Then user should receive response status code as 404

