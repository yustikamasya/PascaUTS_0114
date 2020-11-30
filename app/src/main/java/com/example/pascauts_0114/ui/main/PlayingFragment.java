package com.example.pascauts_0114.ui.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pascauts_0114.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PlayingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlayingFragment extends Fragment {

    private ArrayList<Movie> listMovie;
    private RecyclerView rvMovie;
    private String jsonMovie=null;
    void setData(String jsonString){
        try {
            InputStream is = getResources().openRawResource(R.raw.data);
            Writer writer = new StringWriter();
            char[] buffer = new char[1024];
            try {
                Reader reader = new BufferedReader(new InputStreamReader(is,"UTF-8"));
                int n;
                while ((n=reader.read(buffer)) !=-1){
                    writer.write(buffer, 0,n);
                }
            } finally {
                is.close();
            }

            jsonMovie = writer.toString();
            JSONObject jsonObjectData = new JSONObject(jsonMovie);
            JSONArray jsonArray = new JSONArray(jsonObjectData.getString("results").toString());

            listMovie=new ArrayList<>();
            for(int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject=jsonArray.getJSONObject(i);

                Log.d("Data",jsonObject.getString("title"));
                Movie mov = new Movie(jsonObject.getString("title"),
                        jsonObject.getString("release_date"),jsonObject.getString("poster_path"),jsonObject.getString("overview"));
                listMovie.add(mov);
            }
        }catch (IOException | JSONException e){
            e.printStackTrace();
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_playing, container, false);
        setData(jsonMovie);
        rvMovie = view.findViewById(R.id.rv_movie);
        rvMovie.setHasFixedSize(true);
        rvMovie.setLayoutManager(new LinearLayoutManager(getContext()));
        MovieAdapter movieAdapter = new MovieAdapter(getActivity(), listMovie);
        rvMovie.setAdapter(movieAdapter);

        return view;
    }
}