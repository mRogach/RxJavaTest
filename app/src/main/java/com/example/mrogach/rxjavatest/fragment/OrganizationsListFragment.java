package com.example.mrogach.rxjavatest.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mrogach.rxjavatest.R;
import com.example.mrogach.rxjavatest.activity.MainActivity;
import com.example.mrogach.rxjavatest.adapter.OrganizationsAdapter;
import com.example.mrogach.rxjavatest.api.RestClient;
import com.example.mrogach.rxjavatest.model.OrganizationModel;
import com.example.mrogach.rxjavatest.model.ResponseModel;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by
 * mRogach on 11.09.2015.
 */
public class OrganizationsListFragment extends Fragment {

    private RecyclerView recyclerView;
    private OrganizationsAdapter mAdapter;
    private LinearLayoutManager linearLayoutManager;
    private MainActivity mActivity;
    private List<OrganizationModel> organizationModels;

    public static OrganizationsListFragment getInstance() {
        return new OrganizationsListFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity = (MainActivity) getActivity();
        getOrganizations();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mInflatedView = inflater.inflate(R.layout.organizations_list_fragment, container, false);
        recyclerView = (RecyclerView) mInflatedView.findViewById(R.id.rvOrganizations_OLF);

        init();

        return mInflatedView;
    }

    private void init() {
        organizationModels = new ArrayList<>();
        mAdapter = new OrganizationsAdapter();
        linearLayoutManager = new LinearLayoutManager(mActivity);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private void getOrganizations() {
        mActivity.getLoadingDialogController().register(mActivity);
        mActivity.getLoadingDialogController().showLoadingDialog("load");
        RestClient.getApiService().getInfo()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mActivity.getLoadingDialogController().hideLoadingDialog("load");
                        if (e != null) {
                            Toast.makeText(mActivity, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onNext(ResponseModel responseModel) {
                        mActivity.getLoadingDialogController().hideLoadingDialog("load");
                        if (responseModel != null) {
                            organizationModels = responseModel.organizations;
                            setData(organizationModels);
                        }
                    }
                });
    }

//    Callback<ResponseModel> callback = new Callback<ResponseModel>() {
//        @Override
//        public void success(ResponseModel responseModel, Response response) {
//            mActivity.getLoadingDialogController().hideLoadingDialog("load");
//            if (response != null) {
//                organizationModels = responseModel.organizations;
//                setData(organizationModels);
//            }
//        }
//
//        @Override
//        public void failure(RetrofitError error) {
//            mActivity.getLoadingDialogController().hideLoadingDialog("load");
//            if (error != null) {
//                Toast.makeText(mActivity, error.getMessage(), Toast.LENGTH_LONG).show();
//            }
//        }
//    };

    private void setData(List<OrganizationModel> _organizationModels) {
        mAdapter.setData(_organizationModels);
        mAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(mAdapter);
    }
}
