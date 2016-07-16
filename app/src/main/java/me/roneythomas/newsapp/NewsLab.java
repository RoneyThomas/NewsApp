package me.roneythomas.newsapp;

import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class NewsLab {
    private static NewsLab sNewsLab;
    private ArrayList<News> mNewsArrayList;

    private NewsLab(Context context) throws ExecutionException, InterruptedException {
        mNewsArrayList = new ArrayList<>();
        JSONObject resultJSON = new getNews().execute("http://content.guardianapis.com/search?page=1&q=android&api-key=test").get();
        try {
            if (resultJSON.getJSONObject("response").getString("status").equals("ok")) {
                JSONArray newsJSONlist = resultJSON.getJSONObject("response").getJSONArray("results");
                for (int a = 0; a < newsJSONlist.length(); a++) {
                    JSONObject newsJSON = newsJSONlist.getJSONObject(a);
                    News news1 = new News(newsJSON.getString("webTitle"), newsJSON.getString("sectionName"), newsJSON.getString("webUrl"));
                    mNewsArrayList.add(news1);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static NewsLab getInstance(Context context) throws ExecutionException, InterruptedException {
        if (sNewsLab == null) {
            sNewsLab = new NewsLab(context);
        }
        return sNewsLab;
    }

    public ArrayList<News> getNews() {
        return mNewsArrayList;
    }

    private class getNews extends AsyncTask<String, Void, JSONObject> {

        @Override
        protected JSONObject doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setReadTimeout(1000);
                urlConnection.setConnectTimeout(1500);
                urlConnection.setDoInput(true);

                urlConnection.connect();
                if (urlConnection.getResponseCode() == 200) {
                    int len = 6000;
                    InputStream inputStream = urlConnection.getInputStream();
                    String inputString = inputString(inputStream, len);
                    return new JSONObject(inputString);
                }
                return null;
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        private String inputString(InputStream inputStream, int len) throws IOException {
            Reader reader = null;
            reader = new InputStreamReader(inputStream, "UTF-8");
            char[] buffer = new char[len];
            reader.read(buffer);
            return new String(buffer);
        }
    }
}
