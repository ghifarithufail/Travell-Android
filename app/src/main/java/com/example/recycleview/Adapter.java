package com.example.recycleview;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    static Context context;
    ArrayList<com.example.recycleview.Db> list;




    public Adapter(Context context, ArrayList<com.example.recycleview.Db> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Db database = list.get(position);
        holder.firstname.setText(database.getFirstName());
        holder.lastname.setText((database.getAddress()));
        holder.dateTravell.setText((database.getDateTravel()));

        holder.btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.firstname.getContext())
                        .setContentHolder(new ViewHolder(R.layout.update_popup))
                        .setExpanded(true,2000)
                        .create();

                //dialogPlus.show();

                View v = dialogPlus.getHolderView();

                EditText fn = v.findViewById(R.id.txtFN);
                EditText ln = v.findViewById(R.id.txtLN);
                EditText ad = v.findViewById(R.id.txtAddress);
                EditText dt = v.findViewById(R.id.txtDateT);
                EditText bi = v.findViewById(R.id.txtBirt);
                EditText st = v.findViewById(R.id.txtState);
                EditText ci = v.findViewById(R.id.txtCity);
                EditText em = v.findViewById(R.id.txtEmail);



                Button update = v.findViewById(R.id.btnUPDATE);

                fn.setText(database.getFirstName());
                ln.setText(database.getLastName());
                ad.setText(database.getAddress());
                em.setText(database.getEmail());
                dt.setText(database.getDateTravel());
                bi.setText(database.getDate());
                st.setText(database.getState());
                ci.setText(database.getCity());


                dialogPlus.show();

                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("firstName", fn.getText().toString());
                        map.put("lastName", ln.getText().toString());
                        map.put("address", ad.getText().toString());
                        map.put("city", ci.getText().toString());
                        map.put("date", bi.getText().toString());
                        map.put("dateTravel", dt.getText().toString());
                        map.put("email", em.getText().toString());
                        map.put("state", st.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("Customer")
                                .child(database.getFirstName()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.firstname.getContext(), "Data Update Successfully.", Toast.LENGTH_SHORT).show();
                                        context.startActivity(new Intent(holder.firstname.getContext(),Data.class));
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(holder.firstname.getContext(), "Error while Updating.", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                });
                    }
                });
            }
        });



    holder.btndelete.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            AlertDialog.Builder builder = new AlertDialog.Builder(holder.firstname.getContext());
            builder.setTitle("Are you sure");
            builder.setMessage("Delete data can't be Undo");

            builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    FirebaseDatabase.getInstance().getReference().child("Customer")
                            .child(database.getFirstName()).removeValue();
                    Intent intent = new Intent(holder.firstname.getContext(),Data.class);
                    context.startActivity(new Intent(holder.firstname.getContext(),Data.class));
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    Toast.makeText(holder.firstname.getContext(),"Deleted sukses",Toast.LENGTH_LONG).show();
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(holder.firstname.getContext(), "Cancelled", Toast.LENGTH_SHORT).show();
                }
            });
            builder.show();
        }
    });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView    firstname,lastname,dateTravell;
        Button MENU;
        private static final String TAG = "MyViewHolder";

        Button btnedit,btndelete;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            firstname = itemView.findViewById(R.id.Fname);
            lastname = itemView.findViewById(R.id.Lname);
            dateTravell = itemView.findViewById(R.id.DateT);


            btnedit   = (Button)itemView.findViewById(R.id.btnEdit);
            btndelete = (Button)itemView.findViewById(R.id.btnDelete);
        }





    }
}
