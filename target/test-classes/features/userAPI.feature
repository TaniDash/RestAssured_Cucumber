Feature: Testing UserAPI Using CRUD Method

 Background:
   Given user provides baseUri with valid authetication details
   
  Scenario: User should be able to send GET request with valid credentials
  When user sends GET request with valid endpoint 
  Then user receives valid status code as 200
  And user receives response content-type as "aplication/json"

Scenario: User should be able to send GET request by specific user id
When user sends GET request with valid path parameter
Then user receives valid status code as 200
And user should be able to validate the user id in the response body

Scenario: User should be able to send POST request with valid credentials
When user sends POST request with valid Json body
Then user gets valid status code as 201
  And user receives response content-type as "aplication/json"
And user should be able to validate the response body 
And user should be able to validate the message "Successfully Created !!" in the response body

Scenario: User should be able to send PUT request with valid credentials
When user sends PUT request with updated valid Json body providing specific userId as path parameter
Then user gets valid status code as 201
  And user receives response content-type as "aplication/json"
And user should be able to validate the updated response body 
And user should be able to validate the updated message "Successfully Updated !!" in the response body

Scenario: User should be able to send DELETE request with valid credentials
When user sends DELETE request for specific userId 
#Then user receives valid status code as 200
  And user receives response content-type as "aplication/json"
And user should be able to validate the deleted message "The record has been deleted !!" in the response body