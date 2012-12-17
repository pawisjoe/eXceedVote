package com.github.ant2.exceedvote.controller.admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ListModel;

import com.github.ant2.exceedvote.model.domain.Criterion;
import com.github.ant2.exceedvote.model.domain.Project;
import com.github.ant2.exceedvote.model.domain.VoteEvent;
import com.github.ant2.exceedvote.model.process.ChangeObserver;
import com.github.ant2.exceedvote.model.process.EditCriterionProcess;
import com.github.ant2.exceedvote.view.admin.EditCriteriaWindow;
import com.github.ant2.exceedvote.view.admin.ManageEventWindow;
import com.github.exceedvote.process.admin.EventManagerProcess;

/**
 * 
 *
 * @author Thiwat Rongsirigul (Leo Aiolia)
 */
public class EventManagerController implements ChangeObserver {
	private EventManagerProcess process;
	private ManageEventWindow view;
	private List<Project> projects;
	private List<Criterion> criteria;
	private VoteEvent event;

	public EventManagerController(EventManagerProcess process,
			ManageEventWindow view) {
		this.process = process;
		this.view = view;
		process.addObserver(this);
		event = process.getEvent();
		addListener();
		reload();
	}
	
	@Override
	public void changed() {
		reload();
	}
	
	private void reload() {
		projects = process.getAllProject();
		criteria = process.getAllCriterion();
		setListModel();
	}

	private void setListModel() {
		view.getProjectList().setModel(new ProjectListModel());
		view.getCriteriaList().setModel(new CriterionListModel());
	}

	private void addListener() {
		view.getAddCriteriaButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				editCriterion(event.createCriterion(""));
			}
		});
		view.getEditCriteriaButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				editCriterion(criteria.get(view.getCriteriaList().getSelectedIndex()));
			}
		});
	}
	
	private void editCriterion(Criterion criterion) {
		EditCriterionProcess subprocess = process.editCriterion(criterion);
		EditCriteriaWindow window = new EditCriteriaWindow();
		EditCriterionController controller = new EditCriterionController(subprocess, window);
		controller.run();
	}

	public void run() {
		view.setVisible(true);
		view.pack();
	}

	private class ProjectListModel extends AbstractListModel implements
			ListModel {
		/** */
		private static final long serialVersionUID = 1L;

		@Override
		public String getElementAt(int index) {
			return projects.get(index).toString();
		}

		@Override
		public int getSize() {
			return projects.size();
		}

	}

	private class CriterionListModel extends AbstractListModel
			implements ListModel {
		/** */
		private static final long serialVersionUID = 1L;

		@Override
		public String getElementAt(int index) {
			return criteria.get(index).toString();
		}

		@Override
		public int getSize() {
			return criteria.size();
		}

	}

}
