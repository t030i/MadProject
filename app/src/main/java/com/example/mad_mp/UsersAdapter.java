package com.example.mad_mp;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.MyViewHolder> {

    private Context context;
    private ArrayList fn_id, ln_id, dob_id, user_id, email_id, pass_id;


    public UsersAdapter(Context context, ArrayList fn_id, ArrayList ln_id, ArrayList dob_id,
                       ArrayList user_id, ArrayList email_id, ArrayList pass_id){
        this.context=context;
        this.fn_id=fn_id;
        this.ln_id=ln_id;
        this.dob_id=dob_id;
        this.user_id=user_id;
        this.email_id=email_id;
        this.pass_id=pass_id;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(context).inflate(R.layout.userentry,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.fn_id.setText(String.valueOf(fn_id.get(position)));
        holder.ln_id.setText(String.valueOf(ln_id.get(position)));
        holder.dob_id.setText(String.valueOf(dob_id.get(position)));
        holder.user_id.setText(String.valueOf(user_id.get(position)));
        holder.email_id.setText(String.valueOf(email_id.get(position)));
        holder.pass_id.setText(String.valueOf(pass_id.get(position)));
    }

    @Override
    public int getItemCount() {
        return fn_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView fn_id, ln_id, dob_id, user_id, email_id, pass_id;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            fn_id=itemView.findViewById(R.id.textfn);
            ln_id=itemView.findViewById(R.id.textln);
            dob_id=itemView.findViewById(R.id.textdob);
            user_id=itemView.findViewById(R.id.textusername);
            email_id=itemView.findViewById(R.id.textemail);
            pass_id=itemView.findViewById(R.id.textpassword);

        }
    }
}
