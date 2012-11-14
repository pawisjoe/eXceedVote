package com.github.ant2.exceedvote.dao.memory;

import java.util.ArrayList;
import java.util.List;

import com.github.ant2.exceedvote.dao.BallotDao;
import com.github.ant2.exceedvote.dao.CriterionDao;
import com.github.ant2.exceedvote.dao.DaoFactory;
import com.github.ant2.exceedvote.dao.EventDao;
import com.github.ant2.exceedvote.dao.ProjectDao;
import com.github.ant2.exceedvote.dao.VoterDao;
import com.github.ant2.exceedvote.model.domain.Ballot;
import com.github.ant2.exceedvote.model.domain.Criterion;
import com.github.ant2.exceedvote.model.domain.Project;
import com.github.ant2.exceedvote.model.domain.VoteEvent;
import com.github.ant2.exceedvote.model.domain.Voter;

public class MemoryDaoFactory implements DaoFactory {


	private class MemoryDao<T> {
		
		protected List<T> list = new ArrayList<T>();
		
		public void save(T t) {
			list.add(t);
		}
		
		public List<T> findAll() {
			return new ArrayList<T>(list);
		}
		
	}
	
	private class VoteEventPartDao<T extends VoteEvent.Part> extends MemoryDao<T> {
		public List<T> findAllByEvent(VoteEvent event) {
			List<T> list = new ArrayList<T>();
			for (T t : findAll()) {
				if (t.getVoteEvent().equals(event)) {
					list.add(t);
				}
			}
			return list;
		}
	}
	
	private class MemoryEventDao extends MemoryDao<VoteEvent> implements EventDao {
	}

	private class MemoryVoterDao extends VoteEventPartDao<Voter> implements VoterDao {
	}
	
	private class MemoryCriterionDao extends VoteEventPartDao<Criterion> implements CriterionDao {
	}
	
	private class MemoryProjectDao extends VoteEventPartDao<Project> implements ProjectDao {
	}
	
	private class MemoryBallotDao extends MemoryDao<Ballot> implements BallotDao {

		@Override
		public List<Ballot> findAllByVoterAndCriterion(Voter voter, Criterion criterion) {
			List<Ballot> list = new ArrayList<Ballot>();
			for (Ballot ballot : findAll()) {
				if (ballot.getVoter().equals(voter) && ballot.getCriterion().equals(criterion)) {
					list.add(ballot);
				}
			}
			return list;
		}
		
	}

	
	@Override
	public EventDao getEventDao() {
		return new MemoryEventDao();
	}

	@Override
	public VoterDao getVoterDao() {
		return new MemoryVoterDao();
	}

	@Override
	public CriterionDao createCriterionDao() {
		return new MemoryCriterionDao();
	}

	@Override
	public ProjectDao createProjectDao() {
		return new MemoryProjectDao();
	}

	@Override
	public BallotDao createBallotDao() {
		return new MemoryBallotDao();
	}

}
