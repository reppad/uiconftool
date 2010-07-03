package tommy.lcr.uiconftool;

import tommy.lcr.uiconftool.controller.EventManager;
import tommy.lcr.uiconftool.model.Parameters;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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

	/**
	 * Launch at activity starting
	 */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		mEventManager = new EventManager(this);
		mEventManager.readParam();
		launchMain();
	}
	
	/**
	 * Launch main interface
	 */
	public void launchMain() {
		setContentView(R.layout.main);

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
		
		setSpinnerPosition();
		refresh();
	}
	
	/**
	 * Launch personalization interface
	 */
	public void launchPerso() {
		setContentView(R.layout.perso);
		
		CheckBox checkBoxNOTIFICATION_TYPE_STREAM = (CheckBox) findViewById(R.id.CheckBoxNOTIFICATION_TYPE_STREAM);
		checkBoxNOTIFICATION_TYPE_STREAM.setOnCheckedChangeListener(mEventManager.getCheckedChangeListener());

		Button ButtonPersoValid = (Button) findViewById(R.id.ButtonPersoValid);
		ButtonPersoValid.setOnClickListener(mEventManager.getButtonPersoValidListener());

		Button ButtonPersoCancel = (Button) findViewById(R.id.ButtonPersoCancel);
		ButtonPersoCancel.setOnClickListener(mEventManager.getButtonPersoCancelListener());
		
		setPersoValues();
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
	 * Set value personalization checkbox state
	 */
	public void setPersoValues() {
		boolean[] values = mEventManager.getPersoValues();

		CheckBox STATUS_BAR_AT_THE_BOTTOM = (CheckBox) findViewById(R.id.CheckBoxSTATUS_BAR_AT_THE_BOTTOM);
		CheckBox NOTIFICATION_TYPE_STREAM = (CheckBox) findViewById(R.id.CheckBoxNOTIFICATION_TYPE_STREAM);
		CheckBox STREAM_NOTIFICATION_ON_TOP = (CheckBox) findViewById(R.id.CheckBoxSTREAM_NOTIFICATION_ON_TOP);
		CheckBox DIALER_TYPE_STREAM = (CheckBox) findViewById(R.id.CheckBoxDIALER_TYPE_STREAM);
		CheckBox DIALER_TYPE_AOSP = (CheckBox) findViewById(R.id.CheckBoxDIALER_TYPE_AOSP);
		CheckBox LOCK_TYPE_STREAM = (CheckBox) findViewById(R.id.CheckBoxLOCK_TYPE_STREAM);
		CheckBox LAUNCHER_TYPE_STREAM = (CheckBox) findViewById(R.id.CheckBoxLAUNCHER_TYPE_STREAM);

		STATUS_BAR_AT_THE_BOTTOM.setChecked(values[0]);
		NOTIFICATION_TYPE_STREAM.setChecked(values[1]);
		STREAM_NOTIFICATION_ON_TOP.setChecked(values[2]);
		setCheckBoxSTREAM_NOTIFICATION_ON_TOPState(values[1]);
		DIALER_TYPE_STREAM.setChecked(values[3]);
		DIALER_TYPE_AOSP.setChecked(values[4]);
		LOCK_TYPE_STREAM.setChecked(values[5]);
		LAUNCHER_TYPE_STREAM.setChecked(values[6]);
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
	
	/**
	 * Set spinner selected item
	 * @param id selected value
	 */
	public void setSpinnerSelectedItem(int id) {
		Spinner s = (Spinner) findViewById(R.id.SpinnerInterfaces);
		s.setSelection(id);
	}

	/**
	 * Set spinner position
	 */
	public void setSpinnerPosition() {
		switch (mEventManager.getUIType()) {
		case ANDROID:
			setSpinnerSelectedItem(0);
			break;
		case ACER:
			setSpinnerSelectedItem(1);
			break;
		case PERSO:
			setSpinnerSelectedItem(2);
			break;
		default:
			//impossible case
			break;
		}
	}
	
	/**
	 * Set checkBox "Stream notification on top" state
	 * @param state checked
	 */
	public void setCheckBoxSTREAM_NOTIFICATION_ON_TOPState(boolean state) {
		CheckBox checkBoxSTREAM_NOTIFICATION_ON_TOP = (CheckBox) findViewById(R.id.CheckBoxSTREAM_NOTIFICATION_ON_TOP);
		checkBoxSTREAM_NOTIFICATION_ON_TOP.setEnabled(state);
	}
    
	/**
	 * Get event Manager
	 * @return mEventManager
	 */
    public EventManager getEventManager() {
    	return mEventManager;
    }
    
    /**
     * Show confirm message on exit
     */
    public void showQuitMsg() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(R.string.alert_confirmMsg)
				.setCancelable(false)
				.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						mEventManager.applyConfiguration();
						finish();
					}
				})
				.setNeutralButton(R.string.no, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						finish();
					}
				})
				.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});
		AlertDialog alert = builder.create();
		alert.show();
    }
    
	//---------------Menu---------------//
	
	/**
	 *  Creates the menu items
	 */
	public boolean onCreateOptionsMenu(Menu menu) {
	    menu.add(0, 0, 0, R.string.reinitFile);
	    menu.add(0, 1, 1, R.string.about);
	    return true;
	}

	/**
	 *  Handles item selections
	 */
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    case 0:
	    	launchMain();
	    	mEventManager.resetConfiguration();
	        return true;
	    case 1:
	    	popUp("2010  V1.0.1\ngrandgto@gmail.com", 1);
	        return true;
	    }
	    return false;
	}
}