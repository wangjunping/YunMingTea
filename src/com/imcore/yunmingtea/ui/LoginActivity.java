package com.imcore.yunmingtea.ui;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.CursorJoiner.Result;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.imcore.yunmingtea.R;
import com.imcore.yunmingtea.http.HttpHelper;
import com.imcore.yunmingtea.http.RequestEntity;
import com.imcore.yunmingtea.http.ResponseJsonEntity;
import com.imcore.yunmingtea.util.ConnectivityUtil;
import com.imcore.yunmingtea.util.JsonUtil;
import com.imcore.yunmingtea.util.TextUtil;
import com.imcore.yunmingtea.util.ToastUtil;

public class LoginActivity extends Activity {
	private EditText tvUserName;
	private EditText tvPassWord;
	private Button btnLogin;
	private Button btnRegister;
	private final static String SP_NAME = "userInfo";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		tvUserName = (EditText) findViewById(R.id.et_username);
		tvPassWord = (EditText) findViewById(R.id.et_password);
		btnLogin = (Button) findViewById(R.id.btn_login);
		btnRegister = (Button) findViewById(R.id.btn_register);
		btnLogin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				doLogin();

			}
		});

	}

	private void doLogin() {
		if (!ConnectivityUtil.isOnline(this)) {
			return;
		}
		
		String userName = tvUserName.getText().toString();
		String passWord = tvPassWord.getText().toString();
		if( userName==null|| userName.equals("")){
			Toast.makeText(this, "请输入账号密码", Toast.LENGTH_SHORT).show();
		}
		if( passWord == null || passWord.equals("")){
			Toast.makeText(this, "密码不能为空！", Toast.LENGTH_SHORT).show();
		}
		else {
		new LoginTask(userName, passWord).execute();
		}
	}

	private class LoginTask extends AsyncTask<Void, Void, String> {

		private String mUserName;
		private String mPassword;

		LoginTask(String userName, String password) {
			mUserName = userName;
			mPassword = password;
		}

		@Override
		protected void onPreExecute() {
			// 进度条。。。
			super.onPreExecute();
		}

		@Override
		protected String doInBackground(Void... params) {
			String url = "/passport/login.do";
			Map<String, Object> args = new HashMap<String, Object>();
			args.put("phoneNumber", mUserName);
			args.put("password", mPassword);
			args.put("client", "android");

			RequestEntity mEntity = new RequestEntity(url, args);
			String jsonResponse = null;

			try {
				jsonResponse = HttpHelper.execute(mEntity);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return jsonResponse;

		}

		@Override
		protected void onPostExecute(String result) {
			if (TextUtil.isEmptyString(result)) {
				//
				return;
			}
			ResponseJsonEntity mEntity = ResponseJsonEntity.fromJSON(result);
			if (mEntity.getStatus() == 200) {
				String jsonDate = mEntity.getData();
				Log.i("user", jsonDate);
				String userId = JsonUtil.getJsonValueByKey(jsonDate, "id");
				String token = JsonUtil.getJsonValueByKey(jsonDate, "token");

				SharedPreferences sp = getSharedPreferences(SP_NAME,
						MODE_PRIVATE);

				boolean flag = sp.edit().putString("userId", userId)
						.putString("token", token).commit();
				Intent intent = new Intent(LoginActivity.this,
						MainActivity.class);
				startActivity(intent);
				finish();
				super.onPostExecute(result);
			} else {
				ToastUtil.showToast(LoginActivity.this, mEntity.getMessage());
			}

		}
	}
}
