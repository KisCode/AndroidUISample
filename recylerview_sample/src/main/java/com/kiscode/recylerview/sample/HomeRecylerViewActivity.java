package com.kiscode.recylerview.sample;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class HomeRecylerViewActivity extends AppCompatActivity {

    private String[] menuOptions = {"One", "Two"};

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_recyler_view);
        initView();
    }

    private void initView() {
        recyclerView = findViewById(R.id.recylerView_home);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        MyAdapter adapter = new MyAdapter(this, Arrays.asList(menuOptions));
        recyclerView.setAdapter(adapter);

        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHomeViewHolder> {
        private Context context;
        private List<String> mDatas;

        public MyAdapter(Context context, List<String> mDatas) {
            this.context = context;
            this.mDatas = mDatas;
        }

        @NonNull
        @Override
        public MyHomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            MyHomeViewHolder homeViewHolder = new MyHomeViewHolder(LayoutInflater.from(context).inflate(R.layout.item_text_layout, parent, false));
            return homeViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyHomeViewHolder holder, int position) {
            holder.tvText.setText(mDatas.get(position));
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        private class MyHomeViewHolder extends RecyclerView.ViewHolder {
            TextView tvText;

            public MyHomeViewHolder(@NonNull View itemView) {
                super(itemView);
                tvText = itemView.findViewById(R.id.tv_text_item);
            }
        }
    }

}
