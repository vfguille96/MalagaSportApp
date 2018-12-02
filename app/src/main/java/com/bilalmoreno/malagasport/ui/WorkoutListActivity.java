package com.bilalmoreno.malagasport.ui;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.bilalmoreno.malagasport.R;
import com.bilalmoreno.malagasport.adapter.WorkoutAdapter;
import com.bilalmoreno.malagasport.pojo.Instalacion;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WorkoutListActivity extends AppCompatActivity {

    @BindView(R.id.rvWorkout)
    RecyclerView rvWorkout;

    private WorkoutAdapter adapter;
    private WorkoutAdapter.WorkoutListListener listener;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_list);

        ButterKnife.bind(this);

        listener = new WorkoutAdapter.WorkoutListListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(WorkoutListActivity.this, MaquinaListActivity.class);
                intent.putExtra("instalacion", ((Instalacion) adapter.getItem(rvWorkout.getChildAdapterPosition(view))).getId());
                startActivity(intent);
            }
        };
        adapter = new WorkoutAdapter(getApplicationContext(), listener);
        rvWorkout.setAdapter(adapter);
        rvWorkout.setLayoutManager(new LinearLayoutManager(this));

        rvWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(WorkoutListActivity.this, "", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
