package com.example.tasktracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;



public class myTaskList extends RecyclerView.Adapter<myTaskList.TaskViewHolder> {
    private List<TaskList> taskList;
    private OnTaskDoneClickListener listener;

    public myTaskList(List<TaskList> taskList, OnTaskDoneClickListener listener) {
        this.taskList = taskList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the item layout and create ViewHolder
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_task_list, parent, false);
        return new TaskViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        // Bind data to ViewHolder
        TaskList task = taskList.get(position);
        holder.bind(task);
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    // ViewHolder class
    public class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView taskTitleTextView;
        Button markAsDoneButton;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            taskTitleTextView = itemView.findViewById(R.id.taskDescription);
            markAsDoneButton = itemView.findViewById(R.id.markAsDoneButton);

            markAsDoneButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && listener != null) {
                        // Call the listener's method when the button is clicked
                        listener.onTaskDoneClick(position);
                    }
                }
            });
        }

        public void bind(TaskList task) {
            // Bind data to views
            taskTitleTextView.setText(task.getTitle());
            // You can bind other data as needed
        }
    }

    // Interface for click listener
    public interface OnTaskDoneClickListener {
        void onTaskDoneClick(int position);
    }
}

