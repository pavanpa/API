package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GitHubGetRepoRootPOJO {
	
	 private String name;
	 private String full_name;
	 @JsonProperty("private")
	 private boolean privateVal;
	 private Owner Owner;
	 private String html_url;
	 private String description;
	 private Organization Organization;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFull_name() {
		return full_name;
	}
	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}
	public boolean isPrivateVal() {
		return privateVal;
	}
	public void setPrivateVal(boolean privateVal) {
		this.privateVal = privateVal;
	}
	public Owner getOwner() {
		return Owner;
	}
	public void setOwner(Owner owner) {
		Owner = owner;
	}
	public String getHtml_url() {
		return html_url;
	}
	public void setHtml_url(String html_url) {
		this.html_url = html_url;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Organization getOrganization() {
		return Organization;
	}
	public void setOrganization(Organization organization) {
		Organization = organization;
	}

}
