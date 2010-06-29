package tommy.lcr.uiconftool.controller;

import tommy.lcr.uiconftool.model.Parameters;
import android.app.Activity;
import android.content.Context;
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
	        	// ...
	        	/////////////////////
	        	Context context = mActivity.getApplicationContext();
	        	CharSequence text = "Enregistré, reboot maintenant ;-)";
	        	int duration = Toast.LENGTH_SHORT;

	        	Toast toast = Toast.makeText(context, text, duration);
	        	toast.show();
	        	/////////////////////
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
