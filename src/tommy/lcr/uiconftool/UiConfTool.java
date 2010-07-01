package tommy.lcr.uiconftool;

import tommy.lcr.uiconftool.controller.EventManager;
import tommy.lcr.uiconftool.model.Parameters;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * UI Conf Tool for LCR 1.7
 * @author grandgto@gmail.com
 */
public class UiConfTool extends Activity {

	private EventManager mEventManager;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
		
		mEventManager = new EventManager(this);

		Button ButtonActivateCT = (Button) findViewById(R.id.ButtonActivateCT);
		ButtonActivateCT.setOnClickListener(mEventManager.getButtonActivateCT());

		Spinner s = (Spinner) findViewById(R.id.SpinnerInterfaces);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
	            this, R.array.interfaces, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		s.setAdapter(adapter);
		s.setOnItemSelectedListener(mEventManager.getSpinnerListener());

		Button ButtonPerso = (Button) findViewById(R.id.ButtonPerso);
		ButtonPerso.setOnClickListener(mEventManager.getButtonPersoListener());

		Button ButtonValid = (Button) findViewById(R.id.ButtonValid);
		ButtonValid.setOnClickListener(mEventManager.getButtonValidListener());

//		Button ButtonPersoValid = (Button) findViewById(R.id.ButtonPersoValid);
//		ButtonPersoValid.setOnClickListener(mEventManager.getButtonPersoValidListener());
//
//		Button ButtonPersoCancel = (Button) findViewById(R.id.ButtonPersoCancel);
//		ButtonPersoCancel.setOnClickListener(mEventManager.getButtonPersoCancelListener());
		
		mEventManager.readParam();
		mEventManager.setSpinnerPosition();
		refresh();
	}
	
	/**
	 * Hide useless interface elements
	 */
	public void refresh() {
		Parameters.State app = mEventManager.getUIState();
		Button buttonActivateCT = (Button) findViewById(R.id.ButtonActivateCT);
		TextView type = (TextView) findViewById(R.id.TextViewInterfaceType);
		Spinner s = (Spinner) findViewById(R.id.SpinnerInterfaces);
		Button buttonPerso = (Button) findViewById(R.id.ButtonPerso);
		switch (app) {
		case OFF:
			buttonActivateCT.setText(R.string.main_activateCT);
			type.setVisibility(View.INVISIBLE);
			s.setVisibility(View.INVISIBLE);
			buttonPerso.setVisibility(View.INVISIBLE);
			break;
		case DEFAULT:
			buttonActivateCT.setText(R.string.main_desactivateCT);
			type.setVisibility(View.VISIBLE);
			s.setVisibility(View.VISIBLE);
			buttonPerso.setVisibility(View.INVISIBLE);
			break;
		case PERSO:
			buttonActivateCT.setText(R.string.main_desactivateCT);
			type.setVisibility(View.VISIBLE);
			s.setVisibility(View.VISIBLE);
			buttonPerso.setVisibility(View.VISIBLE);
			break;
		default:
			// impossible case
			break;
		}
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
	
	public void setSpinnerSelectedItem(int id) {
		Spinner s = (Spinner) findViewById(R.id.SpinnerInterfaces);
		s.setSelection(id);
	}
	
	public void setPersoValues() {
		boolean[] values = mEventManager.getPersoValues();

		CheckBox STATUS_BAR_AT_THE_BOTTOM = (CheckBox) findViewById(R.id.CheckBoxSTATUS_BAR_AT_THE_BOTTOM);
		CheckBox NOTIFICATION_TYPE_STREAM = (CheckBox) findViewById(R.id.CheckBoxNOTIFICATION_TYPE_STREAM);
		CheckBox STREAM_NOTIFICATION_ON_TOP = (CheckBox) findViewById(R.id.CheckBoxSTREAM_NOTIFICATION_ON_TOP);
		CheckBox DIALER_TYPE_STREAM = (CheckBox) findViewById(R.id.CheckBoxDIALER_TYPE_STREAM);
		CheckBox LOCK_TYPE_STREAM = (CheckBox) findViewById(R.id.CheckBoxLOCK_TYPE_STREAM);
		CheckBox LAUNCHER_TYPE_STREAM = (CheckBox) findViewById(R.id.CheckBoxLAUNCHER_TYPE_STREAM);

		STATUS_BAR_AT_THE_BOTTOM.setChecked(values[0]);
		NOTIFICATION_TYPE_STREAM.setChecked(values[1]);
		STREAM_NOTIFICATION_ON_TOP.setChecked(values[2]);
		DIALER_TYPE_STREAM.setChecked(values[3]);
		LOCK_TYPE_STREAM.setChecked(values[4]);
		LAUNCHER_TYPE_STREAM.setChecked(values[5]);
	}

	//---------------Menu---------------//
	
	/* Creates the menu items */
	public boolean onCreateOptionsMenu(Menu menu) {
	    menu.add(0, 0, 0, R.string.reinitFile);
	    return true;
	}

	/* Handles item selections */
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    case 0:
	    	mEventManager.resetConfiguration();
	        return true;
	    }
	    return false;
	}
}