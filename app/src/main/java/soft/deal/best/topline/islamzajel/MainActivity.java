package soft.deal.best.topline.islamzajel;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.AlertDialogWrapper;
import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.balysv.materialripple.MaterialRippleLayout;

import org.json.JSONObject;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import soft.deal.best.topline.islamzajel.adapters.ImageSlideAdapter;
import soft.deal.best.topline.islamzajel.bean.Slider;
import soft.deal.best.topline.islamzajel.fragment.*;
import soft.deal.best.topline.islamzajel.json.GetJSONObject;
import soft.deal.best.topline.islamzajel.json.JsonReader;
import soft.deal.best.topline.islamzajel.utils.CheckNetworkConnection;
import soft.deal.best.topline.islamzajel.utils.TagName;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {


    Button contactUsButton1;
    Button dotsButton1;
    Button smsButton1;
    Button smileButton1;
    Button clothsButton1;
    Button giftButton1;
    Activity mActivity;
    SlidShowFragment SlidShowFragment;
    Fragment contentFragment;
    TextView imgNameTxt;
    TextView chooseTitle;
  //  TextView LogoText;
    TextView LogoText2;
    List<Slider> SlidShowMessage;
    String message;
    RequestImgTask task;
    String url = "http://api.islamzajel.com/api/Sliders2";
    private ProgressBar materialDialog;
    ImageView Logo3;
    RelativeLayout mainly;
    FrameLayout content_frame;
    PopupWindow popupWindow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mActivity=MainActivity.this;
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/AdobeArabic.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
         String android_id = Settings.Secure.getString(mActivity.getContentResolver(),
                Settings.Secure.ANDROID_ID);
      //  Typeface font = Typeface.createFromAsset(MainActivity.this.getAssets(), "fonts/zahra-bold.ttf");
        Typeface font2 = Typeface.createFromAsset(MainActivity.this.getAssets(), "fonts/zahra.ttf");
     //   LogoText = (TextView) findViewById(R.id.LogoText);
        LogoText2 = (TextView) findViewById(R.id.LogoText2);
      //  LogoText.setTypeface(font2);
                LogoText2.setTypeface(font2);
        contactUsButton1 = (Button) findViewById(R.id.contactUsButton1);
        dotsButton1 = (Button) findViewById(R.id.dotsButton1);
        smsButton1 = (Button) findViewById(R.id.smsButton1);
        smileButton1 = (Button) findViewById(R.id.smileButton1);
        giftButton1 = (Button) findViewById(R.id.giftButton1);
        imgNameTxt = (TextView) findViewById(R.id.Slider2Text);
        chooseTitle = (TextView) findViewById(R.id.Slider2Title);
        clothsButton1= (Button) findViewById(R.id.clothsButton1);
        Logo3= (ImageView) findViewById(R.id.Logo3);
        mainly= (RelativeLayout) findViewById(R.id.mainly);
        materialDialog =(ProgressBar) findViewById(R.id.ProgressBar);
        content_frame= (FrameLayout) findViewById(R.id.content_frame);

        sendRequest();

        Logo3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mainly.setAlpha(0.1f);
                LayoutInflater layoutInflater
                        = (LayoutInflater) mActivity
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View popupView = layoutInflater.inflate(R.layout.popuplogo, null);
              popupWindow = new PopupWindow(
                        popupView,
                        RecyclerView.LayoutParams.MATCH_PARENT,
                        RecyclerView.LayoutParams.MATCH_PARENT);

                popupWindow.getContentView().setOnKeyListener(new View.OnKeyListener() {
                    @Override
                    public boolean onKey(View v, int keyCode, KeyEvent event) {

                        if (keyCode == KeyEvent.KEYCODE_BACK) {
                            popupWindow.dismiss();
                            mainly.setAlpha(1.0f);
                            return true;
                        }
                        return false;
                    }
                });

                Button btnDismiss = (Button) popupView.findViewById(R.id.dismiss);


                popupWindow.showAsDropDown(popupView, Gravity.CENTER, 0, 0);
                popupWindow.setOutsideTouchable(true);

                btnDismiss.setOnClickListener(new Button.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        popupWindow.dismiss();
                        mainly.setAlpha(1.0f);

                    }
                });
            }
        });

        smsButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(mActivity, AddMessage.class);
                mActivity.startActivity(mainIntent);
            }
        });


        contactUsButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(mActivity, ContactUsActivity.class);
                mActivity.startActivity(mainIntent);
            }
        });

        dotsButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(mActivity, BoxesActivity.class);
                mActivity.startActivity(mainIntent);
            }
        });

        smileButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(mActivity, DonationActivity.class);
                mActivity.startActivity(mainIntent);
            }
        });

        clothsButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(mActivity, Order1Activity.class);
                mActivity.startActivity(mainIntent);
            }
        });

        giftButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(mActivity, Order2Activity.class);
                mActivity.startActivity(mainIntent);
            }
        });

        FragmentManager fragmentManager = getSupportFragmentManager();
        if (savedInstanceState != null) {
            if (fragmentManager.findFragmentByTag(soft.deal.best.topline.islamzajel.fragment.SlidShowFragment.ARG_ITEM_ID) != null) {
                SlidShowFragment = (SlidShowFragment) fragmentManager
                        .findFragmentByTag(soft.deal.best.topline.islamzajel.fragment.SlidShowFragment.ARG_ITEM_ID);
                contentFragment = SlidShowFragment;
            }
        } else {
            SlidShowFragment = new SlidShowFragment();
            switchContent(SlidShowFragment, soft.deal.best.topline.islamzajel.fragment.SlidShowFragment.ARG_ITEM_ID);
        }
    }

    public void showAlertDialog(String message, final boolean finish) {
        new AlertDialogWrapper.Builder(mActivity)
                .setMessage(message)
                .setNegativeButton(R.string.OK, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        if(finish){
                            mActivity.finish();
                        }
                    }
                }).show();
    }

    private void sendRequest() {
        if (CheckNetworkConnection.isConnectionAvailable(mActivity)) {
            task = new RequestImgTask(mActivity);
            task.execute(url);
            try {
                task.get(15000, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        } else {
            message = getResources().getString(R.string.no_internet_connection);
            showAlertDialog(message, true);
        }
    }

    @Override
    public void onBackPressed() {
        if(popupWindow!=null&&popupWindow.isShowing()){
            popupWindow.dismiss();
            mainly.setAlpha(1.0f);
        }
        else{
            finish();
        }
    }

    private class RequestImgTask extends AsyncTask<String, Void, List<Slider>> {
        private final WeakReference<Activity> activityWeakRef;
        Throwable error;

        public RequestImgTask(Activity context) {
            this.activityWeakRef = new WeakReference<Activity>(context);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            imgNameTxt.setVisibility(View.INVISIBLE);
            chooseTitle.setVisibility(View.INVISIBLE);
            materialDialog.setVisibility(View.VISIBLE);

        }

        @Override
        protected List<Slider> doInBackground(String... urls) {

            try {
                JSONObject jsonObject = getJsonObject(urls[0]);
                if (jsonObject != null) {
                    int statusCode = jsonObject.getInt(TagName.TAG_STATUS);

                    if (statusCode == 200) {

                        SlidShowMessage = JsonReader.getHomeSliders2(jsonObject);

                    } else {
                        message = jsonObject.getString(TagName.TAG_DATA);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return SlidShowMessage;
        }

        /**
         * It returns jsonObject for the specified url.
         *
         * @param url
         * @return JSONObject
         */
        public JSONObject getJsonObject(String url) {
            JSONObject jsonObject = null;
            try {
                jsonObject = GetJSONObject.getJSONObject(url,mActivity);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return jsonObject;
        }

        @Override
        protected void onPostExecute(List<Slider> result) {
            super.onPostExecute(result);
            materialDialog.setVisibility(View.INVISIBLE);

            if (!activityWeakRef.get().isFinishing()) {
                if (error != null && error instanceof IOException) {
                    message = getResources().getString(R.string.time_out);
                    showAlertDialog(message, true);
                } else if (error != null) {
                    message = getResources().getString(R.string.error_occured);
                    showAlertDialog(message, true);
                } else {
                    SlidShowMessage = result;
                    if (result != null) {
                        if (SlidShowMessage.size() != 0) {


                            imgNameTxt.setVisibility(View.VISIBLE);
                            chooseTitle.setVisibility(View.VISIBLE);


                            imgNameTxt.setText(((Slider)SlidShowMessage.get(0)).getDescription());

                            chooseTitle.setText(((Slider) SlidShowMessage.get(0)).getTitle());

                        } else {
                            imgNameTxt.setVisibility(View.VISIBLE);
                            chooseTitle.setVisibility(View.VISIBLE);
                            imgNameTxt.setText(mActivity.getResources().getString(R.string.no_search_results));
                            chooseTitle.setText(mActivity.getResources().getString(R.string.no_search_results));

                        }
                    }
                    // if server error
                    else {

                        imgNameTxt.setVisibility(View.VISIBLE);
                        chooseTitle.setVisibility(View.VISIBLE);
                        imgNameTxt.setText(mActivity.getResources().getString(R.string.no_search_results));
                        chooseTitle.setText(mActivity.getResources().getString(R.string.no_search_results));

                    }
                }
            }
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (contentFragment instanceof SlidShowFragment) {
            outState.putString("content", soft.deal.best.topline.islamzajel.fragment.SlidShowFragment.ARG_ITEM_ID);
        }
        super.onSaveInstanceState(outState);
    }

    public void switchContent(Fragment fragment, String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        //   while (fragmentManager.popBackStackImmediate()) ;

        if (fragment != null) {
            FragmentTransaction transaction = fragmentManager
                    .beginTransaction();
            transaction.replace(R.id.content_frame, fragment, tag);
            // Only ProductDetailFragment is added to the back2 stack.
            if (!(fragment instanceof SlidShowFragment)) {
                transaction.addToBackStack(tag);
            }
            transaction.commit();
            contentFragment = fragment;
        }
    }


}