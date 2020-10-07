Feature: Validating Place API's
@AddPlace @Regression
Scenario Outline: Verify if place is being successfully using addPlace API
Given Add place payload with "<name>" "<language>" "<address>"
When User calls "AddplaceAPI" with https request method "POST"
Then API call is success with status code 200
And "status" in response body is "OK"
And "scope" in response body is "APP"
And Verify place_id created maps to "<name>" using "GetplaceAPI"

Examples:
        |name|language|address|
        |ramu|telugu|IBM street center|
       # |srujana|hindi|Nivas street center|
@DeletePlace @Regression    
Scenario: verify the Delete Place functionality is working
Given DeletePlace payload
When User calls "DeleteplaceAPI" with https request method "POST"
Then API call is success with status code 200
And "status" in response body is "OK"
