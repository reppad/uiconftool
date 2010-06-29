package tommy.lcr.uiconftool.model;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import android.os.Environment;

public class Parameters {	
	private boolean THIS_CONF_MUST_BE_APPLIED;
	private boolean RECOVERY;
	private boolean FULL_UI_ACER;
	private boolean FULL_UI_ANDROID;
	private boolean STATUS_BAR_AT_THE_BOTTOM;
	private boolean STREAM_NOTIFICATION_ON_TOP;
	private boolean DIALER_TYPE_STREAM;
	private boolean LOCK_TYPE_STREAM;
	private boolean LAUNCHER_TYPE_STREAM;
	
	/**
	 * default constructor
	 */
	public Parameters() {
		THIS_CONF_MUST_BE_APPLIED = false;
		RECOVERY = false;
		FULL_UI_ACER = false;
		FULL_UI_ANDROID = false;
		STATUS_BAR_AT_THE_BOTTOM = false;
		STREAM_NOTIFICATION_ON_TOP = false;
		DIALER_TYPE_STREAM = false;
		LOCK_TYPE_STREAM = false;
		LAUNCHER_TYPE_STREAM = false;
		readConfFile();
	}	
	
	//---------Getters and Setters-----------//
	
	public boolean isTHIS_CONF_MUST_BE_APPLIED() {
		return THIS_CONF_MUST_BE_APPLIED;
	}
	public void setTHIS_CONF_MUST_BE_APPLIED(boolean tHIS_CONF_MUST_BE_APPLIED) {
		THIS_CONF_MUST_BE_APPLIED = tHIS_CONF_MUST_BE_APPLIED;
	}

	public boolean isRECOVERY() {
		return RECOVERY;
	}
	public void setRECOVERY(boolean rECOVERY) {
		RECOVERY = rECOVERY;
	}

	public boolean isFULL_UI_ACER() {
		return FULL_UI_ACER;
	}
	public void setFULL_UI_ACER(boolean fULL_UI_ACER) {
		FULL_UI_ACER = fULL_UI_ACER;
	}

	public boolean isFULL_UI_ANDROID() {
		return FULL_UI_ANDROID;
	}
	public void setFULL_UI_ANDROID(boolean fULL_UI_ANDROID) {
		FULL_UI_ANDROID = fULL_UI_ANDROID;
	}

	public boolean isSTATUS_BAR_AT_THE_BOTTOM() {
		return STATUS_BAR_AT_THE_BOTTOM;
	}
	public void setSTATUS_BAR_AT_THE_BOTTOM(boolean sTATUS_BAR_AT_THE_BOTTOM) {
		STATUS_BAR_AT_THE_BOTTOM = sTATUS_BAR_AT_THE_BOTTOM;
	}

	public boolean isSTREAM_NOTIFICATION_ON_TOP() {
		return STREAM_NOTIFICATION_ON_TOP;
	}
	public void setSTREAM_NOTIFICATION_ON_TOP(boolean sTREAM_NOTIFICATION_ON_TOP) {
		STREAM_NOTIFICATION_ON_TOP = sTREAM_NOTIFICATION_ON_TOP;
	}

	public boolean isDIALER_TYPE_STREAM() {
		return DIALER_TYPE_STREAM;
	}
	public void setDIALER_TYPE_STREAM(boolean dIALER_TYPE_STREAM) {
		DIALER_TYPE_STREAM = dIALER_TYPE_STREAM;
	}

	public boolean isLOCK_TYPE_STREAM() {
		return LOCK_TYPE_STREAM;
	}
	public void setLOCK_TYPE_STREAM(boolean lOCK_TYPE_STREAM) {
		LOCK_TYPE_STREAM = lOCK_TYPE_STREAM;
	}

	public boolean isLAUNCHER_TYPE_STREAM() {
		return LAUNCHER_TYPE_STREAM;
	}
	public void setLAUNCHER_TYPE_STREAM(boolean lAUNCHER_TYPE_STREAM) {
		LAUNCHER_TYPE_STREAM = lAUNCHER_TYPE_STREAM;
	}

	//---------Methods-----------//
	
	/**
	 * read configuration file (/sdcard/LCR_UI.txt)
	 * @return Method final state
	 */
	public String readConfFile() {
		String ret;
		try {
			File root = Environment.getExternalStorageDirectory();
			if (root.canRead()){
				File confFile = new File(root, "LCR_UI.txt");
				FileInputStream fstream = new FileInputStream(confFile);
			    DataInputStream in = new DataInputStream(fstream);
			    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			
			    String line = new String();
			    while((line = reader.readLine()) != null) {
			    	searchParam(line);
			    }

				fstream.close();
			    in.close();
			    reader.close();
			} else {
				ret = "root.canRead() is false";
			}
		} catch (Exception e) {
			ret = "Exception : " + e.getMessage();
		}
		ret = "readConfFile OK";
		return ret;
	}
	
	/**
	 * Extract parameter from string
	 * @param line Current line read from the file
	 * @return Method final state
	 */
	private void searchParam(String line) {
		//exit if it is a comment line 
		if(line.startsWith("#"))
			return;
		//read parameter name and set attribute
		if(line.startsWith("|THIS_CONF_MUST_BE_APPLIED|"))
			THIS_CONF_MUST_BE_APPLIED = readParamValue(line);
		else if(line.startsWith("|RECOVERY|"))
			RECOVERY = readParamValue(line);
		else if(line.startsWith("|FULL_UI_ACER|"))
			FULL_UI_ACER = readParamValue(line);
		else if(line.startsWith("|FULL_UI_ANDROID|"))
			FULL_UI_ANDROID = readParamValue(line);
		else if(line.startsWith("|STATUS_BAR_AT_THE_BOTTOM|"))
			STATUS_BAR_AT_THE_BOTTOM = readParamValue(line);
		else if(line.startsWith("|STREAM_NOTIFICATION_ON_TOP|"))
			STREAM_NOTIFICATION_ON_TOP = readParamValue(line);
		else if(line.startsWith("|DIALER_TYPE_STREAM|"))
			DIALER_TYPE_STREAM = readParamValue(line);
		else if(line.startsWith("|LOCK_TYPE_STREAM|"))
			LOCK_TYPE_STREAM = readParamValue(line);
		else if(line.startsWith("|LAUNCHER_TYPE_STREAM|"))
			LAUNCHER_TYPE_STREAM = readParamValue(line);
	}
	
	/**
	 * @param line Current line read from the file
	 * @return Value of the parameter
	 */
	private boolean readParamValue(String line) {
		return line.contains("|1|");
	}
	
	/**
	 * Generate configuration file from classe attributes
	 * @return Method final state
	 */
	private String applyConfiguration() {
		String ret;
		
		// ...
		ret = "applyConfiguration OK";
		return ret;
	}
	
	private String getStringValue(boolean param) {
		return null;
	}
//	public void writeTestFile() {
//		try {
//		    File root = Environment.getExternalStorageDirectory();
//		    if (root.canWrite()){
//		        File gpxfile = new File(root, "testFile.txt");
//		        FileWriter gpxwriter = new FileWriter(gpxfile);
//		        BufferedWriter out = new BufferedWriter(gpxwriter);
//		        out.write("Hello world");
//		        out.close();
//		    }
//		} catch (IOException e) {
//		    
//		}
//	}


}//class
