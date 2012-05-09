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
	private String currentLink;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		getWindow().requestFeature(Window.FEATURE_PROGRESS);
		getWindow().setFeatureInt( Window.FEATURE_PROGRESS, Window.PROGRESS_VISIBILITY_ON);
		
		setContentView(R.layout.activity_web);
		
		if (savedInstanceState == null) {
			makeLinkFromIntent();
		} else {
			String savedLink = savedInstanceState.getString("link");
			if (savedLink != null) {
				Log.d(TAG, "Restaurando ultimo link: " + savedLink);
				currentLink = savedLink;
			} else {
				makeLinkFromIntent();
			}
		}
		
		initBrowser();
	}

	private void makeLinkFromIntent() {
		Intent intent = getIntent();
		currentLink = "http://dontpad.com/"+intent.getStringExtra("link");
	}

	private void initBrowser() {
		browser = (WebView) findViewById(R.id.browser);
		browser.getSettings().setJavaScriptEnabled(true);
		browser.getSettings().setBuiltInZoomControls(true);
		browser.getSettings().setSupportZoom(true);
		
		browser.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				if (Uri.parse(url).getHost().contains("dontpad.com")) {
					Log.d(TAG, "Salvando link: " + url.toString());
					currentLink = url.toString();
					return false;
				}
				
				Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
				startActivity(intent);
				return true;
			}
			
			public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
				Toast.makeText(DontpadActivity.this, "Oh no! " + description, Toast.LENGTH_SHORT).show();
			}
		});
		
		browser.setWebChromeClient(new WebChromeClient() {
			public void onProgressChanged(WebView view, int progress) {
				// Activities and WebViews measure progress with different scales.
				// The progress meter will automatically disappear when we reach 100%
				DontpadActivity.this.setTitle("Loading...");
				DontpadActivity.this.setProgress(progress * 100);
 
                if(progress == 100)
                	DontpadActivity.this.setTitle(R.string.app_name);
			}
		});
		
		Log.d(TAG, "Opening dontpad link:" + currentLink);
		browser.loadUrl(currentLink);
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		
		outState.putString("link", currentLink);
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
