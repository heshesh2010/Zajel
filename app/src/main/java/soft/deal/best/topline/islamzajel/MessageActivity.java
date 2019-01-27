package soft.deal.best.topline.islamzajel;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import soft.deal.best.topline.islamzajel.adapters.ToBrother_Adapter;
import soft.deal.best.topline.islamzajel.bean.Message;
import soft.deal.best.topline.islamzajel.json.GetJSONObject;
import soft.deal.best.topline.islamzajel.json.JsonReader;
import soft.deal.best.topline.islamzajel.utils.AsyncTaskResult;
import soft.deal.best.topline.islamzajel.utils.TagName;
import soft.deal.best.topline.islamzajel.utils.makeDialog;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MessageActivity extends AppCompatActivity {
    ViewPager viewPager;
    TabLayout tabLayout;
    static Activity mActivity;
    static RecyclerView ToBrotherRecyclerView;
    static RecyclerView ToSisterRecyclerView;
    static int id;
    ImageView BackKey;
    static RelativeLayout mainly;
    private static ProgressBar materialDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_message);
        BackKey= (ImageView) findViewById(R.id.BackKey);
        mainly= (RelativeLayout) findViewById(R.id.mainly);
        materialDialog =(ProgressBar) findViewById(R.id.ProgressBar);

        // Setting CustomFont
        setupViews();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/AdobeArabic-Regular.otf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
        Intent intent = getIntent();
         id = intent.getIntExtra("CurrentBox",0);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        BackKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }

        });
    }


    private void setupViews() {

        viewPager = (ViewPager) findViewById(R.id.htab_viewpager);
        tabLayout = (TabLayout) findViewById(R.id.htab_tabs);
        mActivity = MessageActivity.this;
    }

    //Setting custom Font
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new ToSister(), mActivity.getResources().getString(R.string.ToSister));
        adapter.addFrag(new ToBrother(), mActivity.getResources().getString(R.string.ToBrother));
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(1);
    }

    static class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

    }


    public static class ToBrother extends Fragment {
        int color;
        Typeface custom_font2;
        public ToBrother() {
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            custom_font2 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/AdobeArabic-Bold.otf");
            View rootView = inflater.inflate(R.layout.fragment_to_brother, container, false);

            mActivity=getActivity();
            ////////////////////////***** Setting Default Boxes List to adapter *****//////////////////////
            ToBrotherRecyclerView = (RecyclerView) rootView.findViewById(R.id.ToBrotherRV);
            String BoxesUrl = "http://api.islamzajel.com/api/Categories/3/Messages?CategoryID="+id;
            new GetMessagesAsyncTask(ToBrotherRecyclerView,1).execute(BoxesUrl);

            return rootView;
        }
    }



    public static class ToSister extends Fragment {
        int color;
        Typeface custom_font2;
        public ToSister() {
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            custom_font2 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/AdobeArabic-Bold.otf");
            View rootView = inflater.inflate(R.layout.fragment_to_sister, container, false);


            mActivity=getActivity();
            ////////////////////////***** Setting Default Boxes List to adapter *****//////////////////////
         //   ToSisterRecyclerView = (RecyclerView) rootView.findViewById(R.id.ToSisterRV);
          //  mActivity=getActivity();
            ////////////////////////***** Setting Default Boxes List to adapter *****//////////////////////
            ToBrotherRecyclerView = (RecyclerView) rootView.findViewById(R.id.ToBrotherRV);
            String BoxesUrl = "http://api.islamzajel.com/api/Categories/3/Messages?CategoryID="+id;
            new GetMessagesAsyncTask(ToBrotherRecyclerView,2).execute(BoxesUrl);

            return rootView;
        }
    }

    // Title AsyncTask
    private static class GetMessagesAsyncTask extends AsyncTask<String, Void, AsyncTaskResult<JSONObject>>{
        String message;
        List<Message> mMessages;
        List<Message> NewMessages= new ArrayList();;
        RecyclerView mToBrotherRecyclerView;
        RecyclerView.Adapter mToBrotherAdapter;
        RecyclerView.LayoutManager mToBrotherLayoutManager;
        int sex;

        public GetMessagesAsyncTask( RecyclerView mToBrotherRecyclerView,int sex2) {
            this.mToBrotherRecyclerView=mToBrotherRecyclerView;
            sex=sex2;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mToBrotherRecyclerView = ToBrotherRecyclerView;
            materialDialog.setVisibility(View.VISIBLE);

        }

        @Override
        protected  AsyncTaskResult<JSONObject> doInBackground(String... urls) {
            try {
                JSONObject jsonObject = getJsonObject(urls[0]);
                if (jsonObject != null) {
                    int statusCode = jsonObject.getInt(TagName.TAG_STATUS);

                    if (statusCode == 200) {

                        mMessages = JsonReader.getHomeMessages(jsonObject);

                    } else {
                        message = jsonObject.getString(TagName.TAG_DATA);
                    }
                }
                return new AsyncTaskResult<JSONObject>(jsonObject);
            } catch (Exception e) {
                e.printStackTrace();
                return new AsyncTaskResult<JSONObject>(e);
            }
        }

        public JSONObject getJsonObject(String url) {
            JSONObject jsonObject = null;
            try {
                jsonObject = GetJSONObject.getJSONObject(url,mActivity);
            } catch (Exception e) {
            }
            return jsonObject;
        }

        @Override
        protected void onPostExecute(AsyncTaskResult<JSONObject> result) {
            super.onPostExecute(result);
            materialDialog.setVisibility(View.INVISIBLE);

            if (result.getError() != null) {
                new makeDialog().makeDialog(mActivity,mActivity.getResources().getString(R.string.error_occured));
            } else if (isCancelled()) {
                new makeDialog().makeDialog(mActivity,mActivity.getResources().getString(R.string.cancel_occured));
            }
            else if (mMessages==null){
                new makeDialog().makeDialog(mActivity,mActivity.getResources().getString(R.string.no_search_results));
            }

            else if(mMessages.isEmpty()||mMessages==null) {

                new makeDialog().makeDialog(mActivity,mActivity.getResources().getString(R.string.no_search_results));
            }
            else {

                for(int i=0;i<mMessages.size();i++){
                    if(i%3==0)
                    {
                        NewMessages.add(null);
                    }
                    NewMessages.add(mMessages.get(i));
                }
                ////////////////////////***** Setting Default Boxes List to adapter *****//////////////////////
                mToBrotherLayoutManager = new LinearLayoutManager(mActivity);
                mToBrotherRecyclerView.setHasFixedSize(true);
                mToBrotherRecyclerView.setLayoutManager(mToBrotherLayoutManager);
                mToBrotherAdapter = new ToBrother_Adapter(mActivity, new ArrayList<Message>(NewMessages), ToBrotherRecyclerView,sex, mainly);
                mToBrotherRecyclerView.setAdapter(mToBrotherAdapter);
                mToBrotherAdapter.notifyDataSetChanged();
            }
        }
    }

}
