@UserSkillMapGet
Feature: Testing UserSkillMapGetAPI Using CRUD Method

  Scenario: User should not be able to get all users with all skill details by sending GET request with no credentials
    Given user provides baseUri with no authetication details
    When user sends GET request to get all users with all skill details with valid endpoint
    Then user should receive response status code as 401

  Scenario: User should be able to get all users with all skill details by sending GET request with valid credentials
    Given user provides baseUri with valid authetication details
    When user sends GET request to get all users with all skill details with valid endpoint
   Then user should receive response status code as 200
    And user receives response content-type as "aplication/json" 

  Scenario: User should not be able to get single user with skill details by sending GET request with invalid user id
    Given user provides baseUri with valid authetication details
    When user sends GET request to get single user with skill details with valid endpoint and invalid user id
    Then user should receive response status code as 404

  Scenario: User should be able to get single user with skill details by sending GET request with specific user id and valid credentials
    Given user provides baseUri with valid authetication details
    When user sends GET request to get single user with skill details with valid endpoint and specific user id
    Then user should receive response status code as 200
    And user should be able to validate json schema for usersSkillsMapGetAPI
    
      #Scenario: User should be able to get all user details by sending GET request with valid skill id
    #Given user provides baseUri with valid authetication details
    #When user sends GET request to get all user details with valid endpoint and valid skill id
    #Then user should receive response status code as 200
    
  #Scenario: User should not be able to get all user details by sending GET request with invalid skill id
    #Given user provides baseUri with valid authetication details
    #When user sends GET request to get all user details with valid endpoint and invalid skill id
 #		Then user should receive response status code as	 404
#

