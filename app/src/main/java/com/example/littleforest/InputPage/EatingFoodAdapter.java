package com.example.littleforest.InputPage;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

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
        /*holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modify(holder.getAdapterPosition());
            }
        });*/

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
            ((AddMenuActivity)AddMenuActivity.addMenuContext).addMenu("modify");

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

            Dialog dialog_removeMenu = new Dialog((AddMenuActivity)AddMenuActivity.addMenuContext);dialog_removeMenu.setContentView(R.layout.eatingfood_dialog_remove);

            TextView tv_eatingfood_yes = dialog_removeMenu.findViewById(R.id.tv_eatingfood_yes);
            TextView tv_eatingfood_no = dialog_removeMenu.findViewById(R.id.tv_eatingfood_no);

            // 다이얼로그 불러오기
            dialog_removeMenu.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog_removeMenu.show();

            // Yes 버튼 -> 메뉴를 삭제하고 다이얼로그 종료
            tv_eatingfood_yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    data_menu.remove(position);
                    notifyItemRemoved(position);

                    dialog_removeMenu.cancel();
                }
            });

            // No 버튼 -> 다이얼로그 종료
            tv_eatingfood_no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog_removeMenu.cancel();
                }
            });

            /*// 다이어로그 불러오기
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
            builder.show();*/

        }catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }
    }
}

//---------- 다이얼그램 어댑터
class EatingFoodSearchAdapter extends BaseAdapter implements Filterable {

    private ArrayList<EatingFoodSearch> listViewItemList = new ArrayList<>();
    private ArrayList<EatingFoodSearch> filteredItemList = listViewItemList;

    Filter listFilter;

    public EatingFoodSearchAdapter(){

    }

    @Override
    public int getCount() {
        return filteredItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return filteredItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int position1 = position;
        final Context context = parent.getContext();

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.eatingfood_listview_item, parent, false);
        }

        TextView textView = (TextView) convertView.findViewById(R.id.eatingfood_search);

        EatingFoodSearch listView = filteredItemList.get(position);

        textView.setText(listView.getEatingfood_search());

        // 리스트뷰 내 아이템 short 클릭 시 -> 다이얼로그의 텍스트에디터에 값 전달
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String clicked_food_name = textView.getText().toString();
                ((AddMenuActivity)AddMenuActivity.addMenuContext).messageFoodName(clicked_food_name);
            }
        });


        return convertView;
    }

    public void addItem(String text){
        EatingFoodSearch item = new EatingFoodSearch();

        item.setEatingfood_search(text);

        listViewItemList.add(item);
    }

    @Override
    public Filter getFilter() {
        if(listFilter == null) {
            listFilter = new ListFilter();
        }

        return listFilter;
    }

    private class ListFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if(constraint == null || constraint.length() == 0){
                results.values = listViewItemList;
                results.count = listViewItemList.size();
            }else {
                ArrayList<EatingFoodSearch> itemList = new ArrayList<>();

                for (EatingFoodSearch item : listViewItemList) {
                    if (item.getEatingfood_search().toUpperCase().contains(constraint.toString().toUpperCase())) {
                        itemList.add(item);
                    }
                }

                results.values = itemList;
                results.count = itemList.size();
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredItemList = (ArrayList<EatingFoodSearch>) results.values;

            if (results.count > 0) {
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }
        }
    }
}

