package com.github.ant2.ui.activity;

/**
 * A base, abstract implementation of Activity. An Activity is like a
 * "Controller" that mediates between the model and the ActivityView.
 * 
 * @param <View>
 *            type of ActivityView that this Activity controls.
 * @author dtinth
 */
public abstract class AbstractActivity<View extends ActivityView> implements
		Activity {

	/**
	 * The view that this Activity is controlling.
	 */
	protected View view;
	
	/**
	 * The ActivityDelegate.
	 */
	protected ActivityDelegate delegate;

	@Override
	public ActivityView getView() {
		return view;
	}

	/**
	 * Constructs an AbstractActivity with the specified activity view.
	 * 
	 * @param view
	 *            the view to associate with this activity
	 */
	public AbstractActivity(View view) {
		this.view = view;
	}

	@Override
	public void setDelegate(ActivityDelegate delegate) {
		this.delegate = delegate;
	}

	/**
	 * Transitions to another activity. This is a convenient method that
	 * subclasses can use to take user to another activity.
	 * 
	 * @param activity
	 *            the activity to transition to
	 */
	protected void runActivity(Activity activity) {
		delegate.runActivity(activity);
	}

	/**
	 * Transitions to another activity. This is a convenient method that
	 * subclasses can use to take user to another activity.
	 * 
	 * @param activity
	 *            the activity to transition to
	 * @param fx
	 *            the transition effect
	 */
	protected void runActivity(Activity activity, Fx fx) {
		delegate.runActivity(activity, fx);
	}

}
