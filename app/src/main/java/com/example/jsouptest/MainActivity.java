package com.example.jsouptest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MainActivity extends AppCompatActivity {

    public ImageView imageView;
    public TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        //Picasso.get().load("https://upload.wikimedia.org/wikipedia/commons/c/c7/Tabby_cat_with_blue_eyes-3336579.jpg")
               //.into(imageView);


    }

    @Override
    protected void onStart() {
        super.onStart();
        AsyncClass ac = new AsyncClass();
        ac.execute();
    }




    public class AsyncClass extends AsyncTask<Void, Void, Void> {
        //private Document doc = Jsoup.connect("https://cookstre.com").get();
        String url = "https://firebase.google.com/";
        ProgressDialog progressDialog;
        private Context ctx;
        public Bitmap bitmap;
        public String title;
        public String imgSrc;
        ImageView imageView;
        TextView textView;
        private View view;

        AsyncClass() {

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            imageView = findViewById(R.id.plsWork);
            textView = findViewById(R.id.textView);
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.show();

        }

        @Override
        protected Void doInBackground(Void... voids) {
            //Document get request stuff?
            try {
                //Connect to website
                Document doc = Jsoup.connect(url).get();

                //Get logo
                Elements img = doc.select("img");

                //Locate the src attribute
                //imgSrc = img.attr("src");
                imgSrc = "https://upload.wikimedia.org/wikipedia/commons/c/c7/Tabby_cat_with_blue_eyes-3336579.jpg";
                //Download image from URL
                InputStream input = new java.net.URL(imgSrc).openStream();

                //Decode bitmap
                bitmap = BitmapFactory.decodeStream(input);

                //Get the title of website
                //title = doc.title();
                Log.i("Async", "Data?");
            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Picasso.get().load(imgSrc).into(imageView);
            //textView.setText(title);
            progressDialog.dismiss();
        }

    }

}