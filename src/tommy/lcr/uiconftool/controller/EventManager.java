package tommy.lcr.uiconftool.controller;

import tommy.lcr.uiconftool.R;
import tommy.lcr.uiconftool.UiConfTool;
import tommy.lcr.uiconftool.model.Parameters;
import tommy.lcr.uiconftool.model.Parameters.UIType;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;

/**
 * UI Conf Tool for LCR 1.7
 * Event manager
 * @author grandgto@gmail.com
 */
public class EventManager {
	public enum Screen {
		STATUS_BAR_AT_THE_BOTTOM(0),
		NOTIFICATION_TYPE_STREAM(1),
		STREAM_NOTIFICATION_ON_TOP(2),
		DIALER_TYPE_ANDROID(3),
		DIALER_TYPE_STREAM(4),
		DIALER_TYPE_AOSP(5),
		LOCK_TYPE_STREAM(6),
		LAUNCHER_TYPE_STREAM(7),
		FULL_UI_ANDROID(8),
		FULL_UI_ACER(9);
		
		private final int value;
		
		private Screen(int value) {
			this.value = value;
		}
		
		public int getValue() {
			return this.value;
		}
	};

	public UiConfTool				mActivity;
	public Parameters				mParam;
	public OnClickListener 			buttonActivateCT,
									buttonPersoListener,
									buttonValidListener,
									buttonPersoValidListener,
									buttonPersoCancelListener;
	public OnItemSelectedListener	spinnerInterfaceListener,
									spinnerDialerListener;
	public OnCheckedChangeListener	checkedChangeStreamNotifications,
									checkedChangeStreamElements,
									checkedChangeLockTypeStream,
									checkedChangeLauncherTypeStream;
	public OnLongClickListener		onLongClickFullUi,
									onLongClickStatusBarBottom,
									onLongClickNotificationStream,
									onLongClickStreamNotificationOnTop,
									onLongClickDialer,
									onLongClickLockTypeStream,
									onLongClickLauncherTypeStream;

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
				savePersoParam();
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
		
		onLongClickFullUi = new OnLongClickListener() {
			public boolean onLongClick(View arg0) {
				Spinner s = (Spinner) mActivity.findViewById(R.id.SpinnerInterfaces);
				int pos = s.getSelectedItemPosition();
				switch (pos) {
				case 0:
					mActivity.LaunchScreenshot(Screen.FULL_UI_ANDROID);
					break;
				case 1:
					mActivity.LaunchScreenshot(Screen.FULL_UI_ACER);
					break;
				default:
					break;
				}
				return false;
			}
		};
		onLongClickStatusBarBottom = new OnLongClickListener() {
			public boolean onLongClick(View arg0) {
				mActivity.LaunchScreenshot(Screen.STATUS_BAR_AT_THE_BOTTOM);
				return false;
			}
		};
		onLongClickNotificationStream = new OnLongClickListener() {
			public boolean onLongClick(View arg0) {
				mActivity.LaunchScreenshot(Screen.NOTIFICATION_TYPE_STREAM);
				return false;
			}
		};
		onLongClickStreamNotificationOnTop = new OnLongClickListener() {
			public boolean onLongClick(View arg0) {
				mActivity.LaunchScreenshot(Screen.STREAM_NOTIFICATION_ON_TOP);
				return false;
			}
		};
		onLongClickDialer = new OnLongClickListener() {
			public boolean onLongClick(View arg0) {
				Spinner s = (Spinner) mActivity.findViewById(R.id.SpinnerDialerType);
				int pos = s.getSelectedItemPosition();
				switch (pos) {
				//dialer android
				case 0:
					mActivity.LaunchScreenshot(Screen.DIALER_TYPE_ANDROID);
					break;
				//dialer stream
				case 1:
					mActivity.LaunchScreenshot(Screen.DIALER_TYPE_STREAM);
					break;
				//dialer AOSP
				case 2:
					mActivity.LaunchScreenshot(Screen.DIALER_TYPE_AOSP);
					break;
				default:
					break;
				}
				return false;
			}
		};
		onLongClickLockTypeStream = new OnLongClickListener() {
			public boolean onLongClick(View arg0) {
				mActivity.LaunchScreenshot(Screen.LOCK_TYPE_STREAM);
				return false;
			}
		};
		onLongClickLauncherTypeStream = new OnLongClickListener() {
			public boolean onLongClick(View arg0) {
				mActivity.LaunchScreenshot(Screen.LAUNCHER_TYPE_STREAM);
				return false;
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
	
	private void savePersoParam(){
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
	}

	//--------------Getters----------------//
	
	public Parameters.State getUIState() {
		return mParam.getUIState();
	}
	
	public UIType getUIType() {
		return mParam.getUIType();
	}
	
	public boolean[] getPersoValues() {
		return mParam.getPersoValues();
	}
	
} //class
