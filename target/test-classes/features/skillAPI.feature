@skillAPI
Feature: Testing UserAPI Using CRUD Method

  Scenario: User should not be able to get all skill data by sending GET request with no authetication
    Given user provides baseUri with no authetication details
    When user sends GET request with valid endpoint for skillAPI
    Then user should receive response status code as 401

  Scenario: User should be able to get all skill data by sending GET request with valid authetication
    Given user provides baseUri with valid authetication details
    When user sends GET request with valid endpoint for skillAPI
    Then user should receive response status code as 200
    And user receives response content-type as "aplication/json"

  Scenario: User should be able to get skill data by sending GET request for specific skill id
    Given user provides baseUri with valid authetication details
    When user sends GET request with valid endpoint and specific skill id
    Then user should receive response status code as 200
  	And user should be able to validate the skill id in the response body
    And user should be able to validate json schema for skillAPI

  Scenario: User should be able to create new skill detail by sending POST request
    Given user provides baseUri with valid authetication details
    When user sends POST request for skillAPI with valid Json body and header
    Then user should receive response status code as 201
    And user should be able to find the new skill detail when he gets the new skill id
    And user should also receive the message "Successfully Created !!"

  Scenario: User should not be able to create new user by sending already existing skill id
    Given user provides baseUri with valid authetication details
    When user sends POST request for skillAPI with valid Json body and header already existing
    Then user should receive response status code as 400
    And user should also receive the message "Failed to create new Skill details as Skill already exists !!"

  Scenario: User should be able to send PUT request with valid credentials
    Given user provides baseUri with valid authetication details
    When user sends PUT request with updated valid Json body and specific skill id
    Then user should receive response status code as 201
    And user should also receive the message "Successfully Updated !!"
    
     Scenario: User should not be able to send PUT request with invalid data
    Given user provides baseUri with valid authetication details
    When user sends PUT request with updated valid Json body and invalid skill id
    Then user should receive response status code as 404
    And user should also receive the message "Skill(id- 0) Not Found !!"

  Scenario: User should be able to send DELETE request with valid credentials
    Given user provides baseUri with valid authetication details
    When user sends DELETE request for specific skill id
    Then user should receive response status code as 200
    And user should also receive the message "The record has been deleted !!" 

  Scenario: User should not be able to get already deleted or non-existing skill id
    Given user provides baseUri with valid authetication details
    When user sends GET request for already deleted or non-existing skill id
    Then user should receive response status code as 404
    And get request to the skill id should not return the skill id
    And user should also receive the message "Skill(id- nonExisting) Not Found !!"
