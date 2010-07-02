package tommy.lcr.uiconftool;

import tommy.lcr.uiconftool.controller.EventManager;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

/**
 * UI Conf Tool for LCR 1.7
 * @author grandgto@gmail.com
 */
public class Perso extends Activity {

	private EventManager mEventManager;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.perso);
		
//		UiConfTool parent = (UiConfTool) getParent();
//		mEventManager = parent.getEventManager();

//		Button ButtonPersoValid = (Button) findViewById(R.id.ButtonPersoValid);
//		ButtonPersoValid.setOnClickListener(mEventManager.getButtonPersoValidListener());
//
//		Button ButtonPersoCancel = (Button) findViewById(R.id.ButtonPersoCancel);
//		ButtonPersoCancel.setOnClickListener(mEventManager.getButtonPersoCancelListener());
	}
	
	/**
	 * Show a popup message
	 * @param msg Displayed text
	 * @param duration 1 for long, 0 for short
	 */
	public void popUp(String msg, int duration) {
		Toast toast = Toast.makeText(getApplicationContext(), msg, duration);
		toast.show();
	}
	
//	public void setPersoValues() {
//		boolean[] values = mEventManager.getPersoValues();
//
//		CheckBox STATUS_BAR_AT_THE_BOTTOM = (CheckBox) findViewById(R.id.CheckBoxSTATUS_BAR_AT_THE_BOTTOM);
//		CheckBox NOTIFICATION_TYPE_STREAM = (CheckBox) findViewById(R.id.CheckBoxNOTIFICATION_TYPE_STREAM);
//		CheckBox STREAM_NOTIFICATION_ON_TOP = (CheckBox) findViewById(R.id.CheckBoxSTREAM_NOTIFICATION_ON_TOP);
//		CheckBox DIALER_TYPE_STREAM = (CheckBox) findViewById(R.id.CheckBoxDIALER_TYPE_STREAM);
//		CheckBox DIALER_TYPE_AOSP = (CheckBox) findViewById(R.id.CheckBoxDIALER_TYPE_AOSP);
//		CheckBox LOCK_TYPE_STREAM = (CheckBox) findViewById(R.id.CheckBoxLOCK_TYPE_STREAM);
//		CheckBox LAUNCHER_TYPE_STREAM = (CheckBox) findViewById(R.id.CheckBoxLAUNCHER_TYPE_STREAM);
//
//		STATUS_BAR_AT_THE_BOTTOM.setChecked(values[0]);
//		NOTIFICATION_TYPE_STREAM.setChecked(values[1]);
//		STREAM_NOTIFICATION_ON_TOP.setChecked(values[2]);
//		DIALER_TYPE_STREAM.setChecked(values[3]);
//		DIALER_TYPE_AOSP.setChecked(values[4]);
//		LOCK_TYPE_STREAM.setChecked(values[5]);
//		LAUNCHER_TYPE_STREAM.setChecked(values[6]);
//	}

	//---------------Menu---------------//
	
//	/* Creates the menu items */
//	public boolean onCreateOptionsMenu(Menu menu) {
//	    menu.add(0, 0, 0, R.string.reinitFile);
//	    return true;
//	}
//
//	/* Handles item selections */
//	public boolean onOptionsItemSelected(MenuItem item) {
//	    switch (item.getItemId()) {
//	    case 0:
//	    	mEventManager.resetConfiguration();
//	        return true;
//	    }
//	    return false;
//	}
}