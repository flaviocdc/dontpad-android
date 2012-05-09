package com.dontpad.android;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class DontpadActivity extends Activity {

	private static final String TAG = DontpadActivity.class.getSimpleName();
	
	private WebView browser;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		getWindow().requestFeature(Window.FEATURE_PROGRESS);
		getWindow().setFeatureInt( Window.FEATURE_PROGRESS, Window.PROGRESS_VISIBILITY_ON);
		
		setContentView(R.layout.activity_web);
		
		Intent intent = getIntent();
		String link = intent.getStringExtra("link");
		
		initBrowser(link);
	}

	private void initBrowser(String link) {
		browser = (WebView) findViewById(R.id.browser);
		browser.getSettings().setJavaScriptEnabled(true);
		browser.getSettings().setBuiltInZoomControls(true);
		browser.getSettings().setSupportZoom(true);
		
		browser.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				if (Uri.parse(url).getHost().contains("dontpad.com")) {
					return false;
				}
				
				Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
				startActivity(intent);
				return true;
			}
		});
		
		browser.setWebChromeClient(new WebChromeClient() {
			public void onProgressChanged(WebView view, int progress) {
				// Activities and WebViews measure progress with different scales.
				// The progress meter will automatically disappear when we reach 100%
				Log.d(TAG, "To aqui! " + progress);
				DontpadActivity.this.setTitle("Loading...");
				DontpadActivity.this.setProgress(progress * 100);
 
                if(progress == 100)
                	DontpadActivity.this.setTitle(R.string.app_name);
			}
		});
		browser.setWebViewClient(new WebViewClient() {
			public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
				Toast.makeText(DontpadActivity.this, "Oh no! " + description, Toast.LENGTH_SHORT).show();
			}
		});
		
		browser.loadUrl("http://www.dontpad.com/"+link);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    // Check if the key event was the Back button and if there's history
	    if ((keyCode == KeyEvent.KEYCODE_BACK) && browser.canGoBack()) {
	        browser.goBack();
	        return true;
	    }
	    // If it wasn't the Back key or there's no web page history, bubble up to the default
	    // system behavior (probably exit the activity)
	    return super.onKeyDown(keyCode, event);
	}
	
}
