package me.saurav.travelsmart.ui;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.saurav.travelsmart.BuildConfig;
import me.saurav.travelsmart.R;

public class InfoActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    List<Map<String, String>> infoList = new ArrayList<Map<String,String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_info);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.title_titlebar_info));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        initList();
        ListView lv = (ListView) findViewById(R.id.list_info);
        SimpleAdapter simpleAdpt = new SimpleAdapter(this, infoList, android.R.layout.simple_list_item_1,
                new String[] {"info"}, new int[] {android.R.id.text1});

        lv.setAdapter(simpleAdpt);
        lv.setOnItemClickListener(this);
        lv.setSelection(0);

        //views
        TextView tvVersionName=(TextView)findViewById(R.id.label_version);
        tvVersionName.setText("v"+BuildConfig.VERSION_NAME);
    }


    private void initList() {
        infoList.add(createPlanet("info","Version"));
        infoList.add(createPlanet("info","Privacy policy"));
        infoList.add(createPlanet("info", "Published by Saurav Karki "));


    }

    private HashMap<String, String> createPlanet(String key, String name) {
        HashMap<String, String> planet = new HashMap<String, String>();
        planet.put(key, name);
        return planet;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(position==1){
            startActivity(new Intent(this,PrivacyPolicy.class));
        }
        if(position==2){
            Intent browserIntent = new Intent(Intent.ACTION_VIEW);
            browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            browserIntent.setData(Uri.parse(getString(R.string.site_admin)));
            startActivity(browserIntent);
        }

    }
}
