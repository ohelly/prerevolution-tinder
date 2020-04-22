package ru.server.prerevolutiontinderserver.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "USER")
public class User {

	@Id
	@GeneratedValue(generator = "increment")
	@Column(name = "ID")
	private Long id;

	@Column(name = "LOGIN")
	private String login;

	@Column(name = "PSWD")
	private String pswd;

	@Column(name = "TOKEN")
	private Integer token;

	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "profile_id")
	private Profile profile;

	@OneToMany(cascade = {CascadeType.ALL})
	@JoinColumn(name = "id_user_from")
	private List<Match> matches;

	public User(String login, String pswd, Integer token, Profile profile, List<Match> matches) {
		this.login = login;
		this.pswd = pswd;
		this.token = token;
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
