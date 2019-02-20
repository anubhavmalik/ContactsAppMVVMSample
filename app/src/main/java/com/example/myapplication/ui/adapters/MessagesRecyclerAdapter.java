package com.example.myapplication.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.myapplication.base.BaseViewHolder;
import com.example.myapplication.data.models.Message;
import com.example.myapplication.databinding.ItemEmptyViewBinding;
import com.example.myapplication.databinding.ItemMessagesViewBinding;
import com.example.myapplication.ui.tabcontainer.messages.MessagesItemViewModel;

import java.util.List;

public class MessagesRecyclerAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int VIEW_TYPE_EMPTY = 0;

    public static final int VIEW_TYPE_NORMAL = 1;

    private List<Message> mContactsList;

    private TabsListRecyclerAdapter.BlogAdapterListener mListener;

    private boolean isLoading = true;

    public MessagesRecyclerAdapter(List<Message> mContactsList) {
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
                ItemMessagesViewBinding blogViewBinding = ItemMessagesViewBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent, false);
                return new MessagesRecyclerAdapter.MessageViewHolder(blogViewBinding);

            case VIEW_TYPE_EMPTY:

            default:
                ItemEmptyViewBinding emptyBinding = ItemEmptyViewBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent, false);
                return new MessagesRecyclerAdapter.EmptyViewHolder(emptyBinding);
        }
    }

    public void addItems(List<Message> mContactsList) {
        this.isLoading = mContactsList != null && mContactsList.size() > 0;
        this.mContactsList.addAll(mContactsList);
        Log.d("KKKK", mContactsList.size() + " is list size");
        notifyDataSetChanged();
    }

    public void clearItems() {
        mContactsList.clear();
    }

    public class MessageViewHolder extends BaseViewHolder {

        private ItemMessagesViewBinding mBinding;

        private MessagesItemViewModel mContactsItemViewModel;

        public MessageViewHolder(ItemMessagesViewBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            final Message result = mContactsList.get(position);
            mContactsItemViewModel = new MessagesItemViewModel(result);
            Log.d("PPPP", "bind hue");
            mBinding.setViewModel(mContactsItemViewModel);

            // Immediate Binding
            // When a variable or observable changes, the binding will be scheduled to change before
            // the next frame. There are times, however, when binding must be executed immediately.
            // To force execution, use the executePendingBindings() method.
            mBinding.executePendingBindings();
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
