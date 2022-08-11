@UserSkillMapGet
Feature: Testing UserSkillMapGetAPI Using CRUD Method
   
  Scenario: User should be able to get all users by sending GET request with valid credentials
   Given user provides baseUri for userSkillsMap with valid authetication details
   When user sends GET request to get all users by providing valid endpoint
  Then user should receive valid status code as 200
  And user should receive response content-type as "aplication/json"

  Scenario: User should be able to get user with skill details by specific user id
   Given user provides baseUri for userSkillsMap with valid authetication details
   When user sends GET request to get user with skill details by providing user id as path parameter
  Then user should receive valid status code as 200
  And user should be able to validate the specific user id in the response body
  
    Scenario: User should be able to get all user details by providing specific skill id
   Given user provides baseUri for userSkillsMap with valid authetication details
   When user sends GET request to get all user details by providing skill id as path parameter
  Then user should receive valid status code as 200
  And user should be able to validate the skill id in the response body
  
    #Scenario: User should be able to get all users by sending GET request with valid credentials
   #Given user provides baseUri for userSkillsMap with valid authetication details
   #When user sends GET request to get all users by providing valid endpoint
  #Then user should receive valid status code as 200
  #And user should receives response content-type as "aplication/json"
  
    #Scenario: User should be able to get all users by sending GET request with valid credentials
   #Given user provides baseUri for userSkillsMap with valid authetication details
   #When user sends GET request to get all users by providing valid endpoint
  #Then user should receive valid status code as 200
  #And user should receives response content-type as "aplication/json"

