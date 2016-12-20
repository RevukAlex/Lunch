package com.example.revuk.lunch;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;

public class Activity_Create_Dish extends AppCompatActivity {
    final int OPEN_GALLERY = 1;

    ImageView getPic;
    TextView getNameMenu;
    String nameMenu;
    EditText nameDish;
    EditText description;
    EditText weight;
    String nameMenuS;
    String nameDishS;
    String descriptionS;
    String weightS;
    String uploadImage;
    Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__create__dish);

//ідентифікуєму поля
        nameDish = (EditText) findViewById(R.id.editText_nameDish);
        description = (EditText) findViewById(R.id.editText_description);
        weight = (EditText) findViewById(R.id.editText_weight);
        getPic = (ImageView)findViewById(R.id.imageView_dish);
        getNameMenu = (TextView) findViewById(R.id.textView_nameMenu);
//отримуємо від головного актівіті дані в якому типу меню ми знаходимось
        Intent intent = getIntent();
        nameMenu = intent.getStringExtra(Config.KEY_TAB);
        getNameMenu.setText(nameMenu);

    }


    public void onClick(View v){
    switch (v.getId()) {
        case R.id.button_getpic:
//кнопка викликати меню для вибору картинки
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(intent, OPEN_GALLERY);
            break;
        case R.id.button_create_dish:
//отримати данні з полів вводу
            nameMenuS = nameMenu;
            nameDishS = String.valueOf(nameDish.getText());
            descriptionS = String.valueOf(description.getText());
            weightS = String.valueOf(weight.getText());
//запустити метод дадати в БД
            addMenu();
            break;

        case R.id.button_back:
            this.finish();

            break;
    }
    }

//вернути дані про вибрану картинку
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            if(requestCode == OPEN_GALLERY){
                try {
                    Uri selectedImage = data.getData();
                    final InputStream imageStream = getContentResolver().openInputStream(selectedImage);
                     bitmap = BitmapFactory.decodeStream(imageStream);
                    getPic.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }

        }
    }

//перекодувати файл в String
    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }




    private void addMenu() {

            class addMenu extends AsyncTask<Bitmap, Void, String> {
            ProgressDialog loading;
            RequestHandler rh = new RequestHandler();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Activity_Create_Dish.this, "Adding...", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(Activity_Create_Dish.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Bitmap... bit) {
                Bitmap bitmap = bit[0];
                uploadImage = getStringImage(bitmap);

                HashMap<String, String> params = new HashMap<>();
                params.put(Config.KEY_LUNCH_NAME_MENU,nameMenuS );
                 params.put(Config.KEY_LUNCH_NAME_DISH,nameDishS );
                 params.put(Config.KEY_LUNCH_DESCRIPTION,descriptionS );
                 params.put(Config.KEY_LUNCH_WEIGHT,weightS );
                 params.put(Config.KEY_LUNCH_IMAGE,uploadImage );


                String res = rh.sendPostRequest(Config.URL_ADD_DISH, params);
                return res;


            }
        }
        addMenu addM = new addMenu();
        addM.execute(bitmap);
    }










}
