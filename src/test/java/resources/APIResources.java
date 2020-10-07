package resources;

public enum APIResources {
	AddplaceAPI("/maps/api/place/add/json"),
			DeleteplaceAPI("/maps/api/place/delete/json"),
			GetplaceAPI("/maps/api/place/get/json");
private String resource;
			APIResources(String resource) {
				this.resource=resource;
			}
			public String getResource()
			{
				return resource;
			}

}
