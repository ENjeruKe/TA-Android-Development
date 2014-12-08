package com.example.audio_player;

import java.io.File;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends Activity {
	
	Context context = this;
	private HashMap<String, String> songs = new HashMap<String, String>();
	private String currentSongUri = "android.resource://com.example.audio_player/" + R.raw.test;
	TextView nowPlaying;
	ImageView prevButton;
	ImageView stopButton;
	ImageView playButton;
	ImageView nextButton;
	String description = "test.mp3";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Intent startAudioPlayerServiceIntent = new Intent(MainActivity.this, AudioPlayerService.class );
        context.startService(startAudioPlayerServiceIntent);
        
        retrieveMusicFilesInfo();
        
        if (songs.isEmpty()) {
        	
        	Toast.makeText(context, "No songs found! Using local resources: " + currentSongUri,
					Toast.LENGTH_SHORT).show();
        	
        	songs.put("android.resource://com.example.audio_player/" + R.raw.test, "test.mp3");
        	songs.put("android.resource://com.example.audio_player/" + R.raw.build1, "build1.mp3");
        	songs.put("android.resource://com.example.audio_player/" + R.raw.build2, "build2.mp3");
        	songs.put("android.resource://com.example.audio_player/" + R.raw.build3, "build3.mp3");
		}else {
			
		  currentSongUri = (String) songs.keySet().toArray()[0];
		}
        
        prevButton = (ImageView) this.findViewById(R.id.imagePrevious);
        stopButton = (ImageView) this.findViewById(R.id.imageStop);
        playButton = (ImageView) this.findViewById(R.id.imagePlay);
        nextButton = (ImageView) this.findViewById(R.id.imageNext);
        
        
        prevButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int index = 0;
				String prevUri = currentSongUri;
				String tempUri = null;
				
				for (Object key : songs.keySet()) {
				    String lKey = (String) key;
				    if (lKey == currentSongUri && index == 0) {
				    
				    	prevUri = currentSongUri;
				    	description = songs.get(key);
				    	break;
					} else if (lKey == currentSongUri && index > 0) {
						prevUri = tempUri;
						description = songs.get(key);
						break;
					}
				    tempUri = lKey;
				    index++;
				}
				
				currentSongUri = prevUri;
				
				nowPlaying = (TextView) findViewById(R.id.textNowPlaying);
				nowPlaying.setText(description +"\n"+currentSongUri);
			}			
		});
        
        stopButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent("com.example.audio_player.STOP");
				sendBroadcast(i);
				
				nowPlaying = (TextView) findViewById(R.id.textNowPlaying);
				nowPlaying.setText(description +"\n STOPPED");
			}
		});
        
        playButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
	
				playSong();
			}
        });
        
        nextButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			
				boolean foundCurrent = false;
				
				String nextUri = (String) songs.keySet().toArray()[0];
				
				for (Object key : songs.keySet()) {
					String lKey = (String) key;
					if (foundCurrent) {
						nextUri = lKey;
						description = songs.get(key);
						break;
					}
				    
				    if (lKey == currentSongUri ) {
				    	foundCurrent = true;
					}
				}
				currentSongUri = nextUri;
				
				nowPlaying = (TextView) findViewById(R.id.textNowPlaying);
				nowPlaying.setText(description +"\n"+currentSongUri);
				
				//playSong();
			}
		});
    }

    private void playSong() {
		
		Intent i = new Intent("com.example.audio_player.PLAY");
		i.putExtra("URI", currentSongUri);
		sendBroadcast(i);
		
		nowPlaying = (TextView) findViewById(R.id.textNowPlaying);
		nowPlaying.setText(description +"\n PLAYING");
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
    
    public void retrieveMusicFilesInfo(){
    	//Some audio may be explicitly marked as not being music
    	String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";

    	String[] projection = {
    	        MediaStore.Audio.Media._ID,
    	        MediaStore.Audio.Media.ARTIST,
    	        MediaStore.Audio.Media.TITLE,
    	        MediaStore.Audio.Media.DATA,
    	        MediaStore.Audio.Media.DISPLAY_NAME,
    	        MediaStore.Audio.Media.DURATION
    	};

    	Cursor cursor = this.managedQuery(
    	        MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
    	        projection,
    	        selection,
    	        null,
    	        null);

    	
    	while(cursor.moveToNext()){
    	        songs.put( cursor.getString(3),
    	        		cursor.getString(0) + "||" + cursor.getString(1) + "||" +   cursor.getString(2)  + "||" +  cursor.getString(4) + "||" +  cursor.getString(5));
    	}
    }
    
}
