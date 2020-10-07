package stepDefinations;

import cucumber.api.java.Before;

public class Hooks {
	@Before("@DeletePlace")
	public void beforeScenario() throws Throwable
	{
		//execute this code only when place id is null
		//write code that will will generate place_id
		stepDefination m=new stepDefination();
		if(stepDefination.place_id==null)
		{
		m.add_place_payload_with("ramu","telugu","Nivas_Heights");
		m.user_calls_with_https_request_method("AddplaceAPI","POST");
		m.verify_place_id_created_maps_to_using("ramu","GetplaceAPI");
		}
	}

}
