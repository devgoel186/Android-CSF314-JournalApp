package androidsamples.java.journalapp;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EntryDetailsFragment # newInstance} factory method to
 * create an instance of this fragment.
 */
public class EntryDetailsFragment extends Fragment {

  private static final String TAG = "EntryDetailsFragment";
  private EditText mEditTitle;
  private Button mDateBtn, mStartBtn, mEndBtn;
  private EntryDetailsViewModel mEntryDetailsViewModel;
  private JournalEntry mEntry;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setHasOptionsMenu(true);

    mEntryDetailsViewModel = new ViewModelProvider(requireActivity()).get(EntryDetailsViewModel.class);

    UUID entryId = UUID.fromString(EntryDetailsFragmentArgs.fromBundle(getArguments()).getEntryId());
    Log.d(TAG, "Loading entry: " + entryId);

    mEntryDetailsViewModel.loadEntry(entryId);
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_entry_details, container, false);

    mEditTitle = view.findViewById(R.id.edit_title);
    mDateBtn = view.findViewById(R.id.btn_entry_date);
    mStartBtn = view.findViewById(R.id.btn_start_time);
    mEndBtn = view.findViewById(R.id.btn_end_time);

    view.findViewById(R.id.btn_save).setOnClickListener(this::saveEntry);

    view.findViewById(R.id.btn_entry_date).setOnClickListener(this::pickUserDate);
    view.findViewById(R.id.btn_start_time).setOnClickListener(this::pickUserStartTime);
    view.findViewById(R.id.btn_end_time).setOnClickListener(this::pickUserEndTime);

    return view;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    mEntryDetailsViewModel.getEntryLiveData().observe(requireActivity(),
        entry -> {
          this.mEntry = entry;
          if (entry != null) updateUI();
        });
  }

  @Override
  public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
    super.onCreateOptionsMenu(menu, inflater);
    inflater.inflate(R.menu.menu_entry_detail, menu);
  }

  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    if (item.getItemId() == R.id.delete) {
      Log.d(TAG, "Delete button clicked");

      new AlertDialog.Builder(requireActivity())
          .setTitle("Delete Entry")
          .setMessage("This entry will be deleted. Proceed?")
          .setIcon(android.R.drawable.ic_menu_delete)
          .setPositiveButton(android.R.string.yes, (dialog, whichButton) -> {
            mEntryDetailsViewModel.deleteEntry(mEntry);
            requireActivity().onBackPressed();
          })
          .setNegativeButton(android.R.string.no, null).show();

    }

    else if (item.getItemId() == R.id.share) {
      Log.d(TAG, "Share button clicked");

      Intent sendIntent = new Intent();
      sendIntent.setAction(Intent.ACTION_SEND);
      String text = "Look what I have been up to: " + mEntry.title() + " on " + mEntry.date() + ", " + mEntry.start() + " to " + mEntry.end();
      sendIntent.putExtra(Intent.EXTRA_TEXT, text);
      sendIntent.setType("text/plain");
      Intent.createChooser(sendIntent,"Share via");
      startActivity(sendIntent);
    }

    return super.onOptionsItemSelected(item);
  }

  /**
   * To update EditText and Button elements with new text
   */
  @SuppressLint("SetTextI18n")
  private void updateUI() {
    mEditTitle.setText(mEntry.title());

    if (!mEntry.date().isEmpty()) {
      mDateBtn.setText(mEntry.date());
    } else {
      mDateBtn.setText("Date");
    }

    if (!mEntry.end().isEmpty()) {
      mStartBtn.setText(mEntry.start());
    }
    else {
      mStartBtn.setText("Start Time");
    }

    if (!mEntry.start().isEmpty()) {
      mEndBtn.setText(mEntry.end());
    }
    else {
      mEndBtn.setText("End Time");
    }

  }

  /**
   * Implements navigation action for picking user date using DatePicker
   * @param v button view
   */
  private void pickUserDate(View v) {
    Navigation.findNavController(v).navigate(R.id.datePickerAction);
  }

  /**
   * Implements navigation action for picking user start time using TimePicker
   * @param v button view
   */
  private void pickUserStartTime(View v) {
    EntryDetailsFragmentDirections.TimePickerAction action = EntryDetailsFragmentDirections.timePickerAction();
    action.setIsStart(true);
    Navigation.findNavController(v).navigate(action);
  }

  /**
   * Implements navigation action for picking user end time using TimePicker
   * @param v button view
   */
  private void pickUserEndTime(View v) {
    EntryDetailsFragmentDirections.TimePickerAction action = EntryDetailsFragmentDirections.timePickerAction();
    action.setIsStart(false);
    Navigation.findNavController(v).navigate(action);
  }

  /**
   * Saves the entry in the Room database
   * @param v button view
   */
  private void saveEntry(View v) {
    Log.d(TAG, "Save button clicked");

    mEntry.setTitle(mEditTitle.getText().toString());
    mEntry.setDate(mDateBtn.getText().toString());
    mEntry.setStart(mStartBtn.getText().toString());
    mEntry.setEnd(mEndBtn.getText().toString());

    mEntryDetailsViewModel.saveEntry(mEntry);

    requireActivity().onBackPressed();
  }
}