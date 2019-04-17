package com.kgcorner.vachan.viewers;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.Image;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;
import com.kgcorner.sdk.models.Quote;
import com.kgcorner.vachan.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ShareActivity extends Activity {

    private static final int PERMISSIONS_FOR_SHARE = 1000;

    private static final String TAG = "ShareActivity";

    @BindView(R.id.txtQuote)
    TextView txtQuote;

    @BindView(R.id.txtAuthor)
    TextView txtAuthor;

    @BindView(R.id.imgClose)
    ImageView imgClose;

    @BindView(R.id.btnShare)
    Button btnShare;

    @BindView(R.id.imgFont)
    ImageView imgFont;

    @BindView(R.id.imgPallete)
    ImageView imgPallete;

    @BindView(R.id.imgImage)
    ImageView imgImage;



    @BindView(R.id.quoteContainer)
    FrameLayout quoteContainer;

    @BindView(R.id.imgLogo)
    ImageView imgLogo;


    private int[] fonts;

    private Unbinder unbinder;

    private int activeFont = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        unbinder = ButterKnife.bind(this);
        Bundle extras = getIntent().getExtras();
        if( extras != null) {
            Quote quote = (Quote) extras.getSerializable("Quote");
            txtAuthor.setText(quote.getAuthor());
            txtQuote.setText(quote.getQuote());
        }
        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareQuote();
            }
        });
        fonts = new int[7];
        fonts[0] = R.font.sniglet;
        fonts[1] = R.font.pirate;
        fonts[2] = R.font.badscript;
        fonts[3] = R.font.engagement;
        fonts[4] = R.font.fasterone;
        fonts[5] = R.font.greatvibes;
        fonts[6] = R.font.lemonada;

        imgFont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFont();
            }
        });

        imgPallete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeColor();
            }
        });

    }

    private void changeColor() {
        chooseColor();
    }

    private void changeFont() {
        activeFont ++;
        if(activeFont == fonts.length) {
            activeFont = 0;
        }
        Typeface tf = ResourcesCompat.getFont(this, fonts[activeFont]);
        txtQuote.setTypeface(tf);
        txtAuthor.setTypeface(tf);
    }

    private void shareQuote() {
        int permission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE);
        int permissionWrite = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission == PackageManager.PERMISSION_GRANTED &&
                permissionWrite == PackageManager.PERMISSION_GRANTED) {
            startShare();
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    this.PERMISSIONS_FOR_SHARE);
        }
    }

    private void startShare() {
        Bitmap bitmap = getBitmapFromView(quoteContainer);

        String name = new SimpleDateFormat("YYYYmmDDHHMMSS").format(new Date())+".jpg";
        File storedFile = store(bitmap, name);
        try {
            shareImage(storedFile);

        } catch (FileNotFoundException e) {
            Log.e(TAG, "startShare: "+e.getLocalizedMessage(), e);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    private void shareImage(File file) throws FileNotFoundException {
        String url = MediaStore.Images.Media.insertImage(getContentResolver()
                , file.getPath(), file.getName(), "");
        Uri uri = Uri.parse(url);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("image/*");

        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "");
        intent.putExtra(android.content.Intent.EXTRA_TEXT, "");
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        try {
            startActivity(Intent.createChooser(intent, "Share Screenshot"));
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "No App Available", Toast.LENGTH_SHORT).show();
        }
    }

    private Bitmap getBitmapFromView(View view) {
        view.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);
        return bitmap;
    }

    public static File store(Bitmap bm, String fileName){
        final String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath()
                + "/Screenshots";
        File dir = new File(dirPath);
        if(!dir.exists())
            dir.mkdirs();
        File file = new File(dirPath, fileName);
        try {
            FileOutputStream fOut = new FileOutputStream(file);
            bm.compress(Bitmap.CompressFormat.PNG, 85, fOut);
            fOut.flush();
            fOut.close();
            return file;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == PERMISSIONS_FOR_SHARE) {
            if(grantResults.length == 2
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startShare();
            } else {
                Log.w(TAG, "onRequestPermissionsResult: Permission denied for storage");
            }
        } else {
            Log.w(TAG, "onRequestPermissionsResult: Permission denied for storage");
        }
    }

    void chooseColor() {
        ColorPickerDialogBuilder
                .with(this)
                .setTitle("Choose color")
                .initialColor(Color.GRAY)
                .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                .density(12)
                .setOnColorSelectedListener(new OnColorSelectedListener() {
                    @Override
                    public void onColorSelected(int selectedColor) {
                        txtQuote.setTextColor(selectedColor);
                        txtAuthor.setTextColor(selectedColor);
                    }
                })
                .setPositiveButton("ok", new ColorPickerClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
                        Log.d(TAG, "onClick: "+selectedColor);
                        txtQuote.setTextColor(selectedColor);
                        txtAuthor.setTextColor(selectedColor);
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        txtQuote.setTextColor(Color.GRAY);
                        txtAuthor.setTextColor(Color.GRAY);
                    }
                })
                .build()
                .show();
    }

}
