package be.vbsteven.quickcopy;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class EditEntryActivity extends Activity {
	private String oldValue;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.edit_entry_activity);
		setTitle("Quickcopy : edit an entry");
		
		oldValue = getIntent().getExtras().getString("entry");
		
		final EditText edit = (EditText)findViewById(R.id.editEntry);
		edit.setText(oldValue);
		
		Button saveButton = (Button)findViewById(R.id.saveButton);
		saveButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				DBHelper.get(EditEntryActivity.this).updateEntry(oldValue, edit.getText().toString());	
				finish();
			}
		});
		
		Button deleteButton = (Button)findViewById(R.id.deleteButton);
		deleteButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				DBHelper.get(EditEntryActivity.this).deleteEntry(oldValue);
				finish();
			}
		});
	}
}
