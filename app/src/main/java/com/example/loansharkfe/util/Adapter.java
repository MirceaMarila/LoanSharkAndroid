package com.example.loansharkfe.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loansharkfe.R;
import com.example.loansharkfe.model.FriendCard;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<FriendCard> friendsList;

    public Adapter(List<FriendCard>friendsList) {
        this.friendsList=friendsList;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.friend_card_design,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {

        String resource = friendsList.get(position).getImage();
        String username=friendsList.get(position).getUsername();
        String firstName=friendsList.get(position).getFirstName();
        String lastName=friendsList.get(position).getLastName();

        holder.setData(resource,username,firstName,lastName);
    }

    @Override
    public int getItemCount() {
        return friendsList.size();
    }

    //view holder class



    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView username;
        private TextView firstName;
        private TextView lastName;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image=itemView.findViewById(R.id.friendImageDesign);
            username=itemView.findViewById(R.id.usernameDesign);
            firstName=itemView.findViewById(R.id.firstNameDesign);
            lastName=itemView.findViewById(R.id.lastNameDesign);
        }

        public void setData(String imageString, String userName, String firstname, String lastname) {
            if (imageString != null){
            byte[] decodedString = Base64.decode(imageString, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            image.setImageBitmap(decodedByte);}

            username.setText(userName);
            firstName.setText(firstname);
            lastName.setText(lastname);

        }
    }
}
