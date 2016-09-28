package com.asion.gank.activities;

import android.animation.Animator;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.asion.gank.R;

import static com.asion.gank.R.id.desc;
import static com.asion.gank.R.id.who;

public class SendActivity extends AppCompatActivity {

    private View content;
    private int mX;
    private int mY;
    private ImageButton close;
    private TextView mType;
    private EditText mUrl;
    private TextInputLayout mSendUrl;
    private EditText mDesc;
    private TextInputLayout mSendDesc;
    private EditText mWho;
    private TextInputLayout mSendWho;
    private Button mSubmitGank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);
        initView();
        startAnimotion();


    }

    private void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    private void startAnimotion() {
        content = findViewById(R.id.activity_send);
        close = ((ImageButton) findViewById(R.id.send_close));
        content.post(() -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                mX = getIntent().getIntExtra("x", 0);
                mY = getIntent().getIntExtra("y", 0);
                Animator animator = createRevealAnimator(false, mX, mY);
                animator.start();
            }
        });

        close.setOnClickListener(v -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Animator animator = createRevealAnimator(true, mX, mY);
                animator.start();
            } else {
                finish();
            }
        });
    }

    private Animator createRevealAnimator(boolean reversed, int x, int y) {
        float hypot = (float) Math.hypot(content.getHeight(), content.getWidth());
        float startRadius = reversed ? hypot : 0;
        float endRadius = reversed ? 0 : hypot;

        Animator animator = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            animator = ViewAnimationUtils.createCircularReveal(
                    content, x, y,
                    startRadius,
                    endRadius);
        }
        animator.setDuration(1000);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        if (reversed)
            animator.addListener(animatorListener);
        return animator;
    }

    private Animator.AnimatorListener animatorListener = new Animator.AnimatorListener() {
        @Override
        public void onAnimationStart(Animator animation) {
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            content.setVisibility(View.INVISIBLE);
            finish();
        }

        @Override
        public void onAnimationCancel(Animator animation) {
        }

        @Override
        public void onAnimationRepeat(Animator animation) {
        }
    };

    @Override
    public void onBackPressed() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Animator animator = createRevealAnimator(true, mX, mY);
            animator.start();
        } else {
            finish();
        }
    }

    private void initView() {
        mType = (TextView) findViewById(R.id.type);
        mUrl = (EditText) findViewById(R.id.url);
        mSendUrl = (TextInputLayout) findViewById(R.id.send_url);
        mDesc = (EditText) findViewById(desc);
        mSendDesc = (TextInputLayout) findViewById(R.id.send_desc);
        mWho = (EditText) findViewById(who);
        mSendWho = (TextInputLayout) findViewById(R.id.send_who);
        mSubmitGank = (Button) findViewById(R.id.submit_gank);

        mSubmitGank.setOnClickListener(v -> submit());
    }

    private void submit() {
        hideKeyboard();

        String urlString = mUrl.getText().toString().trim();
        if (TextUtils.isEmpty(urlString) || urlString.contains("http://")) {
            mSendUrl.setError("URL is not correct!");
        }

        String descString = mDesc.getText().toString().trim();
        if (TextUtils.isEmpty(descString) || descString.length() < 15) {
            mSendDesc.setError("Recommended reason can not be less than fifteen words!");
        }

        String whoString = mWho.getText().toString().trim();
        if (TextUtils.isEmpty(whoString)) {
            mSendWho.setError("The network ID can not be empty!");
        }
    }
}
