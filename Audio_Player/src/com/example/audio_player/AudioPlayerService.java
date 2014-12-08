package com.example.audio_player;

import java.io.IOException;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class AudioPlayerService extends Service {

	private static final String TAG = AudioPlayerService.class.getSimpleName();
	Uri musicFileUri;
	private boolean isServiceStarted = false;
	private boolean isPlaying = false;
	private MediaPlayer mPlayer;

	private final BroadcastReceiver receiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			
			Bundle data = new Bundle();
			data = intent.getExtras();
			
			if (action.equals("com.example.audio_player.PLAY")) {
				Log.d(TAG, "PLAY Command received : " + data.getString("URI"));
				// action for play received
				
				if(mPlayer == null){
					mPlayer =  new MediaPlayer();
					//mPlayer =  MediaPlayer.create(context, 5);
					//mPlayer.setDataSource(context, Uri.parse("android.resource://com.package.name/raw/song"));
				}else
				if (mPlayer.isPlaying()) {
					mPlayer.stop();
					mPlayer.release();
					mPlayer = null;
					mPlayer =  new MediaPlayer();
				}
				PlayUri(data.getString("URI"));
		
			} else if (action.equals("com.example.audio_player.STOP")) {
				// action for stop
				Log.d(TAG, "STOP Command received");
				if(mPlayer!=null && mPlayer.isPlaying()){
					mPlayer.stop();
					mPlayer.release();
					mPlayer = null;
				}
			}
		}
	};

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		IntentFilter filter = new IntentFilter();
		filter.addAction("com.example.audio_player.STOP");
		filter.addAction("com.example.audio_player.PLAY");

		registerReceiver(receiver, filter);
	}

	@Override
	@Deprecated
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		Log.d(TAG, "onStart invoked");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
	

		Log.d(TAG, "onStartCommand invoked");
		if (isServiceStarted == false) {
			isServiceStarted = true;
		}
		/*Bundle data = new Bundle();
		data = intent.getExtras();

		PlayerCommands cmd = PlayerCommands.valueOf(data.getString("Command"));

		if (cmd == PlayerCommands.Play && !isPlaying) {
			Log.d(TAG, "Play Command received");
		    PlayUri(data.getString("URI"));
		}*/

		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if (mPlayer != null) {
			mPlayer.release();
			mPlayer = null;
		}
		unregisterReceiver(receiver);
	}


	public void PlayUri(String uriString) {
		mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
		Uri uri = Uri.parse(uriString);
		try {
			mPlayer.setDataSource(getApplicationContext(), uri);
		} catch (IllegalArgumentException e) {
			Toast.makeText(getApplicationContext(),
					"You might not set the URI correctly!", Toast.LENGTH_LONG)
					.show();
		} catch (SecurityException e) {
			Toast.makeText(getApplicationContext(),
					"You might not set the URI correctly!", Toast.LENGTH_LONG)
					.show();
		} catch (IllegalStateException e) {
			Toast.makeText(getApplicationContext(),
					"You might not set the URI correctly!", Toast.LENGTH_LONG)
					.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			mPlayer.prepare();
		} catch (IllegalStateException e) {
			Toast.makeText(getApplicationContext(),
					"You might not set the URI correctly!", Toast.LENGTH_LONG)
					.show();
		} catch (IOException e) {
			Toast.makeText(getApplicationContext(),
					"You might not set the URI correctly!", Toast.LENGTH_LONG)
					.show();
		}
		mPlayer.start();

	}
}
