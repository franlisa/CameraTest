package com.example.cameratest;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
public class MainActivity extends ActionBarActivity {
	Button btn;
	Button btn2;
	ImageView image;
	static final int REQUEST_CAPTURE=1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		image=(ImageView)findViewById(R.id.image);
		btn =(Button)findViewById(R.id.btn);
		btn2=(Button)findViewById(R.id.btn2);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				if(intent.resolveActivity(getPackageManager())!=null){
					startActivityForResult(intent, REQUEST_CAPTURE);
				}
				
				
			}
		});
		btn2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stube
				Intent intent =  new Intent(MainActivity.this,Sec.class);
				startActivity(intent);
				
			}
		});
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode==REQUEST_CAPTURE && resultCode==RESULT_OK){
			Bundle extras=data.getExtras();
			Bitmap bitmap=(Bitmap)extras.get("data");
			image.setImageBitmap(bitmap);
			
			
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
