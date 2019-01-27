package soft.deal.best.topline.islamzajel.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ShareCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import soft.deal.best.topline.islamzajel.R;
import soft.deal.best.topline.islamzajel.adapters.ImageSlideAdapter;
import soft.deal.best.topline.islamzajel.bean.Message;
import soft.deal.best.topline.islamzajel.bean.Slider;
import soft.deal.best.topline.islamzajel.json.GetJSONObject;
import soft.deal.best.topline.islamzajel.json.JsonReader;
import soft.deal.best.topline.islamzajel.utils.CheckNetworkConnection;
import soft.deal.best.topline.islamzajel.utils.CirclePageIndicator;
import soft.deal.best.topline.islamzajel.utils.PageIndicator;
import soft.deal.best.topline.islamzajel.utils.TagName;
import com.afollestad.materialdialogs.AlertDialogWrapper;
import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;

import org.json.JSONObject;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.List;

public class SlidShowFragment extends Fragment {
    Typeface custom_font;
    public static final String ARG_ITEM_ID = "home_fragment";
    private static final long ANIM_VIEWPAGER_DELAY = 5000;
    private static final long ANIM_VIEWPAGER_DELAY_USER_VIEW = 10000;

    // UI References
    private ViewPager mViewPager;
    TextView imgNameTxt;
    TextView chooseTitle;
    PageIndicator mIndicator;
    ImageView ShareIcon;
    ImageView PopUpIcon;
    Dialog alertDialog;

    List<Slider> SlidShowMessage;


    RequestImgTask task;
   boolean stopSliding = true;
    String message;

    private Runnable animateViewPager;
    private Handler handler;

    String url = "http://api.islamzajel.com/api/Sliders";
    FragmentActivity activity;
 //  CircularProgressView progressView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
    }

    public static  SlidShowFragment newInstance(String text) {

        SlidShowFragment f = new SlidShowFragment();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int pos) {
            switch(pos) {

             //   case 0: return FirstFragment.newInstance("FirstFragment, Instance 1");
                case 1: return SlidShowFragment.newInstance("FirstFragment, Instance 1");
               default: return SlidShowFragment.newInstance("FirstFragment, Instance 1");
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        findViewById(view);
        custom_font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/AdobeArabic.ttf");
        mIndicator.setOnPageChangeListener(new PageChangeListener());
        mViewPager.setOnPageChangeListener(new PageChangeListener());


        mViewPager.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mViewPager.setPageTransformer(true, new ZoomOutPageTransformer());
                v.getParent().requestDisallowInterceptTouchEvent(true);
                switch (event.getAction()) {

                    case MotionEvent.ACTION_CANCEL:
                        break;

                    case MotionEvent.ACTION_UP:
                        // calls when touch release on ViewPager
                        if (SlidShowMessage != null && SlidShowMessage.size() != 0) {
                            stopSliding = true;
                            runnable(SlidShowMessage.size());
                            handler.postDelayed(animateViewPager,
                                    ANIM_VIEWPAGER_DELAY_USER_VIEW);
                         /*  Fragment  hh = new SlidShowFragment();
                            if(mViewPager.getCurrentItem() == SlidShowMessage.size()-1){
                                FragmentTransaction transaction =getFragmentManager().beginTransaction();
                                transaction.replace(R.id.content_frame,hh);
                               transaction.addToBackStack(null);

// Commit the transaction
                                transaction.commit();

                            mViewPager.setAdapter(new MyPagerAdapter(activity.getSupportFragmentManager()));
                        }*/

                        }
                        break;

                    case MotionEvent.ACTION_MOVE:
                        // calls when ViewPager touch
                        if (handler != null && stopSliding == false) {
                            stopSliding = true;
                            handler.removeCallbacks(animateViewPager);
                        }
                        break;
                }
                return false;
            }
        });

        return view;
    }


    private void findViewById(View view) {
        mViewPager = (ViewPager) view.findViewById(R.id.view_pager);
        mIndicator = (CirclePageIndicator) view.findViewById(R.id.indicator);
        imgNameTxt = (TextView) view.findViewById(R.id.SliderText);
        chooseTitle = (TextView) view.findViewById(R.id.SliderTitle);
        ShareIcon = (ImageView) view.findViewById(R.id.ShareIcon);
        PopUpIcon= (ImageView) view.findViewById(R.id.PopUpIcon);
        /*     progressView = (CircularProgressView) view.findViewById(R.id.progress_view);
        progressView.bringToFront();*/
    }

    public void runnable(final int size) {
        handler = new Handler();
        animateViewPager = new Runnable() {
            public void run() {
                if (!stopSliding) {
                    if (mViewPager.getCurrentItem() == size - 1) {
                        mViewPager.setCurrentItem(0);
                    } else {
                        mViewPager.setCurrentItem(
                                mViewPager.getCurrentItem() + 1, true);
                    }
                    handler.postDelayed(animateViewPager, ANIM_VIEWPAGER_DELAY);
                }
            }
        };
    }




    @Override
    public void onResume() {
        if (SlidShowMessage == null) {
            sendRequest();
        } else {
            mViewPager.setAdapter(new ImageSlideAdapter(activity, SlidShowMessage,
                    SlidShowFragment.this));

            mIndicator.setViewPager(mViewPager);


                imgNameTxt.setText(((Slider) SlidShowMessage.get(mViewPager
                        .getCurrentItem())).getDescription());

            View.OnClickListener clickListener = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ShareCompat.IntentBuilder
                            .from(activity) // getActivity() or activity field if within Fragment
                            .setText(((Slider) SlidShowMessage.get(mViewPager
                                    .getCurrentItem())).getDescription())
                            .setType("text/plain") // most general text sharing MIME type
                            .setChooserTitle("شارك زاجل")
                            .startChooser();
                }
            };
            ShareIcon.setOnClickListener(clickListener);

            View.OnClickListener clickListener2 = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int sdk = android.os.Build.VERSION.SDK_INT;
                    if(sdk < android.os.Build.VERSION_CODES.HONEYCOMB) {
                        android.text.ClipboardManager clipboard = (android.text.ClipboardManager) activity.getSystemService(Context.CLIPBOARD_SERVICE);
                        clipboard.setText(((Slider) SlidShowMessage.get(mViewPager
                                .getCurrentItem())).getDescription());
                        Toast.makeText(activity, "تم نسخ النص",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        android.content.ClipboardManager clipboard = (android.content.ClipboardManager) activity.getSystemService(Context.CLIPBOARD_SERVICE);
                        android.content.ClipData clip = android.content.ClipData.newPlainText("زاجل",((Slider) SlidShowMessage.get(mViewPager
                                .getCurrentItem())).getDescription());
                        clipboard.setPrimaryClip(clip);
                        Toast.makeText(activity, "تم نسخ النص",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            };
            PopUpIcon.setOnClickListener(clickListener2);

            imgNameTxt.setTypeface(custom_font);
            chooseTitle.setTypeface(custom_font);
            runnable(SlidShowMessage.size());
            //Re-run callback
            handler.postDelayed(animateViewPager, ANIM_VIEWPAGER_DELAY);
        }
        super.onResume();
    }


    @Override
    public void onPause() {
        if (task != null)
            task.cancel(true);
        if (handler != null) {
            //Remove callback
            handler.removeCallbacks(animateViewPager);
        }
        super.onPause();
    }

    private void sendRequest() {
        if (CheckNetworkConnection.isConnectionAvailable(activity)) {
            task = new RequestImgTask(activity);
            task.execute(url);
        } else {
            message = getResources().getString(R.string.no_internet_connection);
            showAlertDialog(message, true);
        }
    }

    public void showAlertDialog(String message, final boolean finish) {
        new AlertDialogWrapper.Builder(activity)
                .setMessage(message)
                .setNegativeButton(R.string.OK, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        if(finish){
                            activity.finish();
                        }
                    }
                }).show();
    }

    private class PageChangeListener implements OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int state) {
            if (state == ViewPager.SCROLL_STATE_IDLE) {
                if (SlidShowMessage != null) {


                        imgNameTxt.setText(((Slider) SlidShowMessage.get(mViewPager
                                .getCurrentItem())).getDescription());
                    chooseTitle.setText(((Slider) SlidShowMessage.get(mViewPager
                            .getCurrentItem())).getTitle());
                    imgNameTxt.setTypeface(custom_font);
                    chooseTitle.setTypeface(custom_font);
                }
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(int arg0) {
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
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
        }

        @Override
        protected List<Slider> doInBackground(String... urls) {

            try {
                JSONObject jsonObject = getJsonObject(urls[0]);
                if (jsonObject != null) {
                    int statusCode = jsonObject.getInt(TagName.TAG_STATUS);

                    if (statusCode == 200) {

                        SlidShowMessage = JsonReader.getHomeSliders(jsonObject);

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
         */
        public JSONObject getJsonObject(String url) {
            JSONObject jsonObject = null;
            try {
                jsonObject = GetJSONObject.getJSONObject(url,activity);
            } catch (Exception e) {
e.printStackTrace();
            }
            return jsonObject;
        }

        @Override
        protected void onPostExecute(List<Slider> result) {
            super.onPostExecute(result);
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

                            mViewPager.setAdapter(new ImageSlideAdapter(
                                    activity, SlidShowMessage, SlidShowFragment.this));

                            View.OnClickListener clickListener = new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    ShareCompat.IntentBuilder
                                            .from(activity) // getActivity() or activity field if within Fragment
                                            .setText(( SlidShowMessage.get(mViewPager
                                                    .getCurrentItem())).getDescription())
                                            .setType("text/plain") // most general text sharing MIME type
                                            .setChooserTitle("شارك زاجل")
                                            .startChooser();
                                }
                            };
                            ShareIcon.setOnClickListener(clickListener);


                            View.OnClickListener clickListener2 = new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    int sdk = android.os.Build.VERSION.SDK_INT;
                                    if(sdk < android.os.Build.VERSION_CODES.HONEYCOMB) {
                                        android.text.ClipboardManager clipboard = (android.text.ClipboardManager) activity.getSystemService(Context.CLIPBOARD_SERVICE);
                                        clipboard.setText(( SlidShowMessage.get(mViewPager
                                                .getCurrentItem())).getDescription());
                                        Toast.makeText(activity, "تم نسخ النص",
                                                Toast.LENGTH_SHORT).show();
                                    } else {
                                        android.content.ClipboardManager clipboard = (android.content.ClipboardManager) activity.getSystemService(Context.CLIPBOARD_SERVICE);
                                        android.content.ClipData clip = android.content.ClipData.newPlainText("زاجل",((Slider) SlidShowMessage.get(mViewPager
                                                .getCurrentItem())).getDescription());
                                        clipboard.setPrimaryClip(clip);
                                        Toast.makeText(activity, "تم نسخ النص",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            };
                            PopUpIcon.setOnClickListener(clickListener2);




                            imgNameTxt.setVisibility(View.VISIBLE);
                            chooseTitle.setVisibility(View.VISIBLE);
                            mIndicator.setViewPager(mViewPager);


                                imgNameTxt.setText((SlidShowMessage.get(mViewPager
                                        .getCurrentItem())).getDescription());

                            chooseTitle.setText((SlidShowMessage.get(mViewPager
                                    .getCurrentItem())).getTitle());
                            imgNameTxt.setTypeface(custom_font);
                            chooseTitle.setTypeface(custom_font);

                            runnable(SlidShowMessage.size());
                            handler.postDelayed(animateViewPager,
                                    ANIM_VIEWPAGER_DELAY);
                        } else {
                            imgNameTxt.setVisibility(View.VISIBLE);
                            chooseTitle.setVisibility(View.VISIBLE);
                            imgNameTxt.setText(activity.getResources().getString(R.string.no_search_results));
                            chooseTitle.setText(activity.getResources().getString(R.string.no_search_results));

                            imgNameTxt.setTypeface(custom_font);
                            chooseTitle.setTypeface(custom_font);
                        }
                    }
                    // if server error
                    else {

                        imgNameTxt.setVisibility(View.VISIBLE);
                        chooseTitle.setVisibility(View.VISIBLE);
                        imgNameTxt.setText(activity.getResources().getString(R.string.no_search_results));
                        chooseTitle.setText(activity.getResources().getString(R.string.no_search_results));
                        imgNameTxt.setTypeface(custom_font);
                        chooseTitle.setTypeface(custom_font);
                    }
                }
            }
        }

    }
}
