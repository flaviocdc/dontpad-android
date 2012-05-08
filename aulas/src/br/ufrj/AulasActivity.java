package br.ufrj;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class AulasActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.main);
        final String[] turmas = {"A", "B", "C"};
        
        ListView lista = (ListView) findViewById(R.id.lista);
        
        ArrayAdapter<String> turmasAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, turmas);
        
        lista.setAdapter(turmasAdapter);
        
        OnItemClickListener clickListener = new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view, int pos,
					long id) {
				// TODO Auto-generated method stub
				Toast.makeText(AulasActivity.this, turmas[pos], Toast.LENGTH_LONG).show();
			}
		};
        
        lista.setOnItemClickListener(clickListener);
    }
}