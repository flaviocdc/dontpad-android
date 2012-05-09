package br.ufrj;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

public class DontpadActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.info);
		
		Intent intent = getIntent();
		String link = intent.getStringExtra("link");
		
		WebView wv = (WebView) findViewById(R.id.browser);
		wv.getSettings().setJavaScriptEnabled(true);
		wv.loadUrl("http://www.dontpad.com/"+link);
		
		
		//Log.d("InfoActivity 1", nometurma);
		//Log.d("InfoActivity 2", salaturma);
		
	}
	
}
