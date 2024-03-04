package com.example.fitnessapp_fyp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnessapp_fyp.ExerciseView;
import com.example.fitnessapp_fyp.Model.Exercise;
import com.example.fitnessapp_fyp.R;
import com.example.fitnessapp_fyp.interfaces.ItemClickListener;

import java.util.List;

class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public ImageView image;
    public TextView text;
    private ItemClickListener itemClickListener;

    public RecyclerViewHolder( View itemView) {
        super(itemView);
        image = (ImageView) itemView.findViewById(R.id.WorkoutImage);
        text = (TextView) itemView.findViewById(R.id.drillName);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view, getAbsoluteAdapterPosition());


    }
}


public class RecyclerViewAdapter  extends RecyclerView.Adapter<RecyclerViewHolder> {

    private List<Exercise> exerciseList;
    private Context context;


    public RecyclerViewAdapter(List<Exercise> exerciseList, Context context) {
        this.exerciseList = exerciseList;
        this.context = context;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.row_item, parent, false);

        return new RecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder( RecyclerViewHolder holder, int position) {

        holder.image.setImageResource(exerciseList.get(position).getImage_id());
        holder.text.setText(exerciseList.get(position).getName());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(context, ExerciseView.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("image_id",exerciseList.get(position).getImage_id() );
                intent.putExtra("name", exerciseList.get(position).getName());
                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return exerciseList.size();
    }
}
