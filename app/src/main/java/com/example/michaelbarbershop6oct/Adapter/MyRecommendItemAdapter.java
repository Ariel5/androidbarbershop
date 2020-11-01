package com.example.michaelbarbershop6oct.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.michaelbarbershop6oct.Common.Common;
import com.example.michaelbarbershop6oct.Database.CartDatabase;
import com.example.michaelbarbershop6oct.Database.CartItem;
import com.example.michaelbarbershop6oct.Database.DatabaseUtils;
import com.example.michaelbarbershop6oct.Interface.IRecyclerItemSelectedListener;
import com.example.michaelbarbershop6oct.Model.ShoppingItem;
import com.example.michaelbarbershop6oct.R;
import com.squareup.picasso.Picasso;

import java.util.List;



public class MyRecommendItemAdapter extends RecyclerView.Adapter<MyRecommendItemAdapter.MyViewHolder> {

    private static final String TAG = MyRecommendItemAdapter.class.getSimpleName();

    private Context mContext;
    private List<ShoppingItem> mShoppingItemList;

    private CartDatabase mCartDatabase;

    public MyRecommendItemAdapter(Context context, List<ShoppingItem> shoppingItemList) {
        mContext = context;
        mShoppingItemList = shoppingItemList;
        mCartDatabase = CartDatabase.getInstance(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.layout_recommend_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Picasso.get().load(mShoppingItemList.get(position).getImage()).into(holder.img_recommend_item);

        holder.txt_name_recommend_item.setText(
                Common.formatShoppingItemName(mShoppingItemList.get(position).getName()));
//        holder.txt_rating_number_recommend_item.setText((int) mShoppingItemList.get(position).getRating());
    }

    @Override
    public int getItemCount() {
        return mShoppingItemList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView txt_name_recommend_item;
//        private TextView txt_shopping_item_price;
        private TextView txt_rating_number_recommend_item;
        private ImageView img_recommend_item;

        private IRecyclerItemSelectedListener mIRecyclerItemSelectedListener;

        public void setIRecyclerItemSelectedListener(IRecyclerItemSelectedListener IRecyclerItemSelectedListener) {
            mIRecyclerItemSelectedListener = IRecyclerItemSelectedListener;
        }

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            img_recommend_item = itemView.findViewById(R.id.img_recommend_item);
            txt_name_recommend_item = itemView.findViewById(R.id.txt_name_recommend_item);
//            txt_shopping_item_price = itemView.findViewById(R.id.txt_price_shopping_item);
            txt_rating_number_recommend_item = itemView.findViewById(R.id.txt_rating_number_recommend_item);

//            txt_add_to_cart.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mIRecyclerItemSelectedListener.onItemSelected(v, getAdapterPosition());
        }
    }
}
