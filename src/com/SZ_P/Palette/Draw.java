package com.SZ_P.Palette;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.view.WindowManager;
  
// �Զ��� view,��Ҫʵ�� һ����ʽ�Ĺ��캯������д onDraw ������һ�еĲ������ڸ÷����Ͻ���  
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
     * Ҫ��ͼ�Σ�������Ҫ���������� 
     * 1.��ɫ���� Color 
     * 2.���ʶ��� Paint 
     * 3.�������� Canvas 
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
        //�û�����ͼ����ʵ���ĵ�  
        paint.setStyle(Paint.Style.FILL);  
        //���û������ߵ� ��ϸ�̶�  
        paint.setStrokeWidth(5);         
        //������  
        canvas.drawRect(1, 1, width, 100, paint);         
        //��Բ  
        canvas.drawCircle(width/2, 100+width/2, 2*width/5, paint);           
        //����ͼƬ  
//        canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher), 150, 150, paint);  
          
        super.onDraw(canvas);  
    }  
	
}  