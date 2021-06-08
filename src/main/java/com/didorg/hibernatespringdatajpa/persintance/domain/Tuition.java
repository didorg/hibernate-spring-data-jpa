package com.didorg.hibernatespringdatajpa.persintance.domain;

import javax.persistence.*;

@Entity
@Table(name = "tuition")
public class Tuition {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Double tuition_fee;

	// Tuition is the owner of the relationship, owner of that FK (owning side)
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "student_id")
	private Student student;
	
	public Tuition() {
	}
	
	public Tuition(Double tuition_fee) {
		this.tuition_fee = tuition_fee;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Double getTuition_fee() {
		return tuition_fee;
	}
	public void setTuition_fee(Double tuition_fee) {
		this.tuition_fee = tuition_fee;
	}

	@Override
	public String toString() {
		return "Tuition{" +
				"id=" + id +
				", fee=" + tuition_fee +
				", student=" + student +
				'}';
	}
}
