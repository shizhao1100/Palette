package com.SZ_P.Palette;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ColorAdapter extends ArrayAdapter<Coloritem> {
	private int resourceId;

	public ColorAdapter(Context context, int textViewResourceId,
			List<Coloritem> objects) {
		super(context, textViewResourceId, objects);
		resourceId = textViewResourceId;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Coloritem coloritem = getItem(position); 
		View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
		TextView ColorText1 = (TextView) view.findViewById(R.id.textView1);
		ColorText1.setBackgroundColor(Color.parseColor(coloritem.getName1()));
		ColorText1.setText(coloritem.getName1());
		return view;
	}
}
