package com.imcore.yunmingtea.ui;

import com.imcore.yunmingtea.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MallFragment extends Fragment{
	private TextView tvMall;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_mall, null);
		tvMall = (TextView) view.findViewById(R.id.tv_mall);
		return view;
	}
}
