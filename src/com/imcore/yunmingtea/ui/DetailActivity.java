package com.imcore.yunmingtea.ui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.imcore.yunmingtea.R;
import com.imcore.yunmingtea.R.id;
import com.imcore.yunmingtea.R.layout;
import com.imcore.yunmingtea.R.menu;
import com.imcore.yunmingtea.http.HttpHelper;
import com.imcore.yunmingtea.http.HttpMethod;
import com.imcore.yunmingtea.http.RequestEntity;
import com.imcore.yunmingtea.http.ResponseJsonEntity;
import com.imcore.yunmingtea.image.ImageFetcher;
import com.imcore.yunmingtea.util.JsonUtil;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Checkable;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

public class DetailActivity extends Activity implements android.view.View.OnClickListener {

	private GridView gv;
	private ListView lv;
	private RadioButton rb1;//排序
	private RadioButton rb2;
	private Button btn1;//ListView按钮。。
	private Button btn2;
	private List<Commodity> myCommList;
	private Long id;
	private ProgressDialog pd;
	private static int sortIndex = 0;
	private Button btn3;//返回监听。。。
	
	private int FilterId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);

		gv = (GridView) findViewById(R.id.gv_gridview);
		lv = (ListView) findViewById(R.id.lv_listview);
		rb1 = (RadioButton) findViewById(R.id.radiobutton1);
		rb2 = (RadioButton) findViewById(R.id.radiobutton2);
		btn1 = (Button) findViewById(R.id.btn1);
		btn2 = (Button) findViewById(R.id.btn2);
		
		btn3 =(Button) findViewById(R.id.ib_details);
        
		add();
		rb1.setOnClickListener(this);
		rb2.setOnClickListener(this);
		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);
		btn3.setOnClickListener(this);

		gv.setOnItemClickListener(listener);
		lv.setOnItemClickListener(listener);
		
		
	}
	
	private OnItemClickListener listener=new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			Intent intent =  new Intent(DetailActivity.this,ProducatDetailActivity.class);
//			Bundle bundle = new Bundle();
//				bundle.putLong("id", myCommList.get(arg2).id);
				intent.putExtra("proId",  myCommList.get(arg2).id);
				startActivity(intent);
		}
	};
	
	
	
	

	private void add() {
		// Intent intent = getIntent();
		// Bundle bundle = intent.getBundleExtra("CommId");
		Bundle bundle = getIntent().getBundleExtra("CommId");
		id = bundle.getLong("ComID");
		new CommodityTask("", 0,0).execute();
		
	}

	class CommodityTask extends AsyncTask<Void, Void, Void> {
		private String orderBy;
		private int desc;
		private int filterId;
		public CommodityTask(String orderBy, int desc,int filterId) {
			this.desc = desc;
			this.orderBy = orderBy;
			this.filterId = filterId;
		}

		@Override
		protected Void doInBackground(Void... arg0) {
			Map<String, Object> map = new HashMap<String, Object>();
			String url = "/category/products.do";
			map.put("id", id);// 要弄个全局的id 不然取不到。。。
			map.put("orderBy", orderBy);
			map.put("desc", desc);
			map.put("filterId", filterId);
			RequestEntity entity = new RequestEntity(url, HttpMethod.GET, map);
			String js;
			try {
				js = HttpHelper.execute(entity);
				ResponseJsonEntity rjs = ResponseJsonEntity.fromJSON(js);
				String data = rjs.getData();
				Log.i("js", data);
				Class<Commodity> cla = Commodity.class;

				myCommList = JsonUtil.toObjectList(data, cla);

//				for (int i = 0; i < myCommList.size(); i++) {
//					Log.i("ee", myCommList.get(i).getImageUrl() +myCommList.get(i).getProductName());
//					String imgUrl = myCommList.get(i).getImageUrl();
//					System.out.println("before");
//					new ImgTask().execute(HttpHelper.DOMAIN+ "/" + imgUrl);
//					System.out.println("after");
//				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			return null;
		}
		@Override
		protected void onPostExecute(Void result) {
			for (int i = 0; i < myCommList.size(); i++) {
				Log.i("ee", myCommList.get(i).getImageUrl() +myCommList.get(i).getProductName());
				String imgUrl = myCommList.get(i).getImageUrl();
				System.out.println("before");
//				new ImgTask().execute(HttpHelper.DOMAIN+ "/" + imgUrl);
				System.out.println("after");
				gv.setAdapter(new CommodityAdapter());
				lv.setAdapter(new CommodityAdapter());
				lv.setVisibility(View.GONE);
			}
			super.onPostExecute(result);
		}

	}

	class ImgTask extends AsyncTask<String, Void, Void> {
		private String imgUrl;
		
		@Override
		protected void onPreExecute() {//准备时，进度条。。。
			pd = ProgressDialog.show(DetailActivity.this, "芸茗茶叶", "正在加载");
			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(String... params) {
			imgUrl = params[0];
			boolean isSucc = ImageFetcher.downLoadImage(imgUrl);
			Log.i("img", isSucc + "");
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {//在结果这里设置适配器。。。
			super.onPostExecute(result);
			pd.dismiss();
			gv.setAdapter(new CommodityAdapter());
			lv.setAdapter(new CommodityAdapter());
			lv.setVisibility(View.GONE);
		}
      
	}

	private class CommodityAdapter extends BaseAdapter {
		@Override
		public int getCount() {
		
			return myCommList.size();
		}

		@Override
		public Object getItem(int arg0) {

			return myCommList.get(arg0);
		}

		@Override
		public long getItemId(int arg0) {
			return arg0;
		}

		@SuppressWarnings("unused")
		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			View view = arg1;
			ViewHolder viewHolder = null;
			if (viewHolder == null) {
				view = getLayoutInflater().inflate(R.layout.view_detail_list,null);
				viewHolder = new ViewHolder();
				viewHolder.tvName = (TextView) view.findViewById(R.id.tv_commodity_name);
				viewHolder.tvPrice = (TextView) view.findViewById(R.id.tv_commodity_price);
				viewHolder.tvsaleTotal = (TextView) view.findViewById(R.id.tv_commodity_saleTotal);
				viewHolder.tvfavotieTotal = (TextView) view.findViewById(R.id.tv_commodity_favotieTotal);
				viewHolder.img = (ImageView) view.findViewById(R.id.img_commodity);
				view.setTag(viewHolder);
//				if (((Checkable) btn1).isChecked()) {
//
//				}
			} else {
				viewHolder = (ViewHolder) view.getTag();
			}
			String Name = myCommList.get(arg0).getProductName();
			long price = myCommList.get(arg0).getPrice();
			long saleTotal = myCommList.get(arg0).getSaleTotal();
			long favotieTotal = myCommList.get(arg0).getFavotieTotal();
			new ImageFetcher().fetch("http://yunming-api.suryani.cn" + "/" +myCommList.get(arg0).getImageUrl(), viewHolder.img);
			
			viewHolder.tvName.setText(Name);
			viewHolder.tvPrice.setText("￥" + price);
			viewHolder.tvsaleTotal.setText("销量：" + saleTotal);
			viewHolder.tvfavotieTotal.setText("收藏：" + favotieTotal);
			return view;
		}

		class ViewHolder {
			TextView tvName, tvPrice, tvsaleTotal, tvfavotieTotal;
			ImageView img;
		}
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.btn2:
			gv.setVisibility(View.VISIBLE);
			lv.setVisibility(View.GONE);
			break;
		case R.id.btn1:
			gv.setVisibility(View.GONE);
			lv.setVisibility(View.VISIBLE);
			break;
		case R.id.radiobutton1:
			new AlertDialogInCommodity();
			break;
		case R.id.radiobutton2:
            new AlertDialogPriceFilter();
			break;
		case R.id.ib_details:
            finish();
			break;
		}
	}
		class AlertDialogInCommodity {
			private String orderBy;
			private int desc;
			String[] sort = new String[] { "按价格升序排序", "按价格降序排序", "按上架时间升序排序",
					"按上架时间降序排序", "按销量降序排序", "按收藏降序排序" };

			public AlertDialogInCommodity() {
				orderBy = "price";
				desc = 0;
				new AlertDialog.Builder(DetailActivity.this)
						.setTitle("请选择排序方式").setSingleChoiceItems(sort, sortIndex,
								new OnClickListener() {

									@Override
									public void onClick(DialogInterface arg0,
											int arg1) {
										switch (arg1) {
										case 0:
											orderBy = "price";
											desc = 0;
											break;
										case 1:
											orderBy = "price";
											desc = 1;
											break;
										case 2:
											break;
										case 3:
											break;
										case 4:
											break;
										case 5:
											break;

										default:
											break;
										}
										sortIndex = arg1;
									}
								
										
									}).setPositiveButton("确认", new OnClickListener() {
										
										@Override
										public void onClick(DialogInterface arg0, int arg1) {
										
											new CommodityTask(orderBy, desc,0).execute();//在传入两个值。异步加载。。。
										}
									}).setNegativeButton("取消", null).show();
									
									
		}
    }
		
		
		private class AlertDialogPriceFilter {
			AlertDialog dialog;
			private int a=0;
			private String orderBy;
			private int desc;
			private int filterId;
			private int filterIndex;
			String[] filter = new String[] {"默认","0-1000","1000-2000","2000-3000",">3000"};
			public AlertDialogPriceFilter() {
				orderBy = "";
				desc = 0;
				filterIndex = 0;
				new AlertDialog.Builder(DetailActivity.this)
						.setTitle("请选择排序方式").setSingleChoiceItems(filter, filterIndex,
								new OnClickListener() {

									@Override
									public void onClick(DialogInterface arg0,
											int arg1) {
										switch (arg1) {
										case 0:
											filterId = 0;
											break;
										case 1:
											filterId = 1;
											break;
										case 2:
											filterId = 2;
											break;
										case 3:
											filterId = 3;
											break;
										case 4:
											filterId = 4;
											break;
										

										default:
											break;
										}
										filterIndex = arg1;
									}
								
										
									}).setPositiveButton("确认", new OnClickListener() {
										
										@Override
										public void onClick(DialogInterface arg0, int arg1) {
										
											new CommodityTask(orderBy, desc,filterId).execute();//在传入两个值。异步加载。。。
										}
									}).setNegativeButton("取消", null).show();
									
									
		}

	
}
}
