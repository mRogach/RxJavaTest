package com.example.mrogach.rxjavatest.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mrogach.rxjavatest.R;
import com.example.mrogach.rxjavatest.fragment.OrganizationsListFragment;
import com.example.mrogach.rxjavatest.util.LoadingDialogController;

public class MainActivity extends AppCompatActivity {

    private LoadingDialogController loadingDialogController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadingDialogController = new LoadingDialogController();
        getSupportFragmentManager().beginTransaction().add(R.id.flContainer, OrganizationsListFragment.getInstance()).commit();

    }

    public LoadingDialogController getLoadingDialogController() {
        return loadingDialogController;
    }

}
