package com.example.cameratest;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.util.LruCache;
import android.widget.ImageView;

public class Sec extends Activity{
	ImageView image;
	private LruCache<String, Bitmap> mMenoryCache; 
	final int REQ_WIDTH=100;
	final int REQ_HEIGHT=100;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	   setContentView(R.layout.sec_layout);
	   image =(ImageView)findViewById(R.id.bitmap);
	   initMemoryCache();
	   loadBitmap(R.drawable.shop_tbsearch_spen_icon_wangwnag,REQ_WIDTH,REQ_HEIGHT);
	   //asyncLoadBitMap(R.drawable.shop_tbsearch_spen_icon_wangwnag,REQ_WIDTH,REQ_HEIGHT);
	 
	}
	private void initMemoryCache(){
		//获取运行时最大内存，kilobytes表示
		final int maxMemory = (int) Runtime.getRuntime().maxMemory()/1024;
		mMenoryCache=new LruCache<String,Bitmap>(maxMemory/8){
			@Override
			/*By default, the cache size is measured in the number of entries. Override
			 * {@link #sizeOf} to size the cache in different units.*/
			protected int sizeOf(String key, Bitmap value) {
				// TODO Auto-generated method stub
				return value.getByteCount()/1024;
			}
		};
	}
	public void addBitmapToCache(String key,Bitmap bitmap){
		if(getBitmapFromCache(key)==null){
			mMenoryCache.put(key, bitmap);
		}
		
	}
	public Bitmap getBitmapFromCache(String key){
		return mMenoryCache.get(key);
		
	}
	private void loadBitmap(int resId, int reqWidth, int reqHeight) {
		// TODO Auto-generated method stub
		final String resKey=String.valueOf(resId);
		Bitmap bitmap=mMenoryCache.get(resKey);
		if(bitmap!=null){
			image.setImageBitmap(bitmap);
		} else{
			asyncLoadBitMap(resId, reqWidth, reqHeight);
		}
		
		
	}
	private void asyncLoadBitMap(int data,int reqWidth,int reqHeight) {
		// TODO Auto-generated method stub
		new AsyncTask<Integer, Object, Bitmap>() {

			@Override
			protected Bitmap doInBackground(Integer... params) {
				// TODO Auto-generated method stub
			   try {
				Thread.sleep(1000);//暂停1秒，模拟从网上下载
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  Bitmap bitmap=decodeSampleBitmapFromRes(getResources(), params[0],params[1],params[2]);
			  mMenoryCache.put(String.valueOf(params[0]),bitmap);
			  return bitmap;
				
			}
			protected void onPostExecute(Bitmap result) {
				image.setImageBitmap(result);
			};
		}.execute(data,reqWidth,reqHeight);
		
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
