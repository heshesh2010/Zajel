package soft.deal.best.topline.islamzajel;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class BoxesActivity extends AppCompatActivity {
    RecyclerView BoxesRecyclerView;
Activity mActivity= BoxesActivity.this;
    ImageView BackKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_boxes);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/AdobeArabic-Regular.otf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        BoxesRecyclerView = (RecyclerView) findViewById(R.id.BoxesRV);
        BackKey= (ImageView) findViewById(R.id.BackKey);

String BoxesUrl = "http://api.islamzajel.com/api/Categories";
        BoxesAsyncTask  BoxesTask = new BoxesAsyncTask(mActivity, BoxesRecyclerView);
        BoxesTask.execute(BoxesUrl);
        try {
        BoxesTask.get(15000, TimeUnit.MILLISECONDS);
    } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } catch (ExecutionException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } catch (TimeoutException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
        BackKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }

        });
    }
    //Setting custom Font
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
