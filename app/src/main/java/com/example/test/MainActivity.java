package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listview = findViewById(R.id.listview);

        ArrayList<Customer> items = new ArrayList<>();
//        items.add(new Customer("고객 1", "010-1234-5678", "남자"));
//        items.add(new Customer("고객 2", "010-4321-8765", "남자"));
//        items.add(new Customer("고객 3", "010-8765-4321", "여자"));

        //MyListViewAdapter adapter = new MyListViewAdapter(items, getApplicationContext());

        ////////////////////////시험///////////////////////////
        AssetManager assetManager = getAssets();
        try {
            InputStream is= assetManager.open("data.json");
            InputStreamReader isr= new InputStreamReader(is);
            BufferedReader reader= new BufferedReader(isr);

            StringBuffer buffer= new StringBuffer();
            String line= reader.readLine();
            while (line!=null){
                buffer.append(line+"\n");
                line=reader.readLine();
            }

            String jsonData= buffer.toString();
            JSONArray jsonArray= new JSONArray(jsonData);

            String s="";

            for(int i=0; i<jsonArray.length();i++){
                JSONObject jo=jsonArray.getJSONObject(i);

                String name= jo.getString("name");
                String msg= jo.getString("msg");
                String gender = jo.getString("gender");
                items.add(new Customer(name, msg, gender));
                //JSONObject flag=jo.getJSONObject("flag");
                //int aa= flag.getInt("aa");
                //int bb= flag.getInt("bb");

                //s += name+" : "+msg+"==>"+aa+","+bb+"\n";
            }
            //tv.setText(s);

        } catch (IOException e) {e.printStackTrace();} catch (JSONException e) {e.printStackTrace(); }

//        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//                items.remove(i);
//                adapter.notifyDataSetChanged();
//
//                Toast.makeText(getApplicationContext(), i+1+"번째 아이템이 삭제되었습니다", Toast.LENGTH_SHORT).show();
//
//                return true;
//            }
//        });
        MyListViewAdapter adapter = new MyListViewAdapter(items, getApplicationContext());

        listview.setAdapter(adapter);

    }
}