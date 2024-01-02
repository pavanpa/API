package pojo;

public class GitHubCreateRepoPOJO {
	
	private String name;
	private String description;
	private boolean privateVal;
	
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isPrivate() {
		return privateVal;
	}

	public void setPrivate(boolean privateVal) {
		this.privateVal = privateVal;
	}

	
}
