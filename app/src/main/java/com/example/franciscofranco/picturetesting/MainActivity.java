package com.example.franciscofranco.picturetesting;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String FILEPATH = "filePath";

    private File cameraDirectory;
    private List<String> images;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (isExternalStorageReadable()) {
            cameraDirectory = findCameraDirectory();
            images = collectImages(cameraDirectory);
        }

        listView = (ListView) findViewById(R.id.listView);

        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, images);

        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                intent.putExtra(FILEPATH, cameraDirectory.toString() + "/" + images.get(position));
                startActivity(intent);
            }
        });

    }

    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    public File findCameraDirectory() {
        File DCIMdirectory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString());
        File[] files = DCIMdirectory.listFiles();

        File CameraDirectory = null;

        for (File CurFile : files) {
            if (CurFile.getName().equals("Camera")) {
                CameraDirectory = CurFile;
                break;
            }
        }

        return CameraDirectory;
    }

    public List<String> collectImages(File CameraDirectory) {
        File[] files = CameraDirectory.listFiles();
        List<String> images = new LinkedList<String>();
        String fileName;

        for (File CurFile : files) {
            fileName = CurFile.getName();
            if (fileName.endsWith("jpg")) {
                images.add(fileName);
            }
        }

        return images;
    }
}
