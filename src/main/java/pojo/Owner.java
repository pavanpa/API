package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Owner {
	private String login;
	private String type;
	private boolean site_admin;
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public boolean isSite_admin() {
		return site_admin;
	}
	public void setSite_admin(boolean site_admin) {
		this.site_admin = site_admin;
	}

}
