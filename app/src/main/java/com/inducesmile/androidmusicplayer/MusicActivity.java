package com.inducesmile.androidmusicplayer;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.inducesmile.androidmusicplayer.fragment.LibraryFragment;
import com.inducesmile.androidmusicplayer.fragment.PlaylistFragment;

public class MusicActivity extends AppCompatActivity{

    private static final String TAG = MusicActivity.class.getSimpleName();

    private FragmentManager fragmentManager;
    private Fragment fragment = null;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        test_Notification_bic_picture();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        fragmentManager = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragment = new LibraryFragment();
        fragmentTransaction.replace(R.id.main_container_wrapper, fragment);
        fragmentTransaction.commit();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View header = navigationView.inflateHeaderView(R.layout.nav_header_music);
        TextView profileName = (TextView) header.findViewById(R.id.profile_name);
        profileName.setText("Adele");

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.nav_library) {
                    fragment = new LibraryFragment();
                } else if (id == R.id.nav_song) {
                    fragment = new PlaylistFragment();
                } else if (id == R.id.nav_playlist) {

                } else if (id == R.id.nav_sound_cound) {

                }else if (id == R.id.nav_raw_folder) {

                }

                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.main_container_wrapper, fragment);
                transaction.commit();

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                assert drawer != null;
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void test_Notification_bic_picture(){
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent = new Intent(this, SplashActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification.Builder builder = new Notification.Builder(this);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.push2));
        builder.setSmallIcon(R.mipmap.push2);
        builder.setTicker("푸쉬메세지");
        builder.setContentTitle("리얼돌TV 푸쉬메세지");
        builder.setContentText("지금시작합니다!");
        builder.setWhen(System.currentTimeMillis());
        builder.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);
        builder.setPriority(Notification.PRIORITY_MAX);
        builder.setNumber(1);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.push2);
        Notification.BigPictureStyle bigPictureStyle = new Notification.BigPictureStyle(builder);
        bigPictureStyle.setBigContentTitle("제목");
        bigPictureStyle.setSummaryText("내용");
        bigPictureStyle.bigPicture(bitmap); // 아래로 밀면 보여질 그림
        builder.setStyle(bigPictureStyle);
        notificationManager.notify(0, builder.build());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.music, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
