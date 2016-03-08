package com.example.avanthi.scanningapp;

import android.app.Activity;
import android.graphics.Color;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends Activity {

        private File root;
        private ArrayList<File> filesList = new ArrayList<File>();
        private LinearLayout view;
        Button scanBtn;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            scanBtn = (Button)findViewById(R.id.BtnSearch);
            scanBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    view = (LinearLayout) findViewById(R.id.view);
                    root = new File(Environment.getExternalStorageDirectory()
                            .getAbsolutePath());
                    getfile(root);

                    for (int i = 0; i < filesList.size(); i++) {
                        TextView textView = new TextView(MainActivity.this);
                        textView.setText(filesList.get(i).getName());
                        textView.setPadding(5, 5, 5, 5);
                        textView.setAllCaps(true);
                        textView.setTextSize(20);
                        textView.setTextColor(Color.BLUE);

                        System.out.println(filesList.get(i).getName());

                        if (filesList.get(i).isDirectory()) {
                            textView.setTextColor(Color.parseColor("#EC3C3C"));
                        }
                        view.addView(textView);
                    }

                }
            });

        }



        private void ListOfTenBigFiles() {
            for (int i = 0; i < filesList.size() ; i++) {

                Collections.sort(filesList);
                String lastFile = filesList.get(filesList.size()).getName();
                System.out.println(lastFile);

                
            }
        }

        public ArrayList<File> getfile(File dir) {
            File listFile[] = dir.listFiles();
            if (listFile != null && listFile.length > 0) {
                for (int i = 0; i < listFile.length; i++) {

                    if (listFile[i].isDirectory()) {
                        filesList.add(listFile[i]);
                        getfile(listFile[i]);

                    } else {
                        if (listFile[i].getName().endsWith(".png")
                                || listFile[i].getName().endsWith(".jpg")
                                || listFile[i].getName().endsWith(".mp4")
                                || listFile[i].getName().endsWith(".mp3"))


                        {
                            filesList.add(listFile[i]);
                        }
                    }

                }
            }
            return filesList;
        }


    }
