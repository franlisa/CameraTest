package com.example.cameratest;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Bundle;
import android.widget.ImageView;

public class Sec extends Activity{
	ImageView image;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	   setContentView(R.layout.sec_layout);
	   image =(ImageView)findViewById(R.id.bitmap);
	   
	   image.setImageBitmap(decodeSampleBitmapFromRes(getResources(),R.drawable.shop_tbsearch_spen_icon_wangwnag,50,50));
	}
	public static Bitmap decodeSampleBitmapFromRes(Resources res,int resId,int reqWidth,int reqHeight){
		final BitmapFactory.Options options =new BitmapFactory.Options();
		options.inJustDecodeBounds=true;//先设置true加载回来的才是null 避免分配了内存
		BitmapFactory.decodeResource(res, resId, options);
		options.inSampleSize=adaptSize(options,reqWidth,reqHeight);
		options.inJustDecodeBounds=false;//要分配图片内存了
		return BitmapFactory.decodeResource(res, resId, options);
	
	}
	private static int adaptSize(Options options, int reqWidth, int reqHeight) {
		// TODO Auto-generated method stub
		final int width = options.outWidth;
		final int height = options.outHeight;
		int sampleSize=1;
		if(height>reqHeight || width > reqWidth){
			while((height/sampleSize > reqHeight) && (width/sampleSize> reqWidth)){
				sampleSize*=2;
			}
		}
		return sampleSize;
	}

}
