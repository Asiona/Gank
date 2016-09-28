package com.asion.gank.behaivors;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.view.View;

//
//
//  现在的努力,坚持和汗水,
//       是为了在未来给我爱的人和爱我的人
//             一个温馨快乐幸福的  家
//
//

public class FloatingBehaivar extends FloatingActionButton.Behavior {

    public FloatingBehaivar() {
        super();
    }

    public FloatingBehaivar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View directTargetChild, View target, int nestedScrollAxes) {
        return true;
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        if(dyConsumed>0){
            child.hide();
        }else{
            child.show();
        }
    }
}
