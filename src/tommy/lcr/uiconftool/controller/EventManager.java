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

public class EventManager {

	public enum State { OFF , DEFAULT , PERSO };

	private UiConfTool				mActivity;
	private Parameters				mParam;
	private OnClickListener 		buttonActivateCT, buttonPersoListener, buttonValidListener;
	private OnItemSelectedListener	spinnerListener;

	public EventManager(UiConfTool p_activity) {
		mActivity = p_activity;
		mParam = new Parameters();
		String ret = mParam.readConfFile();
		mActivity.affMessage(ret);

		buttonActivateCT = new OnClickListener() {
			public void onClick(View v) {
				mParam.setTHIS_CONF_MUST_BE_APPLIED(!mParam.isTHIS_CONF_MUST_BE_APPLIED());
				mActivity.initialise();
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
				String ret = mParam.applyConfiguration();
				mActivity.affMessage(ret);
				
				AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
				builder.setMessage(R.string.alert_confirmMsg)
						.setCancelable(false)
						.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								//reboot phone
								// ...
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
				mActivity.initialise();
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				//nothing to do
			}
		};
	} //constructor

	public State getParamState() {
		if(!mParam.isTHIS_CONF_MUST_BE_APPLIED())
			return State.OFF;
		if(!mParam.isFULL_UI_ANDROID() & !mParam.isFULL_UI_ACER())
			return State.PERSO;
		return State.DEFAULT;
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
