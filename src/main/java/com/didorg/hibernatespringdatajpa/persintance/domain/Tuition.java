package com.didorg.hibernatespringdatajpa.persintance.domain;

import javax.persistence.*;

@Entity
@Table(name = "tuition")
public class Tuition {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Double fee;

	// Tuition is the owner of the relationship, owner of that FK (owning side)
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "student_id")
	private Student student;
	
	public Tuition() {
	}
	
	public Tuition(Double fee) {
		this.fee = fee;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Double getFee() {
		return fee;
	}
	public void setFee(Double fee) {
		this.fee = fee;
	}

	@Override
	public String toString() {
		return "Tuition{" +
				"id=" + id +
				", fee=" + fee +
				", student=" + student +
				'}';
	}
}
