package zilic.limuni;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Adapter.ItemClickListener {

    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new Adapter(this);
        adapter.setItemClickListener(this);
        RecyclerView lista = findViewById(R.id.lista);
        lista.setLayoutManager(new LinearLayoutManager(this));
        lista.setAdapter(adapter);

        new REST().execute("https://oziz.ffos.hr/nastava20202021/tzilic_20/LIMUNI/V1/vrhunski");

    }

    @Override
    public void onItemClick(Limun limun) {
        Log.d("Stigao", "---");
        Toast.makeText(this, "Datum: "+String.valueOf(limun.getDatum())+" Kiselost: "+String.valueOf(limun.getKiselost()),Toast.LENGTH_LONG).show();
    }


    private class REST extends AsyncTask<String,Void, List<Limun>>{

        @Override
        protected List<Limun> doInBackground(String... strings) {
            String stringUrl = strings[0];
            Log.wtf(">>>>> ",stringUrl);
            List<Limun> vrati=null;
            try {
                URL myUrl = new URL(stringUrl);
                HttpURLConnection connection =(HttpURLConnection)
                        myUrl.openConnection();
                connection.setRequestMethod("GET");
                connection.setReadTimeout(15000);
                connection.setConnectTimeout(15000);
                connection.connect();
                InputStreamReader streamReader = new
                        InputStreamReader(connection.getInputStream());
                BufferedReader reader = new BufferedReader(streamReader);
                Type listType = new TypeToken<ArrayList<Limun>>(){}.getType();
                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss.000000").create();
                vrati = gson.fromJson(reader, listType);
                reader.close();
                streamReader.close();

            }catch (Exception e){
                e.printStackTrace();
            }
            return vrati;
        }

        @Override
        protected void onPostExecute(List<Limun> limuns) {
            adapter.setLimuni(limuns);
            adapter.notifyDataSetChanged();
        }
    }
}