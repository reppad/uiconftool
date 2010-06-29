package tommy.lcr.uiconftool.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import android.os.Environment;

public class Parameters {	
	private boolean THIS_CONF_MUST_BE_APPLIED;
	private boolean RECOVERY;
	private boolean FULL_UI_ACER;
	private boolean FULL_UI_ANDROID;
	private boolean STATUS_BAR_AT_THE_BOTTOM;
	private boolean NOTIFICATION_TYPE_STREAM;
	private boolean STREAM_NOTIFICATION_ON_TOP;
	private boolean DIALER_TYPE_STREAM;
	private boolean LOCK_TYPE_STREAM;
	private boolean LAUNCHER_TYPE_STREAM;
	private boolean mReset;
	
	/**
	 * default constructor
	 */
	public Parameters() {
		THIS_CONF_MUST_BE_APPLIED = false;
		RECOVERY = false;
		FULL_UI_ACER = false;
		FULL_UI_ANDROID = false;
		STATUS_BAR_AT_THE_BOTTOM = false;
		NOTIFICATION_TYPE_STREAM = false;
		STREAM_NOTIFICATION_ON_TOP = false;
		DIALER_TYPE_STREAM = false;
		LOCK_TYPE_STREAM = false;
		LAUNCHER_TYPE_STREAM = false;
		mReset = false;
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

	public boolean isNOTIFICATION_TYPE_STREAM() {
		return NOTIFICATION_TYPE_STREAM;
	}
	public void setNOTIFICATION_TYPE_STREAM(boolean nOTIFICATION_TYPE_STREAM) {
		STREAM_NOTIFICATION_ON_TOP = nOTIFICATION_TYPE_STREAM;
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
				ret = "readConfFile : root.canRead() is false";
			}
		} catch (Exception e) {
			ret = "readConfFile Exception : " + e.getMessage();
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
		else if(line.startsWith("|NOTIFICATION_TYPE_STREAM|"))
			NOTIFICATION_TYPE_STREAM = readParamValue(line);
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
		
		String content = "#\n" +
		"# Malez@2010\n" +
		"#\n" +
		"# configuration file to customize LCR user Interface\n" +
		"# do not modify entry name. Just modify value to 0 or 1\n" +
		"# to disable / enable a feature\n" +
		"#\n" +
		"# TO APPLY CHANGES, JUST REBOOT\n" +
		"#\n" +
		"# ==========================================================================================\n" +
		"# REINIT INSTRUCTIONS\n" +
		"#\n" +
		"# IMPORTANT IF YOU HAVE ISSUES, DISABLE THIS CONFIG FILE (|THIS_CONF_MUST_BE_APPLIED|0| or rename the file) \n" +
		"# You can also change user interface in Settings/Application/UI on your phone (you won't need it)\n" +
		"#\n" +
		"# Note than using stream part will slow down your phone\n" +
		"#\n" +
		"# AFTER ANY CHANGE IN SETTINGS, YOU WILL HAVE TO REMOVE/READD YOUR WIDGETS\n" +
		"# ==========================================================================================\n" +
		"#\n" +
		"# Line Format (really important)\n" +
		"# |propertyName|value|\n" +
		"#\n" +
		"############################################\n" +
		"#\n" +
		"# General Settings and Recovery\n" +
		"#\n" +
		"############################################\n" +
		"#\n" +
		"# THIS_CONF_MUST_BE_APPLIED : Does this file need to be parsed / 0 => ignore the file. UI is set via Operting system. / 1 => parse the file\n" +
		"|THIS_CONF_MUST_BE_APPLIED|" + getStringValue(THIS_CONF_MUST_BE_APPLIED) + "|\n" +
		"\n" +
		"# RECOVERY : Try this in case you have application that \"Force Close\" after applying a setting\n" +
		"# This will restore defaut Launcher Settings (you will have to reconfigure launchers)\n" +
		"# After recovering this conf will be disabled\n" +
		"# 1 => Try to fix 0 => Default standard\n" +
		"|RECOVERY|" + getStringValue(RECOVERY) + "|\n" +
		"\n" +
		"############################################\n" +
		"#\n" +
		"# Full UI\n" +
		"#\n" +
		"############################################ \n" +
		"#\n" +
		"# If you enable this any other settings will be ignored\n" +
		"# FULL_UI_ANDROID : Full standard Android UI\n" +
		"# FULL_UI_ACER : Full standard Acer UI (Will break widget update)\n" +
		"# Be carrefull : if you enable both, UI will be set to full android\n" +
		"|FULL_UI_ACER|" + getStringValue(FULL_UI_ACER) + "|\n" +
		"|FULL_UI_ANDROID|" + getStringValue(FULL_UI_ANDROID) + "|\n" +
		"\n" +
		"\n" +
		"############################################\n" +
		"#\n" +
		"# Status bar\n" +
		"#\n" +
		"############################################\n" +
		"# STATUS_BAR_AT_THE_BOTTOM : Do you want the status bar to be moved down to bottom / 0 => top / 1 => bottom\n" +
		"|STATUS_BAR_AT_THE_BOTTOM|" + getStringValue(STATUS_BAR_AT_THE_BOTTOM) + "|\n" +
		"\n" +
		"############################################\n" +
		"#\n" +
		"# Notifications\n" +
		"#\n" +
		"############################################\n" +
		"#NOTIFICATION_TYPE_STREAM : Do you want stream like notification / 0=>android notifications / 1=>Stream notifications\n" +
		"|NOTIFICATION_TYPE_STREAM|" + getStringValue(NOTIFICATION_TYPE_STREAM) + "|\n" +
		"\n" +
		"#STREAM_NOTIFICATION_ON_TOP (only available when NOTIFICATION_TYPE_STREAM=1) : Where are located notifications / 0=>BOTTOM 1=>TOP\n" +
		"|STREAM_NOTIFICATION_ON_TOP|" + getStringValue(STREAM_NOTIFICATION_ON_TOP) + "|\n" +
		"\n" +
		"############################################\n" +
		"#\n" +
		"# Dialer and Lock screen\n" +
		"#\n" +
		"############################################\n" +
		"#DIALER_TYPE_STREAM : What kind of dialer (phone call) to use / 0=>Android default dialer / 1=>Stream dialer\n" +
		"|DIALER_TYPE_STREAM|" + getStringValue(DIALER_TYPE_STREAM) + "|\n" +
		"\n" +
		"#LOCK_TYPE_STREAM : Do you want Widget Lock screen / 0=>Standard Android Lock / 1=>Stream Widget Lock\n" +
		"#\n" +
		"# WARNING !!!\n" +
		"# !! Note that this will break widget refresh on desktop on reboot !!\n" +
		"#\n" +
		"# Android lock prefered\n" +
		"#\n" +
		"|LOCK_TYPE_STREAM|" + getStringValue(LOCK_TYPE_STREAM) + "|\n" +
		"\n" +
		"############################################\n" +
		"#\n" +
		"# Launchers\n" +
		"#\n" +
		"############################################\n" +
		"\n" +
		"#LAUNCHER_TYPE_STREAM : \n" +
		"# if you want use breeze launcher and your status bar does not work, set this to 1, otherwise set 0\n" +
		"# Then change user interface in Settings/Application/UI to Acer_UI on your phone\n" +
		"# Enabling this will disable ANDROID type notifications\n" +
		"# To disable, set to 0 and change user interface in Settings/Application/UI to Android_UI on your phone\n" +
		"|LAUNCHER_TYPE_STREAM|" + getStringValue(LAUNCHER_TYPE_STREAM) + "|\n";
		
		try {
		    File root = Environment.getExternalStorageDirectory();
		    if (root.canWrite()){
		        File confFile = new File(root, "LCR_UI.txt");
		        FileWriter writer = new FileWriter(confFile);
		        BufferedWriter out = new BufferedWriter(writer);
		        out.write(content);
		        out.close();
		    }
		} catch (IOException e) {
		    ret = "applyConfiguration IOException : " + e.getMessage();
		}
		
		ret = "applyConfiguration OK";
		return ret;
	}
	
	private String getStringValue(boolean param) {
		if(mReset)
			return "0";
		if(param)
			return "1";
		return "0";
	}
	
	private void reset() {
		mReset = true;
		applyConfiguration();
		mReset = false;
	}

}//class