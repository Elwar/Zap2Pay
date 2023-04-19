package com.zap2p.zap2pay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import com.zap2p.zap2pay.TransactionHistoryAdapter;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class ConfigurationActivity extends AppCompatActivity {

    // Add the gesture detector as a member variable
    private GestureDetectorCompat gestureDetector;
    private RecyclerView transactionHistoryRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_configuration);

        transactionHistoryRecyclerView = findViewById(R.id.transaction_history_recycler_view);

        // Initialize the gesture detector
        gestureDetector = new GestureDetectorCompat(this, new SwipeGestureListener());
        // Sample transaction data
        ArrayList<HashMap<String, String>> transactionData = new ArrayList<HashMap<String, String>>() {{
            add(new HashMap<String, String>() {{
                put("date", "2023-01-01");
                put("time", "12:00:00");
                put("amount", "0.001");
                put("company", "McDonalds");
            }});
            add(new HashMap<String, String>() {{
                put("date", "2023-01-02");
                put("time", "15:30:00");
                put("amount", "0.002");
                put("company", "Walmart");
            }});
        }};


        // Pass the sample data to the adapter
        TransactionHistoryAdapter transactionHistoryAdapter = new TransactionHistoryAdapter(this, transactionData);
        transactionHistoryRecyclerView.setAdapter(transactionHistoryAdapter);
        transactionHistoryRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    // Add the onTouchEvent method
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    // Add the SwipeGestureListener class
    private class SwipeGestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float diffX = e2.getX() - e1.getX();
            float diffY = e2.getY() - e1.getY();

            if (Math.abs(diffX) > Math.abs(diffY)) {
                if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffX > 0) {
                        onSwipeRight();
                    }
                }
            }
            return false;
        }
    }

    // Add the onSwipeLeft method
    private void onSwipeRight() {
        finish();
    }
}
