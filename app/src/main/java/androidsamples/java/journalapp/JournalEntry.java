package androidsamples.java.journalapp;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity(tableName = "journal_table")
public class JournalEntry {
  @PrimaryKey
  @ColumnInfo(name = "id")
  @NonNull
  private UUID mUid;

  @ColumnInfo(name = "title")
  private String mTitle;

  @ColumnInfo(name = "date")
  private String mDate;

  @ColumnInfo(name = "start")
  private String mStart;

  @ColumnInfo(name = "end")
  private String mEnd;

  public JournalEntry(@NonNull String title, String date, String start, String end) {
    mUid = UUID.randomUUID();
    mTitle = title;
    mDate = date;
    mStart = start;
    mEnd = end;
  }

  @NonNull
  public UUID getUid() {
    return mUid;
  }

  public void setUid(@NonNull UUID id) {
    mUid = id;
  }

  public String title() {
    return mTitle;
  }

  public void setTitle(String title) {
    mTitle = title;
  }

  public String date() {
    return mDate;
  }

  public void setDate(String mDate) {
    this.mDate = mDate;
  }

  public String start() {
    return mStart;
  }

  public void setStart(String mStart) {
    this.mStart = mStart;
  }

  public String end() {
    return mEnd;
  }

  public void setEnd(String mEnd) {
    this.mEnd = mEnd;
  }
}
