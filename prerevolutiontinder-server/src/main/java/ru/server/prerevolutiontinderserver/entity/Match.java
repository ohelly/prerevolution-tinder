package ru.server.prerevolutiontinderserver.entity;

import javax.persistence.*;

@Entity
@Table(name = "MATCHES")
public class Match {

	@Id
	@GeneratedValue(generator = "increment")
	@Column(name = "ID")
	private Long id;

	@Column(name = "ID_PROFILE_TO")
	private Long idProfileTo;

	@Column(name = "CHOICE")
	private Long choice;

	public Match(Long idProfileTo, Long choice) {
		this.idProfileTo = idProfileTo;
		this.choice = choice;
	}

	public Match() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdProfileTo() {
		return idProfileTo;
	}

	public void setIdProfileTo(Long idProfileTo) {
		this.idProfileTo = idProfileTo;
	}

	public Long getChoice() {
		return choice;
	}

	public void setChoice(Long choice) {
		this.choice = choice;
	}

	@Override
	public String toString() {
		return "Match{" +
				"id=" + id +
				", idProfileTo=" + idProfileTo +
				", choice=" + choice +
				'}';
	}

}
