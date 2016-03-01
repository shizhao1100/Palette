package com.SZ_P.Palette;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends Activity {
	//声明SeekBar
	private SeekBar seekbarR = null;
	private SeekBar seekbarG = null;
	private SeekBar seekbarB = null;
	
	//声明EditText
	private EditText textR = null;
	private EditText textG = null;
	private EditText textB = null;
	private EditText textRGB = null;

	//声明翻页
	private ViewPager viewPager = null;
	
    // 声明List
	private List<Coloritem> ColoritemList = null;

	
	private View view1 = null;
	private View view2 = null;
	
	ColorAdapter adapter=null;
	// listView
	ListView listView;
	//
	LinearLayout layout;
	//
	Draw drawView;
	
	// 颜色表数据	
	public String[] data = { "#FFFFFF", "#CCCCCC", "#999999", "#666666",
			"#333333", "#000000", "#990033", "#CC6699", "#FF6699", "#FF3366",
			"#993366", "#CC0066", "#CC0033", "#FF0066", "#FF0033", "#CC3399",
			"#FF3399", "#FF9999", "#FF99CC", "#FF0099", "#CC3366", "#FF66CC",
			"#FF33CC", "#FFCCFF", "#FF99FF", "#FF00CC", "#FF66FF", "#CC33CC",
			"#CC00FF", "#FF33FF", "#CC99FF", "#9900CC", "#FF00FF", "#CC66FF",
			"#990099", "#CC0099", "#C33FFF", "#CC99CC", "#990066", "#993399",
			"#CC66CC", "#CC00CC", "#663366", "#660099", "#666FF0", "#000CCC",
			"#9933CC", "#666699", "#660066", "#333366", "#0066CC", "#9900FF",
			"#333399", "#99CCFF", "#9933FF", "#330099", "#6699FF", "#9966CC",
			"#3300CC", "#003366", "#330033", "#3300FF", "#6699CC", "#663399",
			"#3333FF", "#006699", "#6633CC", "#3333CC", "#3399CC", "#6600CC",
			"#0066FF", "#0099CC", "#9966FF", "#0033FF", "#66CCFF", "#330066",
			"#3366FF", "#3399FF", "#6600FF", "#3366CC", "#003399", "#6633FF",
			"#000066", "#0099FF", "#CCCCFF", "#000033", "#33CCFF", "#9999FF",
			"#0000FF", "#00CCFF", "#9999CC", "#000099", "#6666CC", "#033CCC",
			"#FFFFCC", "#FFCC00", "#CC9909", "#663300", "#FF6600", "#663333",
			"#CC6666", "#FF6666", "#FF0000", "#FFFF99", "#FFCC66", "#FF9900",
			"#FF9966", "#CC3300", "#996666", "#FFCCCC", "#660000", "#FF3300",
			"#FF6666", "#FFCC33", "#CC6600", "#FF6633", "#996633", "#CC9999",
			"#FF3333", "#990000", "#CC9966", "#FFFF33", "#CC9933", "#993300",
			"#FF9933", "#330000", "#993333", "#CC3333", "#CC0000", "#FFCC99",
			"#FFFF00", "#996600", "#CC6633", "#99FFFF", "#33CCCC", "#00CC99",
			"#99FF99", "#009966", "#33FF33", "#33FF00", "#99CC33", "#CCC330",
			"#66FFFF", "#66CCCC", "#66FFCC", "#66FF66", "#009933", "#00CC33",
			"#66FF00", "#336600", "#333000", "#33FFFF", "#339999", "#99FFCC",
			"#339933", "#33FF66", "#33CC33", "#99FF00", "#669900", "#666600",
			"#00FFFF", "#336666", "#00FF90", "#99CC99", "#00FF66", "#66FF33",
			"#66CC00", "#99CC00", "#999933", "#00CCCC", "#006666", "#339966",
			"#66FF99", "#CCFFCC", "#00FF00", "#00CC00", "#CCFF66", "#CCCC66",
			"#009999", "#003333", "#006633", "#33FF99", "#CCFF99", "#66CC33",
			"#33CC00", "#CCFF33", "#666633", "#669999", "#00FFCC", "#336633",
			"#33CC66", "#99FF66", "#006600", "#339900", "#CCFF00", "#999966",
			"#99CCCC", "#33FFCC", "#669966", "#00CC66", "#99FF33", "#003300",
			"#99CC66", "#999900", "#CCCC99", "#CCFFFF", "#33CC99", "#66CC66",
			"#66CC99", "#00FF33", "#009900", "#669900", "#669933", "#CCCC00" };
	
	// 初始化颜色表
	private void initColoritemList() {
		ColoritemList=new ArrayList<Coloritem>();
		for (int i = 0; i < data.length; i++) {
			Coloritem color = new Coloritem(data[i]);
			ColoritemList.add(color);
		}
	}

	// 初始化翻页
	private void initViewPager() {	
		ArrayList<View> views = new ArrayList<View>();
		views.add(view1);
		views.add(view2);
		MYViewPagerAdapter adapter = new MYViewPagerAdapter();
		adapter.setViews(views);
		viewPager.setAdapter(adapter);
	}

	// 初始化View类
	private void InitView() {
		// 加载View布局进LinearLayout
		layout = (LinearLayout) view1.findViewById(R.id.viewGroup);
		// drawView初始化
		// 建立Draw对象
		drawView = new Draw(this);
		// 初始化颜色
		drawView.paint.setColor(Color.rgb(128, 128, 128));
		// 初始化位置
		drawView.setMinimumHeight(300);
		drawView.setMinimumWidth(500);
		// 初始化大小
		drawView.GetSizi();
		layout.addView(drawView);
	}

	// 失去焦点并隐藏键盘
	private void LostFocus_hideKeyboard(EditText text) {
		text.setFocusable(false);
		View view = getWindow().peekDecorView();
		if (view != null) {
			InputMethodManager inputmanger = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
		}

	}

	// 获得焦点并显示键盘
	private void GetFocus_showKeyboard(EditText text) {
		text.setFocusable(true);
		text.setFocusableInTouchMode(true);
		text.requestFocus();
		text.findFocus();
		InputMethodManager inputManager = (InputMethodManager) text
				.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
		inputManager.showSoftInput(text, 0);
	}

	// GetRGB
	private void RGBTextShow() {
		int Color = drawView.paint.getColor();
		int r = (Color & 0xff0000) >> 16;
		int g = (Color & 0xff00) >> 8;
		int b = (Color & 0xff);
		String SRGB = "#" + DECToHEX(r) + DECToHEX(g) + DECToHEX(b);
		textRGB.setText(SRGB);
	}

	// DECToHEX
	private String DECToHEX(int color) {
		if (color < 16) {
			return "0" + Integer.toHexString(color);
		} else
			return Integer.toHexString(color);
	}

	// RGBTextIsMsg
	private boolean IsMsg(String string) {
		char Arry1 = string.charAt(0);
		char Arry2 = string.charAt(1);
		int Arry01 = Arry1;
		int Arry02 = Arry2;

		if ((47 < Arry01 && Arry01 < 58) || (96 < Arry01 && Arry01 < 103)
				|| (64 < Arry01 && Arry01 < 71)) {
			if ((47 < Arry02 && Arry02 < 58) || (96 < Arry02 && Arry02 < 103)
					|| (64 < Arry02 && Arry02 < 71)) {
				return true;
			} else
				return false;
		} else
			return false;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		// 隐藏标题栏
	    requestWindowFeature(Window.FEATURE_NO_TITLE);
	    
		//绑定布局
		setContentView(R.layout.activity_main);
		
		viewPager = (ViewPager) findViewById(R.id.viewPager);
		view1 = LayoutInflater.from(this).inflate(R.layout.palette, null);
		view2 = LayoutInflater.from(this).inflate(R.layout.colorcodelist, null);
		//初始化View类
		InitView();
		//初始化翻页
		initViewPager();
		//初始化coloritemlist
		initColoritemList();
		
		adapter = new ColorAdapter(MainActivity.this,
        		R.layout.color_itme, ColoritemList);
		listView =(ListView)view2.findViewById(R.id.listView1);
        listView.setAdapter(adapter);

		// 绑定
		seekbarR = (SeekBar) view1.findViewById(R.id.Bar_R);
		textR = (EditText) view1.findViewById(R.id.Text_R);

		seekbarG = (SeekBar) view1.findViewById(R.id.Bar_G);
		textG = (EditText) view1.findViewById(R.id.Text_G);

		seekbarB = (SeekBar) view1.findViewById(R.id.Bar_B);
		textB = (EditText) view1.findViewById(R.id.Text_B);

		textRGB = (EditText) view1.findViewById(R.id.Text_RGB);
		LostFocus_hideKeyboard(textR);
		LostFocus_hideKeyboard(textG);
		LostFocus_hideKeyboard(textB);
		LostFocus_hideKeyboard(textRGB);
		// 设置Seekbar监听器
		this.seekbarR
				.setOnSeekBarChangeListener(new OnSeekBarChangeListenerImpR());
		this.seekbarG
				.setOnSeekBarChangeListener(new OnSeekBarChangeListenerImpG());
		this.seekbarB
				.setOnSeekBarChangeListener(new OnSeekBarChangeListenerImpB());

		// 设置EditText监听器
		textR.addTextChangedListener(TextWatcherR);
		textG.addTextChangedListener(TextWatcherG);
		textB.addTextChangedListener(TextWatcherB);

		textR.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				GetFocus_showKeyboard(textR);
			}
		});
		textG.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				GetFocus_showKeyboard(textG);
			}
		});
		textB.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				GetFocus_showKeyboard(textB);
			}
		});
		textRGB.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				GetFocus_showKeyboard(textRGB);
			}
		});

		textRGB.addTextChangedListener(TextWatcherRGB);
		textRGB.setOnFocusChangeListener(new OnFocusChangeListenerimp());
		
		
	}

	// 监听器
	// RSeekBar
	private class OnSeekBarChangeListenerImpR implements
			SeekBar.OnSeekBarChangeListener {
		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {

			drawView.GetColor(seekBar.getProgress(), -1, -1);
			String s = "" + drawView.OldR;

			drawView.postInvalidate();
			// MainActivity.this.textR.setText(""+seekBar.getProgress()+"");
			MainActivity.this.textR.setText(s);
		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
		}

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
		}
	}

	// BSeekBar
	private class OnSeekBarChangeListenerImpG implements
			SeekBar.OnSeekBarChangeListener {
		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {

			drawView.GetColor(-1, seekBar.getProgress(), -1);
			String s = "" + drawView.OldG;

			drawView.postInvalidate();
			// MainActivity.this.textG.setText(""+seekBar.getProgress()+"");
			MainActivity.this.textG.setText(s);
		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
		}

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
		}

	}

	// BSeekBar
	private class OnSeekBarChangeListenerImpB implements
			SeekBar.OnSeekBarChangeListener {
		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {

			drawView.GetColor(-1, -1, seekBar.getProgress());
			String s = "" + drawView.OldB;

			drawView.postInvalidate();
			// MainActivity.this.textB.setText(""+seekBar.getProgress()+"");
			MainActivity.this.textB.setText(s);
		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
		}

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
		}

	}

	// REditText
	TextWatcher TextWatcherR = new TextWatcher() {
		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
		}

		@Override
		public void afterTextChanged(Editable s) {
			String inputText = textR.getText().toString();
			if (inputText.length() == 3) {
				LostFocus_hideKeyboard(textR);
			}
			int R = 0;
			if ("".equals(inputText)) {
				R = 0;
			} else
				R = Integer.valueOf(inputText);
			String a = "" + R;
			if (R > 255) {
				R = 255;
				textR.setText("255");
				MainActivity.this.seekbarR.setProgress(255);
				textR.setSelection(textR.length());
			} else {
				MainActivity.this.seekbarR.setProgress(R);
				textR.setSelection(textR.length());
			}
			RGBTextShow();
		}
	};
	// GEditText
	// GREditText
	TextWatcher TextWatcherG = new TextWatcher() {
		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
		}

		@Override
		public void afterTextChanged(Editable s) {
			String inputText = textG.getText().toString();
			if (inputText.length() == 3) {
				LostFocus_hideKeyboard(textG);
			}
			int G = 0;
			if ("".equals(inputText)) {
				G = 0;
			} else
				G = Integer.valueOf(inputText);
			String a = "" + G;
			if (G > 255) {
				G = 255;
				textG.setText("255");
				MainActivity.this.seekbarR.setProgress(255);
				textG.setSelection(textG.length());
			} else {
				MainActivity.this.seekbarG.setProgress(G);
				textG.setSelection(textG.length());
			}
			RGBTextShow();
		}

	};
	// BEditText
	// BEditText
	TextWatcher TextWatcherB = new TextWatcher() {

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
		}

		@Override
		public void afterTextChanged(Editable s) {
			String inputText = textB.getText().toString();
			if (inputText.length() == 3) {
				LostFocus_hideKeyboard(textB);
			}
			int B = 0;
			if ("".equals(inputText)) {
				B = 0;
			} else
				B = Integer.valueOf(inputText);
			String a = "" + B;
			if (B > 255) {
				B = 255;
				textB.setText("255");
				MainActivity.this.seekbarB.setProgress(255);
				textB.setSelection(textB.length());
			} else {
				MainActivity.this.seekbarB.setProgress(B);
				textB.setSelection(textB.length());
			}
			RGBTextShow();
		}
	};
	// RGBEditText
	TextWatcher TextWatcherRGB = new TextWatcher() {

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
		}

		@Override
		public void afterTextChanged(Editable s) {
			String inputText = textRGB.getText().toString();
			if (textRGB.isFocused()) {
				if (inputText.length() == 7) {
					LostFocus_hideKeyboard(textRGB);
				}
			}
		}
	};

	private class OnFocusChangeListenerimp implements OnFocusChangeListener {
		@Override
		public void onFocusChange(View v, boolean hasFocus) { // hasFocus表示是否获得焦点
			if (!hasFocus) {
				String inputText = textRGB.getText().toString();

				if (inputText.length() == 7) {
					layout.requestFocus();
					String q = inputText.substring(0, 1);
					if (q.equals("#")) {
						String SR = inputText.substring(1, 3);
						String SG = inputText.substring(3, 5);
						String SB = inputText.substring(5, 7);

						if (IsMsg(SR)) {
							if (IsMsg(SG)) {
								if (IsMsg(SB)) {
									MainActivity.this.seekbarR
											.setProgress(Integer.parseInt(SR,
													16));
									MainActivity.this.seekbarG
											.setProgress(Integer.parseInt(SG,
													16));
									MainActivity.this.seekbarB
											.setProgress(Integer.parseInt(SB,
													16));
								} else
									Toast.makeText(MainActivity.this,
											"请输入#+6位16进制代码", Toast.LENGTH_SHORT)
											.show();
							} else
								Toast.makeText(MainActivity.this,
										"请输入#+6位16进制代码", Toast.LENGTH_SHORT)
										.show();
						} else
							Toast.makeText(MainActivity.this, "请输入#+6位16进制代码",
									Toast.LENGTH_SHORT).show();
					} else {
						Toast.makeText(MainActivity.this, "请输入#+6位16进制代码",
								Toast.LENGTH_SHORT).show();
					}
				} else {
					Toast.makeText(MainActivity.this, "请输入#+6位16进制代码",
							Toast.LENGTH_SHORT).show();
				}

			}
		}
	}

}
