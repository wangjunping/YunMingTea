package com.imcore.yunmingtea.ui;

import java.util.ArrayList;
import java.util.List;

import com.imcore.yunmingtea.R;
import com.imcore.yunmingtea.http.HttpHelper;
import com.imcore.yunmingtea.http.RequestEntity;
import com.imcore.yunmingtea.http.ResponseJsonEntity;
import com.imcore.yunmingtea.image.ImageFetcher;
import com.imcore.yunmingtea.model.BigClass;
import com.imcore.yunmingtea.model.SmartClass;
import com.imcore.yunmingtea.util.JsonUtil;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings.SettingNotFoundException;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.HeterogeneousExpandableList;
import android.widget.ImageView;
import android.widget.TextView;

public class MallFragment extends Fragment{
	private ExpandableListView expandable;
	private List<BigClass> bigList;
	private List<String> bigSList;
	private List<SmartClass> smartList ;
	private List<String> smartSList;
	
	private List<List<SmartClass>> mList;//所有小类。。。
	private List<List<String>> nlist;//所有小类的字段。。。
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_mall, null);
		expandable = (ExpandableListView) view.findViewById(R.id.el_expandable);
		//大类
		new ClassTask().execute();
		
          expandable.setOnChildClickListener(new OnChildClickListener() {
			
			@Override
			public boolean onChildClick(ExpandableListView arg0, View arg1, int arg2,
					int arg3, long arg4) {
				Intent intent = new Intent(getActivity(),DetailActivity.class);
				Bundle bundle = new Bundle();
				bundle.putLong("ComID", mList.get(arg2).get((int)arg4).getId());
				intent.putExtra("CommId", bundle);
				startActivity(intent);
				return true;
			}
		});
		
		return view;
	}
	
	private class ExpandableAdapter extends BaseExpandableListAdapter{
		
		@Override
		public boolean isEmpty() {

			return false;
		}
		
		@Override
		public boolean isChildSelectable(int arg0, int arg1) {

			return true;
		}
		
		@Override
		public boolean hasStableIds() {
			return false;
		}
		
		@Override
		public View getGroupView(int arg0, boolean isExpanded, View convertView, ViewGroup parent) {
		    View view = convertView;
		    GroupViewHolder Holder =null;
		    if(view==null){
		    	Holder = new GroupViewHolder();
		    	view = getActivity().getLayoutInflater().inflate(R.layout.view_class_list, null);
		    	Holder.img1 = (ImageView) view.findViewById(R.id.iv_class);
		    	Holder.tv1 = (TextView) view.findViewById(R.id.tv_class);
		    	view.setTag(Holder);
		    }else{
		    	Holder =(GroupViewHolder) view.getTag();
		    }
		     String s1 = bigSList.get(arg0);
			 Holder.tv1.setText(s1);
			 new ImageFetcher().fetch("http://yunming-api.suryani.cn"+"/"+bigList
					 .get(arg0).getImageUrl(), Holder.img1);
			return view;
		}
		
		class GroupViewHolder{
			private ImageView img1;
			private TextView tv1;
			
		}
		
		@Override
		public long getGroupId(int arg0) {
		  
			return arg0;
		}
		
		@Override
		public int getGroupCount() {
	
			return 4;
		}
		
		@Override
		public Object getGroup(int arg0) {
			
			return bigSList.get(arg0);
		}
		
		
		@Override
		public int getChildrenCount(int arg0) {
		
			return nlist.get(arg0).size();
		}
		
		@Override
		public View getChildView(int arg0, int arg1, boolean arg2, View convertView,
				ViewGroup arg4) {
			View view =convertView;
			ChildViewHolder Holder =null;
			if(view==null){
				Holder =new ChildViewHolder();
				view = getActivity().getLayoutInflater().inflate(R.layout.view_section_list, null);
				Holder.iv2 = (ImageView) view.findViewById(R.id.iv_section);
				Holder.tv2 = (TextView) view.findViewById(R.id.tv_section);
				view.setTag(Holder);			
			}else{
				Holder =(ChildViewHolder) view.getTag();
			}
			  String s2 =mList.get(arg0).get(arg1).getCategoryName();
			  Holder.tv2.setText(s2);
			new ImageFetcher().fetch("http://yunming-api.suryani.cn"+"/"+
			  mList.get(arg0).get(arg1).getImageUrl(), Holder.iv2); 
			return view;
		}
		
		class ChildViewHolder{
			private ImageView iv2;
			private TextView tv2;
		}
		
		@Override
		public long getChildId(int arg0, int arg1) {
		
			return arg1;
		}
		
		@Override
		public Object getChild(int arg0, int arg1) {
		 
			return nlist.get(arg0).get(arg1);
		}

		@Override
		public boolean areAllItemsEnabled() {
			return false;
		}

		@Override
		public long getCombinedChildId(long arg0, long arg1) {
			return 0;
		}

		@Override
		public long getCombinedGroupId(long arg0) {
			return 0;
		}
		
	}
	  //大类的解析
	 class ClassTask extends AsyncTask<Void, Void, Void>{

		@Override
		protected Void doInBackground(Void... arg0) {
		 String url ="/category/list.do";
		 RequestEntity entity = new RequestEntity(url, 0, null);
		 String json =null;
		 
			 try {
				json = HttpHelper.execute(entity);
				ResponseJsonEntity JsonEntity = ResponseJsonEntity.fromJSON(json);
				String data = JsonEntity.getData();
				Log.i("json",data);
				
				Class<BigClass> cass = BigClass.class;//映射。。。
				bigList =JsonUtil.toObjectList(data,cass); 
				bigSList =new ArrayList<String>();
				
			    for(int i=0;i<bigList.size();i++){
				String bigName =bigList.get(i).getCategoryName();
				String bigUrl = bigList.get(i).getImageUrl();
				bigSList.add(bigName);
				
			    new ImageTask().execute(HttpHelper.DOMAIN+"/"+bigUrl);
			   
			}
			    
			} catch (Exception e) {
		
				e.printStackTrace();
			}
			return null;
		}
		@Override
		protected void onPostExecute(Void result) {
			 new SectionTask().execute();
				
			super.onPostExecute(result);
		}
		 
	 }
	 //图片下载、、、
	 class ImageTask extends AsyncTask<String, Void, Void>{
        private String imgUrl;
		@Override
		protected Void doInBackground(String... params) {
          imgUrl = params[0];
          //
          boolean s =ImageFetcher.downLoadImage(imgUrl);
			return null;
		}
		 
	 }
	 
	 class SectionTask extends AsyncTask<Void, Void, Void>{

		@Override
		protected Void doInBackground(Void... params) {
		  mList = new ArrayList<List<SmartClass>>();
		  nlist = new ArrayList<List<String>>();
		  for(int i=1;i<bigList.size();i++){
			 String url ="/category/list.do?id="+i; 
			 RequestEntity entity = new RequestEntity(url, 0, null);
			 String json=null;
			   try {
				json = HttpHelper.execute(entity);
				ResponseJsonEntity rje =ResponseJsonEntity.fromJSON(json);
				String data = rje.getData();
				Log.i("ac",data);
				
				Class<SmartClass> cls =SmartClass.class;
				smartList = JsonUtil.toObjectList(data, cls);
				smartSList = new ArrayList<String>();
				mList.add(smartList);
				nlist.add(smartSList);
				
			for(int j =0;j<smartList.size();j++){
				Log.i("aaaa",smartList.get(j).getCategoryName()+smartList.get(j).getImageUrl());
				String smartName=smartList.get(j).getCategoryName();
				String smartUrl=smartList.get(j).getImageUrl();
				smartSList.add(smartName);
				new ImageTask().execute(HttpHelper.DOMAIN+"/"+smartUrl);
			}	
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
		  }
			return null;
		}
		 
		@Override
		protected void onPostExecute(Void result) {
			expandable.setAdapter(new ExpandableAdapter());
			super.onPostExecute(result);
		}
	 }
}    

