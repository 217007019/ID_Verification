package com.example.id_verification;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder>
{
    private final List<User> user;
    ItemClicked activity;

    public UserAdapter (Context context, List<User> list)
    {
        user = list;
        activity = (ItemClicked) context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView person;
        TextView rank, name, persal_Number;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);

            person = itemView.findViewById(R.id.person);
            rank = itemView.findViewById(R.id.rank);
            name = itemView.findViewById(R.id.name);
            persal_Number = itemView.findViewById(R.id.persal_Number);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.onItemClicked(user.indexOf(v.getTag()));
                }
            });
        }
    }

    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_layout,
                viewGroup, false);

        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder viewHolder, int i)
    {
        viewHolder.itemView.setTag(user.get(i));

        viewHolder.person.setImageDrawable(Drawable.createFromPath(user.get(i).getPicture()));
        viewHolder.rank.setText(user.get(i).getRank());
        viewHolder.name.setText(user.get(i).getSurname() + " " + user.get(i).getName());
        viewHolder.persal_Number.setText(user.get(i).getPersalNumber());

    }

    @Override
    public int getItemCount()
    {
        return user.size();
    }


}
