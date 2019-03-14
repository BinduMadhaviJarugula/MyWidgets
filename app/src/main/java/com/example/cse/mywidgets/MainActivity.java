package com.example.cse.mywidgets;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public static final String NAME="com.example.cse.mywidgets";

    ListView listView;
    //In XML File android:entries="@array/names"

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.list);

        String[] s=getResources().getStringArray(R.array.names);

        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,s);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String itemname=adapterView.getItemAtPosition(i).toString();
                Toast.makeText(MainActivity.this, ""+itemname, Toast.LENGTH_SHORT).show();

                sharedPreferences=getSharedPreferences(NAME,MODE_PRIVATE);
                editor=sharedPreferences.edit();
                StringBuffer buffer=new StringBuffer();
                buffer.append(itemname);
                editor.putString("bindu",buffer.toString());
                editor.apply();

                Intent intent=new Intent(MainActivity.this,SampeWidget.class);
                intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);

                int[] ids=AppWidgetManager.getInstance(MainActivity.this).getAppWidgetIds(new ComponentName(getApplication(),SampeWidget.class));
                intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS,ids);
                sendBroadcast(intent);
            }
        });

    }
}
