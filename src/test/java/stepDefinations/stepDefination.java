package stepDefinations;

import java.util.ArrayList;
import java.util.List;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class stepDefination extends Utils {
	Response response;
	RequestSpecification res;
	ResponseSpecification resspec;
	TestDataBuild data = new TestDataBuild();
	static String place_id;
	@Given("^Add place payload with \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	public void add_place_payload_with(String name, String language, String address) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		
        		 res=given().spec(requestSpecification())
        		.body(data.addPlacePayload(name,language,address));
        		 System.out.println("given keyword is updated");
        		
	}

	@When("^User calls \"([^\"]*)\" with https request method \"([^\"]*)\"$")
	public void user_calls_with_https_request_method(String resource, String method) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		APIResources resourceAPI=APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResource());
		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		if(method.equalsIgnoreCase("POST"))
			
		response =res.when().post(resourceAPI.getResource());
		else if(method.equalsIgnoreCase("GET"))
			response =res.when().post(resourceAPI.getResource());	
        		System.out.println("when keyword is updated");
		
	}

	@Then("^API call is success with status code (\\d+)$")
	public void api_call_is_success_with_status_code(int arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	 assertEquals(response.getStatusCode(),200);
	 
	}

	@Then("^\"([^\"]*)\" in response body is \"([^\"]*)\"$")
	public void in_response_body_is(String keyValue, String expectedValue) throws Throwable {
	       
	    assertEquals(getJsonPath(response,keyValue),expectedValue);
	    
	}
	@Then("^Verify place_id created maps to \"([^\"]*)\" using \"([^\"]*)\"$")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws Throwable {
	    //request spec
		place_id=getJsonPath(response,"place_id"); 
		res=given().spec(requestSpecification()).queryParam("place_id",place_id );
		user_calls_with_https_request_method(resource,"GET");
		String actualName=getJsonPath(response,"name");
		assertEquals(actualName,expectedName);
		
	    
	}
	
	@Given("^DeletePlace payload$")
	public void deleteplace_payload() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		 res=given().spec(requestSpecification())
	        		.body(data.deletePlace(place_id));
	}
}
