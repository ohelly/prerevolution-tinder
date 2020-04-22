package ru.client.prerevolutiontinderclient.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Profile implements Serializable {

	private Long id;

	private String name;

	private String description;

	private String sex;

	public Profile(String name, String description, String sex) {
		this.name = name;
		this.description = description;
		this.sex = sex;
	}

	public Profile() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Override
	public String toString() {
		return "Profile{" +
				"id=" + id +
				", name='" + name + '\'' +
				", description='" + description + '\'' +
				", sex='" + sex + '\'' +
				'}';
	}
}
