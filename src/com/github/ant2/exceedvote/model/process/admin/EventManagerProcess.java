package com.github.ant2.exceedvote.model.process.admin;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.ant2.exceedvote.dao.CriterionDao;
import com.github.ant2.exceedvote.dao.DaoFactory;
import com.github.ant2.exceedvote.dao.ProjectDao;
import com.github.ant2.exceedvote.model.domain.Criterion;
import com.github.ant2.exceedvote.model.domain.Project;
import com.github.ant2.exceedvote.model.domain.VoteEvent;
import com.github.ant2.exceedvote.util.ChangeObservable;
import com.github.ant2.exceedvote.util.ChangeObserver;

/**
 * 
 * 
 * @author Thiwat Rongsirigul (Leo Aiolia)
 */
public class EventManagerProcess extends ChangeObservable implements
		ChangeObserver {
	private static Logger logger = LogManager.getLogger(EventManagerProcess.class);
	
	private DaoFactory df;
	private ProjectDao projectDao;
	private CriterionDao criterionDao;
	private List<Project> projects;
	private List<Criterion> criteria;
	private VoteEvent event;

	public EventManagerProcess(DaoFactory df, VoteEvent event) {
		this.df = df;
		this.event = event;
		projectDao = df.getProjectDao();
		criterionDao = df.getCriterionDao();
		logger.debug("Manageing Event: " + event.toString());
	}

	public List<Project> getAllProjects() {
		if (projects == null) {
			projects = projectDao.findAllByEvent(event);
		}
		return projects;
	}

	public List<Criterion> getAllCriteria() {
		if (criteria == null) {
			criteria = criterionDao.findAllByEvent(event);
		}
		return criteria;
	}

	public VoteEvent getEvent() {
		return event;
	}

	public EditCriterionProcess editCriterion(Criterion criterion) {
		EditCriterionProcess process = new EditCriterionProcess(df, criterion);
		process.addObserver(this);
		return process;
	}

	public EditProjectProcess editProject(Project project) {
		EditProjectProcess process = new EditProjectProcess(df, project);
		process.addObserver(this);
		return process;
	}

	@Override
	public void changed() {
		// clear cached data
		projects = null;
		criteria = null;

		// notify others
		notifyObservers();
	}

	public ViewResultProcess viewResult() {
		return new ViewResultProcess(df, event);
	}

}
