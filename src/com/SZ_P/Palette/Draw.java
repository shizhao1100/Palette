package com.SZ_P.Palette;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.view.WindowManager;
  
// 自定义 view,需要实现 一个显式的构造函数，重写 onDraw 方法，一切的操作都在该方法上进行  
public class Draw extends View {  
	
	public Paint paint = new Paint();
	public int OldR=128,OldG=128,OldB=128;
    public static Object draw;
    public int width;
    public int height;

	public void GetSizi() {
		WindowManager wm = (WindowManager) getContext().getSystemService(
				Context.WINDOW_SERVICE);
		 width = wm.getDefaultDisplay().getWidth();
		 height = wm.getDefaultDisplay().getHeight();
	}
	public Draw(Context context)  
    {   
        super(context);  
    }  
      
    /** 
     * 要画图形，最起码要有三个对象： 
     * 1.颜色对象 Color 
     * 2.画笔对象 Paint 
     * 3.画布对象 Canvas 
     */  
	
	
	
	public void GetColor(int R,int G,int B){		
		if(R!=-1)
		{
			OldR=R;
		}
		if(G!=-1)
		{
			OldG=G;
		}
		if(B!=-1)
		{
			OldB=B;
		}
		paint.setColor(Color.rgb(OldR, OldG, OldB)); 
		
		}  
    @Override  
  
    public void onDraw(Canvas canvas) {  
        // TODO Auto-generated method stub  
        paint.setAntiAlias(true);
        //让画出的图形是实心心的  
        paint.setStyle(Paint.Style.FILL);  
        //设置画出的线的 粗细程度  
        paint.setStrokeWidth(5);         
        //画矩形  
        canvas.drawRect(1, 1, width, 100, paint);         
        //画圆  
        canvas.drawCircle(width/2, 100+width/2, 2*width/5, paint);           
        //绘制图片  
//        canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher), 150, 150, paint);  
          
        super.onDraw(canvas);  
    }  
	
}  