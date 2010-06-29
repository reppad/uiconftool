//==============================//
// UI Conf Tool for LCR 1.7		//
// author : grandgto@gmail.com	//
//==============================//

package tommy.lcr.uiconftool;

import tommy.lcr.uiconftool.controller.EventManager;
import tommy.lcr.uiconftool.controller.EventManager.State;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;


public class UiConfTool extends Activity {
	/** Called when the activity is first created. */

	private EventManager mEventManager = new EventManager(this);

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
		initialise();

		Spinner s = (Spinner) findViewById(R.id.SpinnerInterfaces);
		ArrayAdapter adapter = ArrayAdapter.createFromResource(
	            this, R.array.interfaces, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		s.setAdapter(adapter);

		Button ButtonPerso = (Button) findViewById(R.id.ButtonPerso);
		ButtonPerso.setOnClickListener(mEventManager.getButtonPersoListener());

		Button ButtonValid = (Button) findViewById(R.id.ButtonValid);
		ButtonValid.setOnClickListener(mEventManager.getButtonValidListener());
		
	}
	
	private void initialise() {
		State app = mEventManager.getParamState();
		switch (app) {
		case OFF:
			Spinner s = (Spinner) findViewById(R.id.SpinnerInterfaces);
			Button ButtonPerso = (Button) findViewById(R.id.ButtonPerso);
			Button ButtonValid = (Button) findViewById(R.id.ButtonValid);
			s.setVisibility(View.INVISIBLE);
			ButtonPerso.setVisibility(View.INVISIBLE);
			ButtonValid.setVisibility(View.INVISIBLE);
			break;
		case DEFAULT:
			Button ButtonP = (Button) findViewById(R.id.ButtonPerso);
			ButtonP.setVisibility(View.INVISIBLE);
			break;
		case PERSO:
			// ...
			break;
		default:
			// ... Error !
			break;
		}
	}
}