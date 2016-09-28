package com.asion.gank.utils;
//
//
//  现在的努力,坚持和汗水,
//       是为了在未来给我爱的人和爱我的人
//             一个温馨快乐幸福的  家
//
//

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.asion.gank.R;

public class SelectPicPopupWindow extends PopupWindow {

    private LinearLayout layout;
    private View mMenuView;

    public SelectPicPopupWindow(Activity context) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.popwindow_item, null);
        layout = ((LinearLayout) mMenuView.findViewById(R.id.linear));

        mMenuView.findViewById(R.id.normal).setOnClickListener(v ->
                Toast.makeText(context, "normnal", Toast.LENGTH_SHORT).show()
        );
        mMenuView.findViewById(R.id.yaoyiyao).setOnClickListener(v ->
                Toast.makeText(context, "yaoyiyao", Toast.LENGTH_SHORT).show()
        );

        this.setBackgroundDrawable(null);
        this.setContentView(mMenuView);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);
        this.setAnimationStyle(R.style.AnimationBottomFade);
        mMenuView.setOnTouchListener((a, b) -> {
            int height = layout.getTop();
            int y = (int) b.getY();
            if (b.getAction() == MotionEvent.ACTION_UP) {
                if (y < height) {
                    dismiss();
                }
            }
            return true;
        });
    }
}