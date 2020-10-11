package com.example.michaelbarbershop6oct;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.example.michaelbarbershop6oct.Adapter.MyHistoryAdapter;
import com.example.michaelbarbershop6oct.Common.Common;
import com.example.michaelbarbershop6oct.Model.BookingInformation;
import com.example.michaelbarbershop6oct.Model.EventBus.UserBookingLoadEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dmax.dialog.SpotsDialog;


public class HistoryActivity extends AppCompatActivity {

    @BindView(R.id.recycler_history)
    RecyclerView recycler_history;

    @BindView(R.id.txt_history)
    TextView txt_history;

    AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        ButterKnife.bind(this);

        init();
        initView();

        loadUserBookingInformation();
    }

    private void loadUserBookingInformation() {
        dialog.show();

        CollectionReference userBooking = FirebaseFirestore.getInstance()
                .collection("User")
                .document(Common.currentUser.getPhoneNumber())
                .collection("Booking");

        userBooking.whereEqualTo("done", false)
                .orderBy("timestamp", Query.Direction.DESCENDING)
                .get()
                .addOnFailureListener(e -> EventBus.getDefault().post(new UserBookingLoadEvent(false, e.getMessage()))).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                List<BookingInformation> bookingInformationList = new ArrayList<>();
                for (DocumentSnapshot userBookingSnapShot : task.getResult()) {
                    BookingInformation bookingInformation = userBookingSnapShot.toObject(BookingInformation.class);
                    bookingInformationList.add(bookingInformation);
                }

                //Use EventBus to Send
                EventBus.getDefault().post(new UserBookingLoadEvent(true, bookingInformationList));
            }
        });
    }

    private void initView() {
        recycler_history.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recycler_history.setLayoutManager(layoutManager);
        recycler_history.addItemDecoration(new DividerItemDecoration(this, layoutManager.getOrientation()));
    }

    private void init() {
        dialog = new SpotsDialog.Builder().setContext(this).setCancelable(false).build();
    }

    //Here , we will implement EventBus
    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void displayData(UserBookingLoadEvent event) {
        if (event.isSuccess()) {
            MyHistoryAdapter adapter = new MyHistoryAdapter(this, event.getBookingInformationList());
            recycler_history.setAdapter(adapter);

            txt_history.setText(new StringBuilder("HISTORY (")
                    .append(event.getBookingInformationList().size())
                    .append(")"));
        } else {
            Toast.makeText(this, "" + event.getMessage(), Toast.LENGTH_SHORT).show();
        }

        dialog.dismiss();
    }
}
