package com.example.firebase

import android.view.View
import android.view.ViewGroup

class PlatAdapter {
    FirestoreRecyclerAdapter adapter = new FirestoreRecyclerAdapter<Chat, ChatHolder>(options) {
        @Override
        public void onBindViewHolder(ChatHolder holder, int position, Chat model) {

        }

        @Override
        public ChatHolder onCreateViewHolder(ViewGroup group, int i) {
            // Using a custom layout called R.layout.message for each item, we create a new instance of the viewholder
            View view = LayoutInflater.from(group.getContext())
                .inflate(R.layout.message, group, false);

            return new ChatHolder(view);
        }
    };
//Final step, where "mRecyclerView" is defined in your xml layout as
//the recyclerview
    mRecyclerView.setAdapter(adapter);
}