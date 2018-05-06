package ybu.homewrk2;


import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class NewsTab3 extends Fragment {
    ListView listview3;
    ArrayList<String>  liste3= new ArrayList<>();
    ArrayList<String>  link3 = new ArrayList<>();
    ArrayAdapter<String> adapter3;
    private static String URL ="http://ybu.edu.tr/muhendislik/bilgisayar";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.newslisttab3, container, false);
        listview3 = (ListView) rootView.findViewById(R.id.listview3);
        adapter3 = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,liste3);
        new getData3().execute();
        listview3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(URL+link3.get(i))));
            }
        });


        return rootView;
    }
    private class getData3 extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);


            listview3.setAdapter(adapter3);


        }

        @Override
        protected Void doInBackground(Void... voids) {
            liste3.removeAll(liste3);
            try {
                Document doc2 = Jsoup.connect(URL).get();

                for( Element element : doc2.select("div[class=cncItem]") )
                {
                    if(element.parent().parent().hasClass("contentNews")){
                        liste3.add(element.text().toString());
                        link3.add(element.select("span").select("a").attr("href"));
                    }
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            return null;
        }
    }
}




