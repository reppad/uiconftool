package tommy.lcr.uiconftool;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * UI Conf Tool for LCR 1.7
 * @author grandgto@gmail.com
 */
public class Screenshot extends Activity {

	/**
	 * Launch at activity starting
	 */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.screenshotview);
		ImageView img = (ImageView) findViewById(R.id.screenshotview);
		Intent i = getIntent();
		int flag = i.getFlags();
		
		switch (flag) {
		//STATUS_BAR_AT_THE_BOTTOM
		case 0:
			img.setImageResource(R.drawable.screen_statusbarbottom);
			break;
		//NOTIFICATION_TYPE_STREAM
		case 1:
			img.setImageResource(R.drawable.screen_streamnotifications);
			break;
		//STREAM_NOTIFICATION_ON_TOP
		case 2:
			img.setImageResource(R.drawable.stream_streamnotificationontop);
			break;
		//DIALER_TYPE_ANDROID
		case 3:
			img.setImageResource(R.drawable.screen_dialerandroid);
			break;
		//DIALER_TYPE_STREAM
		case 4:
			img.setImageResource(R.drawable.screen_dialerstream);
			break;
		//DIALER_TYPE_AOSP
		case 5:
			img.setImageResource(R.drawable.screen_dialeraosp);
			break;
		//LOCK_TYPE_STREAM
		case 6:
			img.setImageResource(R.drawable.screen_lockscreenacer);
			break;
		//LAUNCHER_TYPE_STREAM
		case 7:
			img.setImageResource(R.drawable.screen_fulluiacer);
			break;
		//FULL_UI_ANDROID
		case 8:
			img.setImageResource(R.drawable.screen_fullandroid);
			break;
		//FULL_UI_ACER
		case 9:
			img.setImageResource(R.drawable.screen_fulluiacer);
			break;
		default:
			break;
		}
	}
}