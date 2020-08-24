package com.sheela.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btnnotification, btnnotification1;
    private NotificationManagerCompat notificationManagerCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notificationManagerCompat = NotificationManagerCompat.from(this);
        CreateChannel channel = new CreateChannel(this);
        channel.createChannel();
        btnnotification = findViewById(R.id.btnnotification);
        btnnotification1 = findViewById(R.id.btnnotification1);
        btnnotification.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                DisplayNotification();
            }
        });
        btnnotification1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DisplayNotification1();
            }
        });
    }

    private void DisplayNotification() {
        Notification notification = new NotificationCompat.Builder(this, CreateChannel.CHANNEL_1)
                .setSmallIcon(R.drawable.ic_audiotrack_black_24dp)
                .setContentTitle("First Message")
                .setContentText("First message body")
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();
        notificationManagerCompat.notify(1, notification);
    }

    private void DisplayNotification1() {
        Notification notification = new NotificationCompat.Builder(this, CreateChannel.CHANNEL_2)
                .setSmallIcon(R.drawable.ic_camera_alt_black_24dp)
                .setContentTitle("Second Message")
                .setContentText("Second message body")
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();
        notificationManagerCompat.notify(2, notification);
    }
}
