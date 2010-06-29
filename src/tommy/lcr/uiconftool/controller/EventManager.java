package tommy.lcr.uiconftool.controller;

import tommy.lcr.uiconftool.R;
import tommy.lcr.uiconftool.model.Parameters;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class EventManager {

	public enum State { OFF , DEFAULT , PERSO };

	private Activity		mActivity;
	private Parameters		mParam;
	private OnClickListener buttonPersoListener,
	buttonValidListener;

	public EventManager(Activity p_activity) {
		mActivity = p_activity;
		mParam = new Parameters();

		buttonPersoListener = new OnClickListener() {
			public void onClick(View v) {
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
	} //constructor

	public State getParamState() {
		if(!mParam.isTHIS_CONF_MUST_BE_APPLIED())
			return State.OFF;
		if(mParam.isFULL_UI_ANDROID() || mParam.isFULL_UI_ACER())
			return State.PERSO;
		return State.DEFAULT;
	}

	public OnClickListener getButtonPersoListener() {
		return buttonPersoListener;
	}

	public OnClickListener getButtonValidListener() {
		return buttonValidListener;
	}


} //class
