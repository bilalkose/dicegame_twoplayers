package com.bilalkose.zaratmaikikisilik;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class SecondActivity extends AppCompatActivity {

    ImageView iv_zar_o1, iv_zar_o2, iv_canlar_o1, iv_canlar_o2;
    TextView tv_oyuncu1, tv_oyuncu2;
    Random r;
    int can_o1, can_o2;
    int zar_at_o1, zar_at_o2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        r = new Random();
        iv_zar_o1 = findViewById(R.id.iv_zar_o1);
        iv_zar_o2 = findViewById(R.id.iv_zar_o2);
        iv_canlar_o1 = findViewById(R.id.iv_canlar_o1);
        iv_canlar_o2 = findViewById(R.id.iv_canlar_o2);
        tv_oyuncu1 = findViewById(R.id.tv_oyuncu1);
        tv_oyuncu2 = findViewById(R.id.tv_oyuncu2);

        tv_oyuncu1.setText("OYUNCU 1 ZARI ATACAK!");
        tv_oyuncu2.setText("OYUNCU 2");

        can_o1 = 6;
        can_o2 = 6;

        zarImage(can_o1, iv_canlar_o1);
        zarImage(can_o2, iv_canlar_o2);

        iv_zar_o1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zar_at_o1 = r.nextInt(6) + 1;
                zarImage(zar_at_o1, iv_zar_o1);

                if (zar_at_o2 != 0) {
                    tv_oyuncu1.setText("OYUNCU 1 ZARI!");
                    tv_oyuncu2.setText("OYUNCU 2 ZARI!");
                    if (zar_at_o1 > zar_at_o2) {
                        can_o2--;
                        zarImage(can_o2, iv_canlar_o2);
                        Toast.makeText(SecondActivity.this, "Oyuncu 1 Üstün", Toast.LENGTH_SHORT).show();
                    }
                    if (zar_at_o2 > zar_at_o1) {
                        can_o1--;
                        zarImage(can_o1, iv_canlar_o1);
                        Toast.makeText(SecondActivity.this, "Oyuncu 2 Üstün", Toast.LENGTH_SHORT).show();
                    }
                    if (zar_at_o1 == zar_at_o2) {
                        Toast.makeText(SecondActivity.this, "Zarlar Eşit", Toast.LENGTH_SHORT).show();
                    }

                    zar_at_o1 = 0;
                    zar_at_o2 = 0;

                    iv_zar_o1.setEnabled(true);
                    iv_zar_o2.setEnabled(true);
                    oyunBitimTest();
                } else {
                    tv_oyuncu1.setText("OYUNCU 1 ZARI ATILDI!");
                    iv_zar_o1.setEnabled(false);
                }
            }
        });

        iv_zar_o2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zar_at_o2 = r.nextInt(6) + 1;
                zarImage(zar_at_o2, iv_zar_o2);

                if (zar_at_o1 != 0) {
                    tv_oyuncu1.setText("OYUNCU 1 ZARI!");
                    tv_oyuncu2.setText("OYUNCU 2 ZARI!");
                    if (zar_at_o1 > zar_at_o2) {
                        can_o2--;
                        zarImage(can_o2, iv_canlar_o2);
                        Toast.makeText(SecondActivity.this, "Oyuncu 1 Üstün", Toast.LENGTH_SHORT).show();
                    }
                    if (zar_at_o2 > zar_at_o1) {
                        can_o1--;
                        zarImage(can_o1, iv_canlar_o1);
                        Toast.makeText(SecondActivity.this, "Oyuncu 2 Üstün", Toast.LENGTH_SHORT).show();
                    }

                    if (zar_at_o1 == zar_at_o2) {
                        Toast.makeText(SecondActivity.this, "Zarlar Eşit", Toast.LENGTH_SHORT).show();
                    }

                    zar_at_o1 = 0;
                    zar_at_o2 = 0;

                    iv_zar_o1.setEnabled(true);
                    iv_zar_o2.setEnabled(true);
                    oyunBitimTest();
                } else {
                    tv_oyuncu2.setText("OYUNCU 2 ZARI ATILDI!");
                    iv_zar_o2.setEnabled(false);
                }
            }
        });
    }

    private void zarImage(int zar, ImageView image) {
        switch (zar) {
            case 1:
                image.setImageResource(R.drawable.zar1);
                break;
            case 2:
                image.setImageResource(R.drawable.zar2);
                break;
            case 3:
                image.setImageResource(R.drawable.zar3);
                break;
            case 4:
                image.setImageResource(R.drawable.zar4);
                break;
            case 5:
                image.setImageResource(R.drawable.zar5);
                break;
            case 6:
                image.setImageResource(R.drawable.zar6);
                break;
            default:
                image.setImageResource(R.drawable.zar0);
        }
    }

    private void oyunBitimTest() {
        if (can_o1 == 0 || can_o2 == 0) {
            iv_zar_o1.setEnabled(false);
            iv_zar_o2.setEnabled(false);

            String text = "";
            if (can_o1 != 0) {
                text = "Oyun Bitti! Oyuncu 1 Kazandı!";
            }
            if (can_o2 != 0) {
                text = "Oyun Bitti! Oyuncu 2 Kazandı!";
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(false);
            builder.setMessage(text);
            builder.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }
}
