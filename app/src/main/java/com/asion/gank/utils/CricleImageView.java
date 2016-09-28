package com.asion.gank.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Asion_Z on 16/8/18.
 */
public class CricleImageView extends ImageView{
    public CricleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CricleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CricleImageView(Context context) {
        super(context);
    }

    /**
     * 自定义中很重要的用来的绘制图像的方法
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable();

        if(drawable == null){
            return;
        }

        if(getHeight() ==0 || getWidth() == 0){
            return;
        }

        if(!(drawable instanceof BitmapDrawable)){
            return;
        }

        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();

        if(bitmap == null){
            return ;
        }
        //ARGB_8888——代表32位ARGB位图  位数越高 图像越逼真
        //ARGB_4444--代表16位ARGB位图
        Bitmap copy = bitmap.copy(Bitmap.Config.ARGB_8888, true);

        Bitmap cricleBitmap = getCricleBitmap(copy,getWidth()>getHeight()?getHeight():getWidth());
        canvas.drawBitmap(cricleBitmap,0,0,null);
    }

    public static Bitmap getCricleBitmap(Bitmap bitmap,int radius){
        Bitmap mBitmap;

        //判断是否需要裁剪
        if(bitmap.getHeight()!=radius || bitmap.getWidth()!=radius){
            mBitmap = Bitmap.createScaledBitmap(bitmap,radius,radius,false);
        }else{
            mBitmap=bitmap;
        }

        Bitmap output = Bitmap.createBitmap(mBitmap.getWidth(),mBitmap.getHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(output);

        final Paint paint = new Paint();
        final Rect rect = new Rect(0,0,mBitmap.getWidth(),mBitmap.getHeight());

        paint.setAntiAlias(true);//抗锯齿
        paint.setDither(true);//图像抖动，true为变清晰
        paint.setFilterBitmap(true);//优化操作

        canvas.drawARGB(0,0,0,0);
        paint.setColor(Color.parseColor("#BAB399"));
        canvas.drawCircle(mBitmap.getWidth()/2+0.7f,mBitmap.getHeight()/2+0.7f,mBitmap.getWidth()/2+0.1f,paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(mBitmap,rect,rect,paint);

        return output;
    }
}
