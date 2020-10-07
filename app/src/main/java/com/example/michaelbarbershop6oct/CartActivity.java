package com.example.michaelbarbershop6oct;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.michaelbarbershop6oct.Adapter.MyCartAdapter;
import com.example.michaelbarbershop6oct.Database.CartDatabase;
import com.example.michaelbarbershop6oct.Database.CartItem;
import com.example.michaelbarbershop6oct.Database.DatabaseUtils;
import com.example.michaelbarbershop6oct.Interface.ICartItemLoadLitener;
import com.example.michaelbarbershop6oct.Interface.ICartItemUpdateListener;
import com.example.michaelbarbershop6oct.Interface.ISumCartListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class CartActivity extends AppCompatActivity implements ICartItemLoadLitener, ICartItemUpdateListener, ISumCartListener {

    private static final String TAG = "CartActivity";

    @BindView(R.id.recycler_cart)
    RecyclerView recycler_cart;
    @BindView(R.id.txt_total_price)
    TextView txt_total_price;
    @BindView(R.id.btn_clear_cart)
    Button btn_clear_cart;

    @OnClick(R.id.btn_clear_cart)
    void clearCart() {
        DatabaseUtils.clearCart(mCartDatabase);

        // Update Adapter
        DatabaseUtils.getAllCart(mCartDatabase, this);
    }

    private CartDatabase mCartDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Log.d(TAG, "onCreate: started!!");

        ButterKnife.bind(CartActivity.this);

        mCartDatabase = CartDatabase.getInstance(this);

        DatabaseUtils.getAllCart(mCartDatabase, this);

        // View
        recycler_cart.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recycler_cart.setLayoutManager(linearLayoutManager);
        recycler_cart.addItemDecoration(new DividerItemDecoration(this, linearLayoutManager.getOrientation()));
    }

    @Override
    public void onGetAllItemFromCartSuccess(List<CartItem> cartItemList) {
        Log.d(TAG, "onGetAllItemFromCartSuccess: called!!");
        // Here, after we get all cart item from DB
        // We will display by Recycler View
        MyCartAdapter adapter = new MyCartAdapter(this, cartItemList, this);
        recycler_cart.setAdapter(adapter);
    }

    @Override
    public void onCartItemUpdateSuccess() {
        Log.d(TAG, "onCartItemUpdateSuccess: called!!");
        DatabaseUtils.sumCart(mCartDatabase, this);
    }

    @Override
    public void onSumCartSuccess(long value) {
        Log.d(TAG, "onSumCartSuccess: called!!");
        txt_total_price.setText(new StringBuilder("$").append(value));
    }
}
