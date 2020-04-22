package ru.client.prerevolutiontinderclient.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

	private Long id;

	private String login;

	private String pswd;

	private Integer token;

	private Profile profile;

	private List<Match> matches;

	public User(String login, String pswd, Profile profile, List<Match> matches) {
		this.login = login;
		this.pswd = pswd;
		this.profile = profile;
		this.matches = matches;
	}
	public User() {}

	public Integer getToken() {
		return token;
	}

	public void setToken(Integer token) {
		this.token = token;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPswd() {
		return pswd;
	}

	public void setPswd(String pswd) {
		this.pswd = pswd;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public List<Match> getMatches() {
		return matches;
	}

	public void setMatches(List<Match> matches) {
		this.matches = matches;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", login='" + login + '\'' +
				", pswd='" + pswd + '\'' +
				", token=" + token +
				", profile=" + profile +
				", matches=" + matches +
				'}';
	}
}

