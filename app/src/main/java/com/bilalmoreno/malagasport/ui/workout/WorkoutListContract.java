package com.bilalmoreno.malagasport.ui.workout;

import com.bilalmoreno.malagasport.data.db.model.Installation;
import com.bilalmoreno.malagasport.ui.base.BaseView;

import java.util.ArrayList;

public interface WorkoutListContract {
    interface View extends BaseView {
        void showWorkouts(ArrayList<Installation> workouts);
    }

    interface Presenter {
        void load();
    }
}
