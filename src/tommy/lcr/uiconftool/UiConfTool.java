package tommy.lcr.uiconftool;

import tommy.lcr.uiconftool.controller.EventManager;
import tommy.lcr.uiconftool.controller.EventManager.Screen;
import tommy.lcr.uiconftool.model.Parameters;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
	private boolean mainUI;
	
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
		ButtonActivateCT.setOnClickListener(mEventManager.buttonActivateCT);

		Spinner s = (Spinner) findViewById(R.id.SpinnerInterfaces);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
	            this, R.array.interfaces, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		s.setAdapter(adapter);
		s.setOnItemSelectedListener(mEventManager.spinnerInterfaceListener);
		s.setOnLongClickListener(mEventManager.onLongClickFullUi);

		Button ButtonPerso = (Button) findViewById(R.id.ButtonPerso);
		ButtonPerso.setOnClickListener(mEventManager.buttonPersoListener);

		Button ButtonValid = (Button) findViewById(R.id.ButtonValid);
		ButtonValid.setOnClickListener(mEventManager.buttonValidListener);
		
		setInterfaceSpinnerPosition();
		refresh();
		mainUI = true;
	}
	
	/**
	 * Launch personalization interface
	 */
	public void launchPerso() {
		setContentView(R.layout.perso);
		
		CheckBox checkBoxNOTIFICATION_TYPE_STREAM = (CheckBox) findViewById(R.id.CheckBoxNOTIFICATION_TYPE_STREAM);
		checkBoxNOTIFICATION_TYPE_STREAM.setOnCheckedChangeListener(mEventManager.checkedChangeStreamNotifications);

		Button ButtonPersoValid = (Button) findViewById(R.id.ButtonPersoValid);
		ButtonPersoValid.setOnClickListener(mEventManager.buttonPersoValidListener);

		Button ButtonPersoCancel = (Button) findViewById(R.id.ButtonPersoCancel);
		ButtonPersoCancel.setOnClickListener(mEventManager.buttonPersoCancelListener);

		Spinner s = (Spinner) findViewById(R.id.SpinnerDialerType);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
	            this, R.array.dialers, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		s.setAdapter(adapter);
		s.setOnItemSelectedListener(mEventManager.spinnerDialerListener);
		

		CheckBox STATUS_BAR_AT_THE_BOTTOM = (CheckBox) findViewById(R.id.CheckBoxSTATUS_BAR_AT_THE_BOTTOM);
		CheckBox NOTIFICATION_TYPE_STREAM = (CheckBox) findViewById(R.id.CheckBoxNOTIFICATION_TYPE_STREAM);
		CheckBox STREAM_NOTIFICATION_ON_TOP = (CheckBox) findViewById(R.id.CheckBoxSTREAM_NOTIFICATION_ON_TOP);
		CheckBox LOCK_TYPE_STREAM = (CheckBox) findViewById(R.id.CheckBoxLOCK_TYPE_STREAM);
		CheckBox LAUNCHER_TYPE_STREAM = (CheckBox) findViewById(R.id.CheckBoxLAUNCHER_TYPE_STREAM);
		
		STATUS_BAR_AT_THE_BOTTOM.setOnCheckedChangeListener(mEventManager.checkedChangeStreamElements);
		NOTIFICATION_TYPE_STREAM.setOnCheckedChangeListener(mEventManager.checkedChangeStreamNotifications);
		STREAM_NOTIFICATION_ON_TOP.setOnCheckedChangeListener(mEventManager.checkedChangeStreamElements);
		LOCK_TYPE_STREAM.setOnCheckedChangeListener(mEventManager.checkedChangeLockTypeStream);
		LAUNCHER_TYPE_STREAM.setOnCheckedChangeListener(mEventManager.checkedChangeLauncherTypeStream);
		
		STATUS_BAR_AT_THE_BOTTOM.setOnLongClickListener(mEventManager.onLongClickStatusBarBottom);
		NOTIFICATION_TYPE_STREAM.setOnLongClickListener(mEventManager.onLongClickNotificationStream);
		STREAM_NOTIFICATION_ON_TOP.setOnLongClickListener(mEventManager.onLongClickStreamNotificationOnTop);
		LOCK_TYPE_STREAM.setOnLongClickListener(mEventManager.onLongClickLockTypeStream);
		LAUNCHER_TYPE_STREAM.setOnLongClickListener(mEventManager.onLongClickLauncherTypeStream);
		s.setOnLongClickListener(mEventManager.onLongClickDialer);
		
		setPersoValues();
		setPersoHelp(R.string.help_streamElements);
		mainUI = false;
	}

    /**
     * Launches the Update activity to add a new contact to the selected accont.
     */
    protected void launchUpdateView() {
        Intent i = new Intent(this, UpdateView.class);
        startActivity(i);
    }

    /**
     * Launches screenshot view
     */
    public void LaunchScreenshot(Screen screen) {
    	setContentView(R.layout.screenshotview);
    	Intent i = new Intent(this, Screenshot.class);
    	i.setFlags(screen.getValue());
        startActivity(i);
    }

	protected void onResume() {
		super.onResume();
		if(mainUI)
			launchMain();
		else
			launchPerso();
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
		CheckBox LOCK_TYPE_STREAM = (CheckBox) findViewById(R.id.CheckBoxLOCK_TYPE_STREAM);
		CheckBox LAUNCHER_TYPE_STREAM = (CheckBox) findViewById(R.id.CheckBoxLAUNCHER_TYPE_STREAM);
		Spinner s = (Spinner) findViewById(R.id.SpinnerDialerType);

		STATUS_BAR_AT_THE_BOTTOM.setChecked(values[0]);
		NOTIFICATION_TYPE_STREAM.setChecked(values[1]);
		STREAM_NOTIFICATION_ON_TOP.setChecked(values[2]);
		setCheckBoxSTREAM_NOTIFICATION_ON_TOPState(values[1]);
		LOCK_TYPE_STREAM.setChecked(values[5]);
		LAUNCHER_TYPE_STREAM.setChecked(values[6]);
		
		if(values[3] && values[4]) {
			//dialer AOSP
			s.setSelection(2);
		} else if(values[3] && !values[4]) {
			//dialer Stream
			s.setSelection(1);
		} else if(!values[3] && values[4]) {
			//dialer AOSP
			s.setSelection(2);
		} else if(!values[3] && !values[4]) {
			//dialer Android
			s.setSelection(0);
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

	/**
	 * Set interface spinner position
	 */
	public void setInterfaceSpinnerPosition() {
		Spinner s = (Spinner) findViewById(R.id.SpinnerInterfaces);
		switch (mEventManager.getUIType()) {
		case ANDROID:
			s.setSelection(0);
			break;
		case ACER:
			s.setSelection(1);
			break;
		case PERSO:
			s.setSelection(2);
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
	 * Set the help message
	 * @param resId ID du message à afficher
	 */
	public void setPersoHelp(int resId) {
		TextView helpMsg = (TextView) findViewById(R.id.TextViewPersoHelp);
		helpMsg.setText(resId);
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
	    menu.add(0, 2, 2, R.string.updatePage);
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
	    	popUp("Reppad 2010  V1.2.2\ngrandgto@gmail.com", 1);
	        return true;
	    case 2:
	    	launchUpdateView();
	        return true;
	    }
	    return false;
	}
}