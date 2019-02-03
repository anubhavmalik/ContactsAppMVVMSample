package com.example.myapplication.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.myapplication.base.BaseViewHolder;
import com.example.myapplication.data.models.Contact;
import com.example.myapplication.databinding.ItemContactsViewBinding;
import com.example.myapplication.databinding.ItemEmptyViewBinding;
import com.example.myapplication.ui.tabcontainer.contacts.ContactsItemViewModel;

import java.util.List;

public class TabsListRecyclerAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int VIEW_TYPE_EMPTY = 0;

    public static final int VIEW_TYPE_NORMAL = 1;

    public static final int VIEW_TYPE_JOB = 3;

    private List<Contact> mContactsList;

    private BlogAdapterListener mListener;

    private boolean isLoading = true;

    public TabsListRecyclerAdapter(List<Contact> mContactsList) {
        this.mContactsList = mContactsList;
    }

    @Override
    public int getItemCount() {
        if (mContactsList != null && mContactsList.size() > 0) {
            Log.d("CONTACTSLISTADAPTER", mContactsList.size() + " size here");
            return mContactsList.size();
        } else {
            return 1;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mContactsList != null && !mContactsList.isEmpty()) {
            return VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_EMPTY;
        }
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                ItemContactsViewBinding blogViewBinding = ItemContactsViewBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent, false);
                return new ContactsViewHolder(blogViewBinding);

            case VIEW_TYPE_EMPTY:

            default:
                ItemEmptyViewBinding emptyBinding = ItemEmptyViewBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent, false);
                return new EmptyViewHolder(emptyBinding);
        }
    }

    public void addItems(List<Contact> mContactsList) {
        this.isLoading = mContactsList != null && mContactsList.size() > 0;
        this.mContactsList.addAll(mContactsList);
        Log.d("KKKK", mContactsList.size() + " is list size");
        notifyDataSetChanged();
    }

    public void clearItems() {
        mContactsList.clear();
    }

    public void setListener(BlogAdapterListener listener) {
        this.mListener = listener;
    }

    public interface BlogAdapterListener {
        void onItemClick(String id);
    }


    public class ContactsViewHolder extends BaseViewHolder implements ContactsItemViewModel.ContactsItemClickListener {

        private ItemContactsViewBinding mBinding;

        private ContactsItemViewModel mContactsItemViewModel;

        public ContactsViewHolder(ItemContactsViewBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            final Contact result = mContactsList.get(position);
            mContactsItemViewModel = new ContactsItemViewModel(result, this);

            mBinding.setViewModel(mContactsItemViewModel);

            // Immediate Binding
            // When a variable or observable changes, the binding will be scheduled to change before
            // the next frame. There are times, however, when binding must be executed immediately.
            // To force execution, use the executePendingBindings() method.
            mBinding.executePendingBindings();
        }

        @Override
        public void onItemClick(int id) {
            //do something
        }
    }

    public class EmptyViewHolder extends BaseViewHolder {
        private ItemEmptyViewBinding mBinding;

        public EmptyViewHolder(ItemEmptyViewBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
           //do something ...
        }
    }
}
