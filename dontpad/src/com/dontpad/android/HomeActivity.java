package com.dontpad.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class HomeActivity extends Activity {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.main);
        final String[] links = {"ufrjagil", "t2", "C"};
        
        ListView lista = (ListView) findViewById(R.id.lista);
        
        ArrayAdapter<String> turmasAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, links);
        
        lista.setAdapter(turmasAdapter);
        
        OnItemClickListener clickListener = new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view, int pos,
					long id) {
				Intent intent = new Intent(HomeActivity.this, DontpadActivity.class);
				intent.putExtra("link", links[pos]);
				
				//Toast.makeText(AulasActivity.this, turmas[pos] + " - " + salas[pos], Toast.LENGTH_SHORT).show();
				HomeActivity.this.startActivity(intent );
								
			}
		};
		lista.setOnItemClickListener(clickListener);
		
		Button adicionar = (Button) findViewById(R.id.adicionar);
		adicionar.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				LayoutInflater inflater = (LayoutInflater) HomeActivity.this.getSystemService(LAYOUT_INFLATER_SERVICE);
				View layout = inflater.inflate(R.layout.dialog_add, null);
				
				new AlertDialog.Builder(HomeActivity.this).setView(layout)
					.setPositiveButton("Adicionar", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							Toast.makeText(HomeActivity.this, "Opa", Toast.LENGTH_LONG);
							// TODO
						}
					}).
					show();
			}
			
		});
        
    }
}