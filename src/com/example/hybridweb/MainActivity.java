package com.example.hybridweb;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

@SuppressLint("JavascriptInterface")
public class MainActivity extends Activity {
		WebView myweb;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		myweb=(WebView) findViewById(R.id.myweb);
		
		WebSettings settings=myweb.getSettings();
		settings.setJavaScriptEnabled(true);
		
		myweb.addJavascriptInterface(new MyJavascriptInterface(),"android");
		myweb.setWebViewClient(new MyWebViewClient());
		myweb.setWebChromeClient(new WebChromeClient());
		myweb.loadUrl("http://192.168.10.11:8080/console/index.jsp");
/*		myweb.loadUrl("http://192.168.10.11");*/
	}

	class MyJavascriptInterface{
		@JavasciptInterface
		public void showToast(String msg){
			Log.i("###", msg);
			Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
		}
	}
	
	class MyWebViewClient extends WebViewClient{
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			// TODO Auto-generated method stub
			Log.i("###", "url="+url);
			view.loadUrl(url);
			return true;
		}
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
