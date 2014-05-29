 package com.imcore.yunmingtea.ui;

import com.imcore.yunmingtea.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MoreFragment extends Fragment{
	private TextView tvMore;
	private ListView lvMore;
	private Button btnMore;
	private MoreAdapter adapter;
	private String[] textMore ={"关于芸茗","设置","意见反馈","推荐给好友","检查更新"};
	private int[] imgMore = {R.drawable.more_about,R.drawable.more_setting,
			R.drawable.more_feedback,R.drawable.more_recommend,R.drawable.more_check_update};
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_more, null);
		tvMore = (TextView) view.findViewById(R.id.tv_more);
		lvMore =(ListView) view.findViewById(R.id.lv_more);
		btnMore =(Button) view.findViewById(R.id.btn_more);
		adapter = new MoreAdapter();
		lvMore.setAdapter(adapter);
		
		return view;
	}
	
	private class MoreAdapter extends BaseAdapter{

		@Override
		public int getCount() {
		
			return 5;
		}

		@Override
		public Object getItem(int position) {

			return textMore.length;
		}

		@Override
		public long getItemId(int position) {

			return 0;
		}

		@Override
		public View getView(int position, View contentView, ViewGroup arg2) {
	       View view = contentView;
	       ViewHolder vh =null;
	       if(view ==null){
	    	   view =getActivity().getLayoutInflater().inflate(R.layout.view_more_list, null);
	    	   vh = new ViewHolder();
	    	   vh.mIageView =(ImageView) view.findViewById(R.id.iv_one);
	    	   vh.mTextView =(TextView) view.findViewById(R.id.tv_two);
	    	   view.setTag(vh);
	       }else{
	    	  vh =(ViewHolder) view.getTag(); 
	       }
	       vh.mTextView.setText(textMore[position]);
	       vh.mIageView.setImageResource(imgMore[position]);
	       
			return view;
		}
		class ViewHolder{
			ImageView mIageView;
			TextView mTextView;
			
		}
		
	}
}
