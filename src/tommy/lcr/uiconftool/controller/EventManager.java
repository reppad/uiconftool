package tommy.lcr.uiconftool.controller;

import tommy.lcr.uiconftool.R;
import tommy.lcr.uiconftool.UiConfTool;
import tommy.lcr.uiconftool.model.Parameters;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

/**
 * Event manager
 * @author grandgto@gmail.com
 */
public class EventManager {

	private UiConfTool				mActivity;
	private Parameters				mParam;
	private OnClickListener 		buttonActivateCT, buttonPersoListener, buttonValidListener;
	private OnItemSelectedListener	spinnerListener;

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
				mActivity.popUp("Coming soon", 0);	//test
				// ...
			}
		};

		buttonValidListener = new OnClickListener() {
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
				builder.setMessage(R.string.alert_confirmMsg)
						.setCancelable(false)
						.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								mParam.applyConfiguration();
								mActivity.finish();
							}
						})
						.setNeutralButton(R.string.no, new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								mActivity.finish();
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
		};
		
		spinnerListener = new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> spinner, View arg1,
					int position, long rowId) {

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
	} //constructor

	/**
	 * Read conf file
	 * @return Message
	 */
	public String readParam() {
		return mParam.readConfFile();
	}
	
	/**
	 * Set spinner position
	 */
	public void setSpinnerPosition() {
		switch (mParam.getUIType()) {
		case ANDROID:
			mActivity.setSpinnerSelectedItem(0);
			break;
		case ACER:
			mActivity.setSpinnerSelectedItem(1);
			break;
		case PERSO:
			mActivity.setSpinnerSelectedItem(2);
			break;
		default:
			//impossible case
			break;
		}
	}
	
	public void resetConfiguration() {
		mParam.resetConfiguration();
		setSpinnerPosition();
		mActivity.refresh();
	}

	//--------------Getters----------------//
	
	public Parameters.State getUIState() {
		return mParam.getUIState();
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

	public OnItemSelectedListener getSpinnerListener() {
		return spinnerListener;
	}

} //class
