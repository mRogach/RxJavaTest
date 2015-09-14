package com.example.mrogach.rxjavatest.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mrogach.rxjavatest.R;
import com.example.mrogach.rxjavatest.model.OrganizationModel;

import java.util.List;

/**
 * Created by
 * mRogach on 11.09.2015.
 */
public class OrganizationsAdapter extends RecyclerView.Adapter<OrganizationsAdapter.ViewHolder>{
    private List<OrganizationModel> mOrganizationModels;

    public void setData(List<OrganizationModel> _organizationModels) {
        mOrganizationModels = _organizationModels;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvTitle.setText(mOrganizationModels.get(position).title);
        holder.tvPhone.setText(mOrganizationModels.get(position).phone);
    }

    @Override
    public int getItemCount() {
        return mOrganizationModels.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle, tvPhone;
        private CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cvItemOrganization_IL);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle_IL);
            tvPhone = (TextView) itemView.findViewById(R.id.tvPhone_IL);
        }
    }
}
