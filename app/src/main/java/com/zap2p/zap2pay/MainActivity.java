package com.zap2p.zap2pay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ImageButton sendButton;
    private TextView balanceTextView;
    private TextView swipeMessage;
    private TextView purchasePriceLabel;
    private EditText purchasePriceInput;
    private GestureDetectorCompat gestureDetector;

    private int balance = 1; // Example balance value

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        sendButton = findViewById(R.id.zap2pay_send_button);
        balanceTextView = findViewById(R.id.balance);
        swipeMessage = findViewById(R.id.swipe_message);
        purchasePriceLabel = findViewById(R.id.purchase_price_label);
        purchasePriceInput = findViewById(R.id.purchase_price_input);

        balanceTextView.setText(getString(R.string.your_balance, Integer.toString(balance)));

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Send button clicked");
                // Your existing send button click handling code
            }
        });

        updateUI();

        gestureDetector = new GestureDetectorCompat(this, new SwipeGestureListener());
    }

    private void updateUI() {
        if (balance > 0) {
            balanceTextView.setVisibility(View.GONE);
            swipeMessage.setVisibility(View.GONE);
            purchasePriceLabel.setVisibility(View.VISIBLE);
            purchasePriceInput.setVisibility(View.VISIBLE);
        } else {
            balanceTextView.setVisibility(View.VISIBLE);
            swipeMessage.setVisibility(View.VISIBLE);
            purchasePriceLabel.setVisibility(View.GONE);
            purchasePriceInput.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "Touch event: " + event);
        gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    private class SwipeGestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float diffX = e2.getX() - e1.getX();
            float diffY = e2.getY() - e1.getY();

            if (Math.abs(diffX) > Math.abs(diffY)) {
                if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffX < 0) {
                        onSwipeLeft();
                    }
                }
            }
            return false;
        }
    }

    private void onSwipeLeft() {
        Log.d(TAG, "Swipe left action");
        Intent intent = new Intent(this, ConfigurationActivity.class);
        startActivity(intent);
    }
}
