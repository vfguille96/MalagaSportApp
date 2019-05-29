package com.bilalmoreno.malagasport.ui.workout;

import com.bilalmoreno.malagasport.data.db.model.Workout;

import java.util.ArrayList;

public class WorkoutListPresenter implements WorkoutListContract.Presenter, WorkoutLisInteractor.OnLoadFinishedListener {

    private WorkoutListContract.View view;
    private WorkoutLisInteractor interactor;

    public WorkoutListPresenter(WorkoutListContract.View view) {
        this.view = view;
        interactor = new WorkoutLisInteractor(this);
    }

    @Override
    public void load() {
        view.showProgress();
        interactor.load();
    }

    @Override
    public void onSuccess(ArrayList<Workout> workouts) {
        view.hideProgress();
        view.showWorkouts(workouts);
    }

    @Override
    public void onError(int resourceErrorMessage) {
        view.hideProgress();
        view.showError(resourceErrorMessage);
    }
}
