package tommy.lcr.uiconftool;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

/**
 * UI Conf Tool for LCR 1.7
 * @author grandgto@gmail.com
 */
public class UpdateView extends Activity {
	WebView mWebView;

	/**
	 * Launch at activity starting
	 */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webview);

		mWebView = (WebView) findViewById(R.id.webview);
		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.loadUrl("http://code.google.com/p/uiconftool/downloads/list");
	}
}