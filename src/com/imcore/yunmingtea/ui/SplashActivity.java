package com.imcore.yunmingtea.ui;

import java.util.ArrayList;
import java.util.List;

import com.imcore.yunmingtea.R;
import com.imcore.yunmingtea.application.MyApplication;
import com.imcore.yunmingtea.data.TopImages;
import com.imcore.yunmingtea.http.HttpHelper;
import com.imcore.yunmingtea.http.HttpMethod;
import com.imcore.yunmingtea.http.RequestEntity;
import com.imcore.yunmingtea.http.ResponseJsonEntity;
import com.imcore.yunmingtea.image.ImageFetcher;
import com.imcore.yunmingtea.util.JsonUtil;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class SplashActivity extends Activity {
	private ImageView mImg;
	static final int delayMillis = 2000;
	private final static String SP_NAME = "userInfo";
	private List<TopImages> list;
	private int count = 0;
	private Object object = new Object();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
		new RequestImageUrl().execute();
	}


	private class DownLoadImages extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... params) {
			ImageFetcher.downLoadImage(params[0]);
			return params[0];
		}

		@Override
		protected void onPostExecute(String result) {
			synchronized (object) {
				count++;
				MyApplication.imgicUrl.add(result);
			}

			if (count == list.size()) {
				SharedPreferences sp = getSharedPreferences(SP_NAME,
						MODE_PRIVATE);
				String userId = sp.getString("userId", "");
				String token = sp.getString("token", "");
				if (!"".equals(userId) && !"".equals(token)) {
					Intent intent = new Intent(SplashActivity.this,
							MainActivity.class);
					startActivity(intent);
					SplashActivity.this.finish();
				} else {
					Intent intent = new Intent(SplashActivity.this,
							LoginActivity.class);
					startActivity(intent);
					SplashActivity.this.finish();
					
				}
			}
			super.onPostExecute(result);
		}

	}

	private class RequestImageUrl extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPostExecute(Void result) {

			for (int i = 0; i < list.size(); i++) {
				new DownLoadImages().execute("http://yunming-api.suryani.cn"+"/"+list.get(i).getImageUrl());
			}

			super.onPostExecute(result);
		}

		@Override
		protected Void doInBackground(Void... params) {
			String url = "/topline/list.do";
			RequestEntity entity = new RequestEntity(url, HttpMethod.GET, null);
			String json = "";
			try {
				json = HttpHelper.execute(entity);
				ResponseJsonEntity responseJsonEntity = ResponseJsonEntity
						.fromJSON(json);
				if (responseJsonEntity.getStatus() == 200) {
					list = JsonUtil.toObjectList(responseJsonEntity.getData(),
							TopImages.class);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

	}
}
