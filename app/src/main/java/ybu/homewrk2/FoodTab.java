package ybu.homewrk2;


import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;


public class FoodTab extends Fragment {
    TextView foodlist;
    ArrayList<String> list1 ;

    URL link = new URL("http://ybu.edu.tr/sks");


    public FoodTab() throws MalformedURLException {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View rootView = inflater.inflate(R.layout.foodlisttab, container, false);
        foodlist = (TextView) rootView.findViewById(R.id.foodlist);
        new getData().execute();
        return rootView;
    }
    private class getData extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }


        @Override
        protected Void doInBackground(Void... voids) {
            list1 = new ArrayList<String>();
            try {

                Document doc = Jsoup.parse(link,1000);
                Element Foods = doc.select("table").first();
                Iterator<Element> iterator = Foods.select("td").iterator();
                iterator.next();

                while (iterator.hasNext()){
                    list1.add(iterator.next().text());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            String item = "";
            for(int i=0;i<list1.size();i++){
                item = item + "\n"+list1.get(i).toString();
            }
            foodlist.setText(item);
        }

    }
}

