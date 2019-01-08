package com.bilalmoreno.malagasport.ui.installation;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.bilalmoreno.malagasport.R;
import com.bilalmoreno.malagasport.data.db.model.Installation;
import com.bilalmoreno.malagasport.data.db.model.Usuario;
import com.bilalmoreno.malagasport.ui.base.BaseActivity;

public class InstallationActivity extends BaseActivity implements InstallationListFragment.OnInstallationShow, InstallationFragment.OnValorationShow, ValoracionFragment.OnValorationSavedChanges {

    private InstallationListFragment installationListFragment;
    private InstallationFragment installationFragment;
    private ValoracionFragment valoracionFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        installationListFragment = (InstallationListFragment) getSupportFragmentManager().findFragmentByTag(InstallationListFragment.TAG);

        if (installationListFragment == null) {
            installationListFragment = InstallationListFragment.getInstance(null, this);

            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.content, installationListFragment, InstallationListFragment.TAG);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onInstallationShow(int installationId) {
        Bundle bundle = new Bundle();
        bundle.putInt(Installation.TAG, installationId);

        installationFragment = InstallationFragment.getInstance(bundle, this);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content, installationFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onValorationShow(int action, String userId, int installationId) {
        Bundle bundle = new Bundle();
        if (action == ValoracionFragment.VALORACION_EDIT) {
            bundle.putInt(ValoracionFragment.ACTION_TAG, ValoracionFragment.VALORACION_EDIT);
        } else {
            bundle.putInt(ValoracionFragment.ACTION_TAG, ValoracionFragment.VALORACION_ADD);
        }
        bundle.putInt(Installation.TAG, installationId);
        bundle.putString(Usuario.TAG, userId);

        valoracionFragment = ValoracionFragment.getInstance(bundle, this);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content, valoracionFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onValorationSavedChanges() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack();
        }
    }
}
