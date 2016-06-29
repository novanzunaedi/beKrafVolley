package nozstudio.bekrafvolley;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import nozstudio.bekrafvolley.adapter.CustomAdapter;
import nozstudio.bekrafvolley.model.Cuaca;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button bAmbilData;
    private ListView lvData;
    private List<Cuaca> CuacaItemList = new ArrayList<Cuaca>();
    private CustomAdapter adapter;
    private ProgressDialog pDialog;

    private String urlCuaca = "http://api.openweathermap.org/data/2.5/forecast/daily?id=1625822&appid=103467d312bd63e69dbffc054a4b8cb5";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvData = (ListView) findViewById(R.id.lvDateTime);
        bAmbilData = (Button) findViewById(R.id.bAmbilData);
        bAmbilData.setOnClickListener(this);

        adapter = new CustomAdapter(this, CuacaItemList);
        lvData.setAdapter(adapter);

        pDialog = new ProgressDialog(MainActivity.this);

    }

    @Override
    public void onClick(View view) {
        Ambildatavolley();
    }

    private void Ambildatavolley() {
        pDialog.setMessage("Loading...");
        pDialog.show();

        JsonObjectRequest joReq = new JsonObjectRequest(
                Request.Method.GET,
                urlCuaca,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        pDialog.dismiss();
                        System.out.println("Response " + response);
                        try {
                            JSONArray jarr = response.getJSONArray("list");
                            for(int i = 0; i < jarr.length(); i ++){
                                JSONObject job = jarr.getJSONObject(i);

                                int id = job.getInt("dt");
                                Cuaca cuaca = new Cuaca(String.valueOf(id));
                                CuacaItemList.add(cuaca);

                            }
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.dismiss();
            }
        }
        );

        AppController.getInstance().addToRequestQueue(joReq);
    }


}
