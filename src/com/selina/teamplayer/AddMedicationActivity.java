package com.selina.teamplayer;
import java.util.Calendar;

import com.teamplayer.database.Medication;
import com.teamplayer.database.MedicationDBHandler;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.ToggleButton;

public class AddMedicationActivity extends Activity {

	EditText txtDate, txtTime, medName, medIntruction;
	ToggleButton toggleRemainder;
	Medication newMedication;
	MedicationDBAdapter medDBAdapter;
	Calendar alarmCal;
	int year1, monthOfYear1, dayOfMonth1;

	// Variable for storing current date and time
	private int mYear, mMonth, mDay, mHour, mMinute;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_medication);

		txtDate = (EditText) findViewById(R.id.txtDatePicket1);
		txtTime = (EditText) findViewById(R.id.txtTimePicket11);

		medName = (EditText) findViewById(R.id.medname);
		medIntruction = (EditText) findViewById(R.id.medIntruction);
		toggleRemainder = (ToggleButton) findViewById(R.id.togglebutton);

		/*
		 * //populate the spinner Spinner spinner = (Spinner)
		 * findViewById(R.id.dosage_spinner); // Create an ArrayAdapter using
		 * the string array and a default spinner layout
		 * ArrayAdapter<CharSequence> adapter =
		 * ArrayAdapter.createFromResource(this, R.array.dosage_array,
		 * android.R.layout.simple_spinner_item); // Specify the layout to use
		 * when the list of choices appears
		 * adapter.setDropDownViewResource(android
		 * .R.layout.simple_spinner_dropdown_item); // Apply the adapter to the
		 * spinner spinner.setAdapter(adapter);
		 */

		medDBAdapter = new MedicationDBAdapter(this);
		medDBAdapter = medDBAdapter.open();

	}

	public void addMedication(View v) {

		// extract all values
		Medication newMedication = new Medication();
		newMedication.setMedName(medName.getText().toString());
		System.out.println("medName:" + medName.getText().toString());
		newMedication.setMedInstrction(medIntruction.getText().toString());
		System.out.println("medIntruction:"
				+ medIntruction.getText().toString());
		newMedication.setStartDate(txtDate.getText().toString());
		System.out.println("txtDate:" + txtDate.getText().toString());
		newMedication.setAlertTime(txtTime.getText().toString());
		System.out.println("txtTime:" + txtTime.getText().toString());
		if (toggleRemainder.isChecked()) {
			newMedication.setRemainder("ON");
			System.out.println("ON");
			// Add a remainder
			addRemainder(newMedication, alarmCal);
		} else {
			newMedication.setRemainder("OFF");
			alarmCal = null;
		}

		medDBAdapter.insertEntry(newMedication.getMedName(),
				newMedication.getMedInstrction(), newMedication.getRemainder(),
				newMedication.getStartDate(), newMedication.getAlertTime());
		
		Intent intent = new Intent (AddMedicationActivity.this,MainWeixin.class);			
		startActivity(intent);			
		AddMedicationActivity.this.finish();
		//place to put checkin reminder

	}

	private void addRemainder(Medication newMedication2, Calendar alamCalendar) {
		if (alamCalendar != null) {
			System.out.println("Machan I am here in add Remainder da");
			Intent intentAlarm = new Intent(this, AlarmReceiver.class);
			long when = alamCalendar.getTimeInMillis();  
			// create the object
			AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
			PendingIntent alarmIntent = PendingIntent.getBroadcast(this, 0, intentAlarm, 0);

			
			 //set the alarm for particular time
			//alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, when, AlarmManager.INTERVAL_FIFTEEN_MINUTES, PendingIntent.getBroadcast(this,1,  intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT));
			alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, alamCalendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, alarmIntent);
			
		}
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		newMedication = null;

	}

	public void returnPre(View v) {
		// some code
		finish();
	}

	public void onToggleClicked(View view) {
		// Is the toggle on?
		boolean on = ((ToggleButton) view).isChecked();

		if (on) {
			// Enable vibrate
		} else {
			// Disable vibrate
		}
	}

	public void popupCalendar(View v) {

		// Process to get Current Date
		final Calendar c = Calendar.getInstance();
		mYear = c.get(Calendar.YEAR);
		mMonth = c.get(Calendar.MONTH);
		mDay = c.get(Calendar.DAY_OF_MONTH);

		// Launch Date Picker Dialog
		DatePickerDialog dpd = new DatePickerDialog(this,
				new DatePickerDialog.OnDateSetListener() {

					@Override
					public void onDateSet(DatePicker view, int year,
							int monthOfYear, int dayOfMonth) {
						// Display Selected date in textbox
						txtDate.setText((monthOfYear + 1) + "/" + dayOfMonth
								+ "/" + year);
						year1 = year;
						monthOfYear1 = monthOfYear + 1;
						dayOfMonth1 = dayOfMonth;

					}
				}, mYear, mMonth, mDay);
		dpd.show();

	}

	public void popupTime(View v) {

		// Process to get Current Time
		final Calendar c = Calendar.getInstance();
		mHour = c.get(Calendar.HOUR_OF_DAY);
		mMinute = c.get(Calendar.MINUTE);

		// Launch Time Picker Dialog
		TimePickerDialog tpd = new TimePickerDialog(this,
				new TimePickerDialog.OnTimeSetListener() {

					@Override
					public void onTimeSet(TimePicker view, int hourOfDay,
							int minute) {
						// Display Selected time in textbox
						txtTime.setText(hourOfDay + ":" + minute);
						alarmCal = Calendar.getInstance();
						//alarmCal.set(year1, monthOfYear1, dayOfMonth1,hourOfDay, minute);
						alarmCal.setTimeInMillis(System.currentTimeMillis());
						alarmCal.set(Calendar.HOUR_OF_DAY, hourOfDay);
						alarmCal.set(Calendar.MINUTE, minute);

						

					}
				}, mHour, mMinute, false);
		tpd.show();
	}

}
