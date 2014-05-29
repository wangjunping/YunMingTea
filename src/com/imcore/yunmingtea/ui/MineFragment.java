package com.imcore.yunmingtea.ui;


import com.imcore.yunmingtea.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MineFragment extends Fragment{
	private TextView tvMine;
	private ImageView ivMine;
	private TextView txMine;
	private ImageButton ibMine;
	private ListView lvMine;
	private MineAdapter adapter;
	
	String[] textMine ={"订单记录","收藏夹","购物车","推送消息"};
	int [] ImageMine ={R.drawable.mine_orderlist,R.drawable.mine_favorite,
			R.drawable.mine_shopping_cart,R.drawable.mine_push_notification};
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_mine, null);
		tvMine = (TextView) view.findViewById(R.id.tv_mine);
		ivMine = (ImageView) view.findViewById(R.id.iv_mine_one);
		txMine =(TextView) view.findViewById(R.id.tv_mine_one);
		ibMine =(ImageButton) view.findViewById(R.id.ib_mine_one);
		lvMine =(ListView) view.findViewById(R.id.lv_mine);
		adapter =new MineAdapter();
		
		lvMine.setAdapter(adapter);
		
		
		return view;
	}
	
	private class MineAdapter extends BaseAdapter{

		@Override
		public int getCount() {
		
			return 4;
		}

		@Override
		public Object getItem(int position) {
	
			return ImageMine.length;
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
	    	vh = new ViewHolder();
	    	view =getActivity().getLayoutInflater().inflate(R.layout.view_mine_list, null);
	    	vh.iv= (ImageView) view.findViewById(R.id.iv_mine_one);
	    	vh.tv= (TextView) view.findViewById(R.id.tv_mine_two);
	    	
	    	view.setTag(vh);
	      }else{
	    	  vh = (ViewHolder) view.getTag();
	      }
	       
	      vh.iv.setImageResource(ImageMine[position]);
	      vh.tv.setText(textMine[position]);
			
			return view;
		}
		
		class ViewHolder{
			private ImageView iv;
			private TextView tv;
		}
		
	}
}
