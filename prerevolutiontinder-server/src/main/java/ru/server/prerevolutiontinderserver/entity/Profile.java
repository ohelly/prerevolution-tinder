package ru.server.prerevolutiontinderserver.entity;

import javax.persistence.*;

@Entity
@Table(name = "PROFILE")
public class Profile {

	@Id
	@GeneratedValue(generator = "increment")
	@Column(name = "ID")
	private Long id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "SEX")
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
