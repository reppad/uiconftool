//==============================//
// UI Conf Tool for LCR 1.7		//
// author : grandgto@gmail.com	//
//==============================//

package tommy.lcr.uiconftool;

import tommy.lcr.uiconftool.controller.EventManager;
import tommy.lcr.uiconftool.controller.EventManager.State;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class UiConfTool extends Activity {
	/** Called when the activity is first created. */

	private EventManager mEventManager;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
		initialise();

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
		
	}
	
	public void initialise() {
		mEventManager = new EventManager(this);
		State app = mEventManager.getParamState();
		Button buttonActivateCT = (Button) findViewById(R.id.ButtonActivateCT);
		Spinner s = (Spinner) findViewById(R.id.SpinnerInterfaces);
		Button buttonPerso = (Button) findViewById(R.id.ButtonPerso);
		switch (app) {
		case OFF:
			buttonActivateCT.setText(R.string.main_activateCT);
			s.setVisibility(View.INVISIBLE);
			buttonPerso.setVisibility(View.INVISIBLE);
			break;
		case DEFAULT:
			buttonActivateCT.setText(R.string.main_desactivateCT);
			s.setVisibility(View.VISIBLE);
			buttonPerso.setVisibility(View.INVISIBLE);
			break;
		case PERSO:
			buttonActivateCT.setText(R.string.main_activateCT);
			s.setVisibility(View.VISIBLE);
			buttonPerso.setVisibility(View.VISIBLE);
			break;
		default:
			// ... Error !
			break;
		}
	}
	
	public void popUp(String msg, int duration) {

		Toast toast = Toast.makeText(getApplicationContext(), msg, duration);
		toast.show();
	}
	
	public void affMessage(String msg) {
		TextView tv = (TextView) findViewById(R.id.TextViewMessage);
		tv.setText(msg);
	}
}