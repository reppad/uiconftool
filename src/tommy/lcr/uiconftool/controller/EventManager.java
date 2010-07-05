package tommy.lcr.uiconftool.controller;

import tommy.lcr.uiconftool.R;
import tommy.lcr.uiconftool.UiConfTool;
import tommy.lcr.uiconftool.model.Parameters;
import tommy.lcr.uiconftool.model.Parameters.UIType;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.AdapterView.OnItemSelectedListener;

/**
 * Event manager
 * @author grandgto@gmail.com
 */
public class EventManager {

	private UiConfTool				mActivity;
	private Parameters				mParam;
	private OnClickListener 		buttonActivateCT,
									buttonPersoListener,
									buttonValidListener,
									buttonPersoValidListener,
									buttonPersoCancelListener;
	private OnItemSelectedListener	spinnerInterfaceListener,
									spinnerDialerListener;
	private OnCheckedChangeListener	checkedChangeStreamNotifications,
									checkedChangeStreamElements,
									checkedChangeLockTypeStream,
									checkedChangeLauncherTypeStream;

	/**
	 * Default constructor
	 * @param p_activity
	 */
	public EventManager(UiConfTool p_activity) {
		mActivity = p_activity;
		mParam = new Parameters();

		buttonActivateCT = new OnClickListener() {
			public void onClick(View v) {
				mParam.setTHIS_CONF_MUST_BE_APPLIED(!mParam.isTHIS_CONF_MUST_BE_APPLIED());
				mActivity.refresh();
			}
		};

		buttonPersoListener = new OnClickListener() {
			public void onClick(View v) {
				mActivity.launchPerso();
			}
		};

		buttonValidListener = new OnClickListener() {
			public void onClick(View v) {
				mActivity.showQuitMsg();
			}
		};

		buttonPersoValidListener = new OnClickListener() {
			public void onClick(View v) {
				CheckBox STATUS_BAR_AT_THE_BOTTOM = (CheckBox) mActivity.findViewById(R.id.CheckBoxSTATUS_BAR_AT_THE_BOTTOM);
				CheckBox NOTIFICATION_TYPE_STREAM = (CheckBox) mActivity.findViewById(R.id.CheckBoxNOTIFICATION_TYPE_STREAM);
				CheckBox STREAM_NOTIFICATION_ON_TOP = (CheckBox) mActivity.findViewById(R.id.CheckBoxSTREAM_NOTIFICATION_ON_TOP);
				CheckBox LOCK_TYPE_STREAM = (CheckBox) mActivity.findViewById(R.id.CheckBoxLOCK_TYPE_STREAM);
				CheckBox LAUNCHER_TYPE_STREAM = (CheckBox) mActivity.findViewById(R.id.CheckBoxLAUNCHER_TYPE_STREAM);
				
				mParam.setSTATUS_BAR_AT_THE_BOTTOM(STATUS_BAR_AT_THE_BOTTOM.isChecked());
				mParam.setNOTIFICATION_TYPE_STREAM(NOTIFICATION_TYPE_STREAM.isChecked());
				mParam.setSTREAM_NOTIFICATION_ON_TOP(STREAM_NOTIFICATION_ON_TOP.isChecked());
				mParam.setLOCK_TYPE_STREAM(LOCK_TYPE_STREAM.isChecked());
				mParam.setLAUNCHER_TYPE_STREAM(LAUNCHER_TYPE_STREAM.isChecked());
				
				mActivity.launchMain();
			}
		};

		buttonPersoCancelListener = new OnClickListener() {
			public void onClick(View v) {
				mActivity.launchMain();
			}
		};
		
		spinnerInterfaceListener = new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> spinner, View arg1, int position, long rowId) {
				switch (position) {
					case 0:
						mParam.setFULL_UI_ANDROID(true);
						mParam.setFULL_UI_ACER(false);
						break;
					case 1:
						mParam.setFULL_UI_ANDROID(false);
						mParam.setFULL_UI_ACER(true);
						break;
					case 2:
						mParam.setFULL_UI_ANDROID(false);
						mParam.setFULL_UI_ACER(false);
						break;
					default:
						break;
				}
				mActivity.refresh();
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				//nothing to do
			}
		};
		
		spinnerDialerListener = new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> spinner, View arg1, int position, long rowId) {
				switch (position) {
					case 0:
						mParam.setDIALER_TYPE_STREAM(false);
						mParam.setDIALER_TYPE_AOSP(false);
						mActivity.setPersoHelp(R.string.help_streamElements);
						break;
					case 1:
						mParam.setDIALER_TYPE_STREAM(true);
						mParam.setDIALER_TYPE_AOSP(false);
						mActivity.setPersoHelp(R.string.help_streamElements);
						break;
					case 2:
						mParam.setDIALER_TYPE_STREAM(false);
						mParam.setDIALER_TYPE_AOSP(true);
						mActivity.setPersoHelp(R.string.help_dialerAOSP);
						break;
					default:
						break;
				}
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				//nothing to do
			}
		};
		
		
		checkedChangeStreamNotifications = new OnCheckedChangeListener() {
			
			public void onCheckedChanged(CompoundButton checkBox, boolean checked) {
				mActivity.setCheckBoxSTREAM_NOTIFICATION_ON_TOPState(checked);
				mActivity.setPersoHelp(R.string.help_streamNotifications);
			}
		};
		checkedChangeStreamElements = new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				mActivity.setPersoHelp(R.string.help_streamElements);
			}
		};
		checkedChangeLockTypeStream = new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				mActivity.setPersoHelp(R.string.help_lockTypeStream);
			}
		};
		checkedChangeLauncherTypeStream = new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				mActivity.setPersoHelp(R.string.help_launcherTypeStream);
			}
		};
	} //constructor

	/**
	 * Read conf file
	 * @return Message
	 */
	public String readParam() {
		return mParam.readConfFile();
	}
	
	public void resetConfiguration() {
		mParam.resetConfiguration();
		mActivity.setInterfaceSpinnerPosition();
		mActivity.refresh();
	}
	
	public void applyConfiguration() {
		mParam.applyConfiguration();
	}

	//--------------Getters----------------//
	
	public Parameters.State getUIState() {
		return mParam.getUIState();
	}
	
	public UIType getUIType() {
		return mParam.getUIType();
	}

	public OnClickListener getButtonActivateCT() {
		return buttonActivateCT;
	}

	public OnClickListener getButtonPersoListener() {
		return buttonPersoListener;
	}

	public OnClickListener getButtonValidListener() {
		return buttonValidListener;
	}

	public OnClickListener getButtonPersoValidListener() {
		return buttonPersoValidListener;
	}

	public OnClickListener getButtonPersoCancelListener() {
		return buttonPersoCancelListener;
	}

	public OnItemSelectedListener getSpinnerInterfaceListener() {
		return spinnerInterfaceListener;
	}

	public OnItemSelectedListener getSpinnerDialerListener() {
		return spinnerDialerListener;
	}

	public OnCheckedChangeListener getCheckedChangeStreamNotifications() {
		return checkedChangeStreamNotifications;
	}
	
	public boolean[] getPersoValues() {
		return mParam.getPersoValues();
	}

	public void setCheckedChangeStreamNotifications( OnCheckedChangeListener checkedChangeStreamNotifications) {
		this.checkedChangeStreamNotifications = checkedChangeStreamNotifications;
	}

	public OnCheckedChangeListener getCheckedChangeStreamElements() {
		return checkedChangeStreamElements;
	}

	public void setCheckedChangeStreamElements(
			OnCheckedChangeListener checkedChangeStreamElements) {
		this.checkedChangeStreamElements = checkedChangeStreamElements;
	}

	public OnCheckedChangeListener getCheckedChangeLockTypeStream() {
		return checkedChangeLockTypeStream;
	}

	public void setCheckedChangeLockTypeStream(
			OnCheckedChangeListener checkedChangeLockTypeStream) {
		this.checkedChangeLockTypeStream = checkedChangeLockTypeStream;
	}

	public OnCheckedChangeListener getCheckedChangeLauncherTypeStream() {
		return checkedChangeLauncherTypeStream;
	}

	public void setCheckedChangeLauncherTypeStream(
			OnCheckedChangeListener checkedChangeLauncherTypeStream) {
		this.checkedChangeLauncherTypeStream = checkedChangeLauncherTypeStream;
	}
	
	

} //class
