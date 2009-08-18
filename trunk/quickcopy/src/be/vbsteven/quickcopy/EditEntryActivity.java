/*
        Quickcopy: Android app for managing frequently used text snippets
        Copyright (C) 2009 Steven Van Bael

        This program is free software: you can redistribute it and/or modify
        it under the terms of the GNU General Public License as published by
        the Free Software Foundation, either version 3 of the License, or
        (at your option) any later version.

        This program is distributed in the hope that it will be useful,
        but WITHOUT ANY WARRANTY; without even the implied warranty of
        MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
        GNU General Public License for more details.

        You should have received a copy of the GNU General Public License
        along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
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
