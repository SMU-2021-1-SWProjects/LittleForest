package com.example.littleforest.InputPage;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.littleforest.R;

import java.util.ArrayList;

//---------- 식단 어댑터
class DietAdapter extends RecyclerView.Adapter<DietAdapter.CustomViewHolder> {

    private ArrayList<Diet> data_diet;
    private Context context;

    public DietAdapter(ArrayList<Diet> data_diet, Context context){
        this.data_diet = data_diet;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_diet, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.diet_time.setText(String.valueOf(data_diet.get(position).getTime()));
        holder.diet_item.setText(String.valueOf(data_diet.get(position).toString()));
    }

    @Override
    public int getItemCount() {
        return (data_diet != null ? data_diet.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView diet_time;
        TextView diet_item;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            this.diet_time = itemView.findViewById(R.id.diet_time);
            this.diet_item = itemView.findViewById(R.id.diet_item);
        }
    }
}

//---------- 메뉴 어댑터
class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.CustomViewHolder> {

    private ArrayList<String> data_menu;
    private Context context;

    public MenuAdapter(ArrayList<String> data_menu, Context context){
        this.data_menu = data_menu;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_menu, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.menu_item.setText(String.valueOf(data_menu.get(position)));

        holder.itemView.setTag(position);

        // 리사이클러뷰 내 아이템 short 클릭 시 -> 다이어그램으로 수정 가능
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modify(holder.getAdapterPosition());
            }
        });

        // 리사이클러뷰 내 아이템 long 클릭 시 -> 다이어그램으로 삭제 가능
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                remove(holder.getAdapterPosition());
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return (data_menu != null ? data_menu.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView menu_item;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.menu_item = itemView.findViewById(R.id.menu_item);
        }
    }

    /**
     * 리사이클러뷰 아이템을 수정하는 함수
     * @param position
     */
    public void modify(int position) {
        try{
            // 다이어로그 불러오기
            EditText editText = new EditText(((AddMenuActivity)AddMenuActivity.addMenuContext));
            String beforeMenu = data_menu.get(position);

            AlertDialog.Builder builder = new AlertDialog.Builder(((AddMenuActivity)AddMenuActivity.addMenuContext));
            builder.setMessage("이 메뉴를 수정하시겠습니까?");
            builder.setView(editText);
            editText.setText(beforeMenu);

            // Yes 버튼 : 메뉴 수정
            builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String afterMenu = editText.getText().toString();
                    data_menu.set(position, afterMenu);
                    notifyDataSetChanged();
                }
            });

            // No 버튼 : 메뉴 수정 취소
            builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            //다이어로그 실행
            builder.show();

        }catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }
    }

    /**
     * 리사이클러뷰 아이템을 삭제하는 함수
     * @param position
     */
    public void remove(int position) {
        try{
            // 다이어로그 불러오기
            AlertDialog.Builder builder = new AlertDialog.Builder(((AddMenuActivity)AddMenuActivity.addMenuContext));
            builder.setMessage("이 메뉴를 삭제하시겠습니까?");

            // Yes 버튼 : 메뉴 삭제
            builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    data_menu.remove(position);
                    notifyItemRemoved(position);
                }
            });

            // No 버튼 : 메뉴 삭제 취소
            builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            // 다이어로그 실행
            builder.show();

        }catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }
    }
}
