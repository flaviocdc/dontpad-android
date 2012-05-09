package com.dontpad.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.dontpad.android.db.DontpadLinksDAO;

public class HomeActivity extends Activity {
	
	private Cursor linksCursor = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_home);
        
        ListView lista = (ListView) findViewById(R.id.lista);
        
        linksCursor = DontpadLinksDAO.getAllLinks(this);
        startManagingCursor(linksCursor);
        
        ListAdapter adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, linksCursor, new String[] { "link" }, new int[] { android.R.id.text1} );
        
        lista.setAdapter(adapter);
        
		lista.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
				Intent intent = new Intent(HomeActivity.this, DontpadActivity.class);
				
				linksCursor.moveToPosition(pos);
				String link = linksCursor.getString(linksCursor.getColumnIndex("link"));
				
				intent.putExtra("link", link);
				
				HomeActivity.this.startActivity(intent );
			}
		});
		
		Button adicionar = (Button) findViewById(R.id.adicionar);
		adicionar.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				LayoutInflater inflater = (LayoutInflater) HomeActivity.this.getSystemService(LAYOUT_INFLATER_SERVICE);
				View layout = inflater.inflate(R.layout.dialog_add, null);
				
				new AlertDialog.Builder(HomeActivity.this).setView(layout)
					.setPositiveButton("Adicionar", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							Toast.makeText(HomeActivity.this, "Opa", Toast.LENGTH_LONG).show();
							
							EditText linkEdit = (EditText) ((Dialog) dialog).findViewById(R.id.nome_link);
							
							DontpadLinksDAO.insertLink(HomeActivity.this, linkEdit.getText().toString());
							
							linksCursor.requery();
						}
					}).
					show();
			}
			
		});
        
    }
}