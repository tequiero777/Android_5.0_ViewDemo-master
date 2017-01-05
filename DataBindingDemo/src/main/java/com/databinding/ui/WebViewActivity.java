/**
 * Copyright (c) 2013 Tianjian, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * Tianjian, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with Tianjian.
 */
package com.databinding.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;

import com.databinding.R;
import com.databinding.bean.MyWebViewClient;
import com.databinding.view.ProgressWebView;



public class WebViewActivity extends Activity implements Handler.Callback{
	private ProgressWebView webView;
	private String url;
	private boolean flag;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.basic_advert_fragment);
		Intent intent = getIntent();
		url = intent.getStringExtra("url");

		webView = (ProgressWebView) findViewById(R.id.webView);
		webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.getSettings().setDefaultTextEncodingName("UTF-8");
		//设置可以支持缩放
		webView.getSettings().setSupportZoom(true);
		webView.getSettings().setBuiltInZoomControls(true);
		//设置默认缩放方式尺寸是far
		webView.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);
		webView.setWebViewClient(new MyWebViewClient());
		//web storage参数设置
		webView.getSettings().setDomStorageEnabled(true);
		webView.getSettings().setAppCacheMaxSize(1024*1024*8);
		String appCachePath = this.getCacheDir().getAbsolutePath();
		webView.getSettings().setAppCachePath(appCachePath);
		webView.getSettings().setAllowFileAccess(true);
		webView.getSettings().setAppCacheEnabled(true);
		/*设置字体大小*/
		if(flag){
			webView.getSettings().setDefaultTextEncodingName("UTF-8") ;
			webView.loadDataWithBaseURL(null, url, "text/html", "utf-8", null);
		}else{
			webView.loadUrl(url);
		}
		this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE|
				WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
	}




	@Override
	public boolean handleMessage(Message msg) {
		// TODO Auto-generated method stub
		return false;
	}
	public ProgressWebView getWebView() {
		return webView;
	}
	

}
