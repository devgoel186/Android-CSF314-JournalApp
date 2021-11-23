package androidsamples.java.journalapp;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.Button;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class DatePickerFragment extends DialogFragment {
  @NonNull
  public static DatePickerFragment newInstance(Date date) {
    DatePickerFragment fragment = new DatePickerFragment();
    Bundle args = new Bundle();
    fragment.setArguments(args);
    return fragment;
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    final Calendar calendar = Calendar.getInstance();
    int yearVal = calendar.get(Calendar.YEAR);
    int monthVal = calendar.get(Calendar.MONTH);
    int dateVal = calendar.get(Calendar.DAY_OF_MONTH);
    return new DatePickerDialog(getActivity(), (dp, y, m, d) -> putUserDate(y, m, d), yearVal, monthVal, dateVal);
  }

  /**
   *
   * To populate the date value set by the user in the DatePicker
   *
   * @param year year value
   * @param month month value
   * @param day day value
   */
  public void putUserDate(int year, int month, int day) {
    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.DATE, day);
    cal.set(Calendar.MONTH, month);
    cal.set(Calendar.YEAR, year);

    @SuppressLint("SimpleDateFormat") Format formatter = new SimpleDateFormat("E, MMM dd, yyyy");
    String s = formatter.format(cal.getTime());

    Button b = requireActivity().findViewById(R.id.btn_entry_date);
    b.setText(s);
  }
}
