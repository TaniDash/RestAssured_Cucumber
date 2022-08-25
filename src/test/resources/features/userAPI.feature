@userAPI
Feature: Testing UserAPI Using CRUD Method

  Scenario: User should not be able to get all users by sending GET request with no credentials
    Given user provides baseUri with no authetication details
    When user sends GET request with valid endpoint
    Then user should receive response status code as 401

  Scenario: User should be able to send GET request with valid credentials
    Given user provides baseUri with valid authetication details
    When user sends GET request with valid endpoint
    Then user should receive response status code as 200
    And user receives response content-type as "aplication/json"

  Scenario: User should be able to send GET request by specific user id
    Given user provides baseUri with valid authetication details
    When user sends GET request with valid path parameter
    Then user should receive response status code as 200
    And user should be able to validate the the response body for userAPI
    And user should be able to validate json schema for userAPI

  Scenario: User should not be able to get valid user details by sending GET request with invalid user id
    Given user provides baseUri with valid authetication details
    When user sends GET request with invalid user id as path parameter
    Then user should receive response status code as 404

  Scenario: User should be able to send POST request with valid credentials
    Given user provides baseUri with valid authetication details
    When user sends POST request with valid Json body and header
    Then user should receive response status code as 201
    And user should also receive the message "Successfully Created !!"

  Scenario: User should not be able to create new user by sending invalid request body in POST request
    Given user provides baseUri with valid authetication details
    When user sends POST request with existing phone number
    Then user should receive response status code as 400
    And user should also receive the message "Failed to create new User details as phone number already exists !!"


  Scenario: User should be able to send PUT request with valid credentials
    Given user provides baseUri with valid authetication details
    When user sends PUT request with updated valid Json body providing specific userId as path parameter
    Then user should receive response status code as 201
    And user should also receive the message "Successfully Updated !!" 

  Scenario: User should be able to send DELETE request with valid credentials
    Given user provides baseUri with valid authetication details
    When user sends DELETE request for specific userId
    Then user should receive response status code as 200
    And user should also receive the message "The record has been deleted !!"
    
  Scenario: User should not be able to get already deleted or non-existing user id
    Given user provides baseUri with valid authetication details
    When user sends GET request for non-existing userId
    Then user should receive response status code as 404
    And user should also receive the message "User(non-existing) Not Found !!"
