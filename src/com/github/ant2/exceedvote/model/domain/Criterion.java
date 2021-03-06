package com.github.ant2.exceedvote.model.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * A criterion which voter can vote for.
 * 
 * @author Artima Mahahemarat
 */
@Entity
public class Criterion extends Model implements VoteEvent.Part {

	/** criterion name */
	private String name;

	@ManyToOne private VoteEvent voteEvent;

	/**
	 * Constructs a criterion.
	 */
	public Criterion() {
	}

	/**
	 * Returns criterion name.
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets criterion name.
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public VoteEvent getVoteEvent() {
		return voteEvent;
	}

	public void setVoteEvent(VoteEvent voteEvent) {
		this.voteEvent = voteEvent;
	}

	@Id @GeneratedValue protected Integer id;

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}
}
