package com.manan.admin.davkoylanagar;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class contactT extends AppCompatActivity implements View.OnClickListener {

    private TextView mTextMessage;
    Intent callIntent = new Intent(Intent.ACTION_CALL);

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.senior:
                    //Do nothing
                    return true;
                case R.id.junior:
                    startActivity(new Intent(contactT.this, juniorT.class));
                    return true;
                case R.id.kids:
                    startActivity(new Intent(contactT.this, kidsT.class));
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_t);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        BottomNavigationView bottomNavigationView;
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.setSelectedItemId(R.id.senior);
        findViewById(R.id.msir_call).setOnClickListener(this);
        findViewById(R.id.manan_call).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.msir_call:
                msir();
                break;
            case R.id.manan_call:
                onManan();
                break;
        }
    }

    public void msir() {
        callIntent.setData(Uri.parse("tel:7004225501"));
        onCall();
    }
    public void onCall() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            //request permission from user if the app hasn't got the required permission
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE},   //request specific permission from user
                    10);

            return;
        }else {     //have got permission
            try{
                startActivity(callIntent);  //call activity and make phone call
            }
            catch (android.content.ActivityNotFoundException ex){
                //can't get access
            }
        }
    }
    public void onManan() {


        callIntent.setData(Uri.parse("tel:8936864543"));
        onCall();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainContent.class));
        super.onBackPressed();
    }
}
