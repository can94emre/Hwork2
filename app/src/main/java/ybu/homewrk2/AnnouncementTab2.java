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

public class AnnouncementTab2 extends Fragment {
    ListView listview2;
    ArrayList<String>  liste2= new ArrayList<>();
    ArrayList<String>  link2 = new ArrayList<>();
    ArrayAdapter<String> adapter2;
    private static String URL ="http://ybu.edu.tr/muhendislik/bilgisayar";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.announcementlisttab2, container, false);
        listview2 = (ListView) rootView.findViewById(R.id.listview2);
        adapter2 = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,liste2);
        new getData2().execute();
        listview2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(URL+link2.get(i))));
            }
        });


        return rootView;
    }
    private class getData2 extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);


            listview2.setAdapter(adapter2);


        }

        @Override
        protected Void doInBackground(Void... voids) {

            liste2.removeAll(liste2);

            try {
                Document doc2 = Jsoup.connect(URL).get();

                for( Element element : doc2.select("div[class=cncItem]") )
                {
                    if(element.parent().parent().hasClass("contentAnnouncements")){
                        liste2.add(element.text().toString());
                        link2.add(element.select("span").select("a").attr("href"));
                    }
                }
                } catch (IOException e1) {
                e1.printStackTrace();
            }

            return null;
        }
    }
}

