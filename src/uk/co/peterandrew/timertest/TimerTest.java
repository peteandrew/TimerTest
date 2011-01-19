package uk.co.peterandrew.timertest;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class TimerTest extends Activity {
	private TextView textView;
	private Handler mHandler;
	private long[] mTimes = new long[40];
	private int timerCount = 0;
	private long lastTime;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        textView = (TextView) this.findViewById(R.id.TextView01);
        textView.setMovementMethod(new ScrollingMovementMethod());
        
        mHandler = new Handler();
        lastTime = SystemClock.uptimeMillis();
        mHandler.postDelayed(onTimerEvent, 70);
    }
    
    private Runnable onTimerEvent = new Runnable() {
    	public void run() {
    		if (timerCount < 40) {
    			mHandler.postDelayed(this, 70);
    			long currTime = SystemClock.uptimeMillis();
    			long millis = currTime - lastTime;
    			mTimes[timerCount] = millis;
    			lastTime= currTime;
    			textView.append("" + timerCount + ":\t" + millis + "\n");
    			timerCount++;   			
    		} else {
    			for (int i=0; i<40; i++) {
    				textView.append("" + mTimes[i] + "\n");
    			}
    		}
    	}
    };
}