/**
 * Copyright (c) 2013 Tianjian, Inc. All rights reserved. This software is the confidential and proprietary information of Tianjian, Inc.
 * You shall not disclose such Confidential Information and shall use it only in accordance with the terms of the license agreement you
 * entered into with Tianjian.
 */
package com.databinding.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

/**
 * 带进度条的webview
 * <p>
 * Title: ProgressWebView.java
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: Tianjian
 * </p>
 * <p>
 * team: TianjianTeam
 * </p>
 * 
 * @author: Zhangyj
 * @date 2014年9月26日上午11:15:04
 * @version 1.0
 */
public class ProgressWebView extends WebView {
	private ProgressBar progressbar;

	public ProgressWebView(Context context, AttributeSet attrs) {
		super(context, attrs);
		progressbar = new ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal);
		progressbar.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 10, 0, 0));
		addView(progressbar);
	}

	
	public class MyOwnWebChromeClient extends WebChromeClient {
		private Activity activity;
		private Handler handler;
		
		public MyOwnWebChromeClient(Activity activity,
				Handler handler) {
			this.activity = activity;
			this.handler = handler;
		}
		public MyOwnWebChromeClient(Activity activity) {
			this.activity = activity;
		}

		// 处理javascript中的alert
		@Override
		public boolean onJsAlert(WebView view, String url, String message,
								 final JsResult result) {
			// 构建一个Builder来显示网页中的对话框
			Builder builder = new Builder(activity);
			builder.setTitle("提示");
			builder.setMessage(message);
			builder.setPositiveButton(android.R.string.ok,
					new AlertDialog.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							result.confirm();
						}
					});
			builder.setCancelable(false);
			builder.create();
			builder.show();
			return true;
		}

		// 处理javascript中的confirm
		@Override
		public boolean onJsConfirm(WebView view, String url, String message,
								   final JsResult result) {
			Builder builder = new Builder(activity);
			builder.setTitle("提示");
			builder.setMessage(message);
			builder.setPositiveButton(android.R.string.ok,
					new AlertDialog.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							result.confirm();
						}
					});
			builder.setNegativeButton(android.R.string.cancel,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							result.cancel();
						}
					});
			builder.setCancelable(false);
			builder.create();
			builder.show();
			return true;
		}

		// 设置网页加载的进度条
		@Override
		public void onProgressChanged(WebView view, int newProgress) {
//			Message msg = handler.obtainMessage();
//			msg.arg1 = newProgress;
//			msg.what=newProgress;
//			handler.sendMessage(msg);
			progressbar.setProgress(newProgress);
			if(newProgress ==100 ){
				progressbar.setVisibility(View.GONE);
			}
			super.onProgressChanged(view, newProgress);
		}

		// 设置应用程序的标题title
		@Override
		public void onReceivedTitle(WebView view, String title) {
			activity.setTitle(title);
			super.onReceivedTitle(view, title);
		}

		//
		@Override
		public void onConsoleMessage(String message, int lineNumber, String sourceID) {
			Log.d("MyWebView", message + " -- From line " + lineNumber + " of " + sourceID);
		}

		@Override
		public View getVideoLoadingProgressView() {
			// TODO Auto-generated method stub
			return super.getVideoLoadingProgressView();
		}

		@Override
		public void onReceivedTouchIconUrl(WebView view, String url,
										   boolean precomposed) {
			view.loadUrl(url);
		}
		
	}
}
