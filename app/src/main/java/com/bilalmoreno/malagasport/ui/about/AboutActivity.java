package com.bilalmoreno.malagasport.ui.about;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

import com.bilalmoreno.malagasport.R;
import com.vansuita.materialabout.builder.AboutBuilder;
import com.vansuita.materialabout.views.AboutView;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        AboutView view = AboutBuilder.with(this)
                .setPhoto(R.drawable.logo_malagasport)
                .setCover(R.drawable.malaga)
                .setName(getString(R.string.app_name))
                .setSubTitle(getString(R.string.desarrollado_por))
                .setBrief(getString(R.string.app_description))
                .setAppIcon(R.mipmap.ic_launcher)
                .setAppName(R.string.app_name)
                .addLinkedInLink(R.string.linkedin_username)
                .addGitHubLink(R.string.github_user)
                .addLink(R.drawable.ic_gitlab, "GitLab", "https://gitlab.com/BilalMoreno92")
                .setVersionNameAsAppSubTitle()
                .setLinksColumnsCount(3)
                .addFeedbackAction(R.string.email_desarrollador)
                .setWrapScrollView(true)
                .setLinksAnimated(true)
                .setShowAsCard(false)
                .setDividerColor(R.color.colorPrimaryDark)
                .setActionsColumnsCount(1)
                .build();

        addContentView(view, new AboutView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
