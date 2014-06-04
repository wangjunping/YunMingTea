package com.imcore.yunmingtea.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.imcore.yunmingtea.R;
import com.imcore.yunmingtea.application.MyApplication;
import com.imcore.yunmingtea.image.ImageFetcher;

public class HomeImageFragment extends Fragment {
	private ImageView mImag;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.topimage_fragment, null);
		mImag = (ImageView) view.findViewById(R.id.img_fragment);
		Bundle arg = getArguments();
		int position = arg.getInt(HomeFragment.IMG);
		ImageFetcher fetcher = new ImageFetcher();
		fetcher.fetch(MyApplication.imgicUrl.get(position),mImag);
		
		
		return view;
	}
	
	
		
	}

	
	

