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
//		int flag = i.getFlags();
//		switch (flag) {
//		case 0:
//			img.setImageResource(R.drawable.);
//			break;
//		case 1:
//			img.setImageResource(R.drawable.);
//			break;
//		case 2:
//			img.setImageResource(R.drawable.);
//			break;
//		case 3:
//			img.setImageResource(R.drawable.);
//			break;
//		case 4:
//			img.setImageResource(R.drawable.);
//			break;
//		case 5:
//			img.setImageResource(R.drawable.);
//			break;
//		case 6:
//			img.setImageResource(R.drawable.);
//			break;
//		case 7:
//			img.setImageResource(R.drawable.);
//			break;
//		default:
//			break;
//		}
	}
}