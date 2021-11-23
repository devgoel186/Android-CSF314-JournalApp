package androidsamples.java.journalapp;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.Button;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

public class TimePickerFragment extends DialogFragment {

  private boolean isStart;

  @NonNull
  public static TimePickerFragment newInstance(Date time) {
    TimePickerFragment fragment = new TimePickerFragment();
    Bundle args = new Bundle();
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setHasOptionsMenu(true);

    isStart = TimePickerFragmentArgs.fromBundle(getArguments()).getIsStart();
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
    final Calendar c = Calendar.getInstance();
    int hour = c.get(Calendar.HOUR_OF_DAY);
    int minute = c.get(Calendar.MINUTE);

    return new TimePickerDialog(getActivity(), (tp, h, m) -> putUserTime(h, m), hour, minute, DateFormat.is24HourFormat(getActivity()));
  }

  /**
   * to populate the time set by user in TimePicker component
   *
   * @param h hours value
   * @param m minutes value
   */
  public void putUserTime(int h, int m) {
    String s = formatTime(h) + ":" + formatTime(m);
    Button b;

    b = isStart ? requireActivity().findViewById(R.id.btn_start_time) : requireActivity().findViewById(R.id.btn_end_time);
    b.setText(s);
  }

  /**
   * Formats the hour and minute value into a proper format
   *
   * @param value the time value passed to the function, that is, hours or minutes
   * @return String
   */
  private String formatTime(int value)
  {
    if(value <= 9)
      return "0" + value;
    return Integer.toString(value);
  }
}
