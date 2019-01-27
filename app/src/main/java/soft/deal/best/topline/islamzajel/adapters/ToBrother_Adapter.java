package soft.deal.best.topline.islamzajel.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import com.google.gson.Gson;
import com.mobily.api.sms.utility.MobilyAPI;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.util.ArrayList;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.util.EntityUtils;

import soft.deal.best.topline.islamzajel.R;
import soft.deal.best.topline.islamzajel.bean.Message;
import soft.deal.best.topline.islamzajel.utils.makeDialog;

import com.mobily.api.sms.utility.OnDataReceiveListner;

/**
 * Created by hashoma on 6/12/2016.
 */
public class ToBrother_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private ArrayList<Message> mMessages;
    private Activity mActivity;
    private RecyclerView mRecyclerView;
    Typeface custom_font;
    final MobilyAPI api;
    private ProgressBar materialDialog;
    int sex;
    RelativeLayout mainly;
     int AD_TYPE=1;
    int CONTENT_TYPE=0;
     PopupWindow popupWindow;
    public ToBrother_Adapter(Activity context, ArrayList<Message> mMessages, RecyclerView rv,int sex2,RelativeLayout mainly2) {
        this.mMessages = mMessages;
        this.mActivity = context;
        mRecyclerView = rv;
        sex=sex2;
        mainly=mainly2;
       api = new MobilyAPI(mActivity, "islamzajel", "i1slam2zaje1l");// mustCreateAnObjectForUsingMobily.wsServices
        MobileAds.initialize(mActivity, "ca-app-pub-9127286221713252~7854386523");
        materialDialog =(ProgressBar) mActivity.findViewById(R.id.ProgressBar);
        custom_font = Typeface.createFromAsset(mActivity.getAssets(), "fonts/AdobeArabic-Regular.otf");


    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        View v = null;
        if (viewType == AD_TYPE)
        {
            v = LayoutInflater.from(mActivity).inflate(R.layout.message_row_item1, null);
            viewHolder = new CustomViewHolderAdMob(v);

        }
        else {
            v = LayoutInflater.from(mActivity).inflate(R.layout.message_row_item, null);
            viewHolder = new CustomViewHolderPackages(v);
        }

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final  int position) {


        switch(getItemViewType(holder.getAdapterPosition())){
            case 0:{
                CustomViewHolderPackages viewHolder = (CustomViewHolderPackages) holder;


        //Brother
        if(sex==1){
            viewHolder.Message.setText(mMessages.get(position).getDescription());
            viewHolder.sentCountNumber.setText(String.valueOf(mMessages.get(position).getSendCount()));
        }
        else{
            viewHolder.Message.setText(mMessages.get(position).getDescriptionFemale());
            viewHolder.sentCountNumber.setText(String.valueOf(mMessages.get(position).getFemaleSendCount()));
        }



        final Message CurrentBox = mMessages.get(position);

        final  View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                mainly.setAlpha(0.1f);
                LayoutInflater layoutInflater
                        = (LayoutInflater) mActivity
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View popupView = layoutInflater.inflate(R.layout.popup, null);
               popupWindow = new PopupWindow(
                        popupView,
                        RecyclerView.LayoutParams.MATCH_PARENT,
                        RecyclerView.LayoutParams.MATCH_PARENT);
                popupWindow.setFocusable(true);
                popupWindow.update();

                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        mainly.setAlpha(1.0f);
                    }
                });

                Button sendSMS = (Button) popupView.findViewById(R.id.sendSMS);

                final EditText phoneNumberET = (EditText) popupView.findViewById(R.id.phoneNumberET);

                Button btnDismiss = (Button) popupView.findViewById(R.id.dismiss);

                btnDismiss.setOnClickListener(new Button.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        mainly.setAlpha(1.0f);
                        popupWindow.dismiss();
                    }
                });



                sendSMS.setOnClickListener(new Button.OnClickListener() {

                    @Override
                    public void onClick(View v) {


                        materialDialog.setVisibility(View.VISIBLE);


                        mainly.setAlpha(1.0f);
                        //Brother
                        if(sex==1){
                            // --------------------------------------------SendMessageAPI---------------------------------------
                            api.sendMessage("islamzajel", mMessages.get(position).getDescription(), phoneNumberET.getText().toString(),
                                    "0", "0", "0", "islamzajel.com",
                                    new OnDataReceiveListner() {

                                        @Override
                                        public void onSuccess(Object data) {
                                            // TODO Auto-generated method stub

                                           /* CommonResponseData commonResponseData = (CommonResponseData) data;
                                            commonResponseData.getMessageAr();
                                            commonResponseData.getMessageEn();
                                            commonResponseData.getResult();*/
                                            //showMessage(new Gson().toJson(commonResponseData));
                                            showMessage("تم إرسالها بنجاح ، نسأل الله أن ينفع بها ، جزاك الله خير");                                             //   showMessage(new Gson().toJson(error));

                                            String BoxesUrl = "http://api.islamzajel.com/api/Messages/"+mMessages.get(position).getMessageID();
                                            new MessageAsyncTaskPost(mActivity,BoxesUrl).execute();
                                        }

                                        @Override
                                        public void onFailure(Object data) {
                                            // TODO Auto-generated method stub
                                            if (data != null) {

                                                com.mobily.api.sms.entity.Error error = (com.mobily.api.sms.entity.Error) data;
                                                error.getErrorCode();
                                                error.getMessageAr();
                                                error.getMessageEn();
                                                  showMessage(new Gson().toJson(error));
                                               // showMessage("تم إرسالها بنجاح ، نسأل الله أن ينفع بها ، جزاك الله خير");                                             //   showMessage(new Gson().toJson(error));
                                                materialDialog.setVisibility(View.INVISIBLE);

                                            } else {
                                                // Unexpected Exception !!...
                                                //  showMessage(getString(R.string.service_not_available_en));

                                            }

                                        }
                                    });// end Send Message API

                        }
                        else{
                            // --------------------------------------------SendMessageAPI---------------------------------------
                            api.sendMessage("islamzajel", mMessages.get(position).getDescriptionFemale(), phoneNumberET.getText().toString(),
                                    "", "", "", "islamzajel.com",
                                    new OnDataReceiveListner() {

                                        @Override
                                        public void onSuccess(Object data) {
                                            // TODO Auto-generated method stub

                                        /*    CommonResponseData commonResponseData = (CommonResponseData) data;
                                            commonResponseData.getMessageAr();
                                            commonResponseData.getMessageEn();
                                            commonResponseData.getResult();*/
                                            // showMessage(new Gson().toJson(commonResponseData));
                                            showMessage("تم إرسالها بنجاح ، نسأل الله أن ينفع بها ، جزاك الله خير");                                             //   showMessage(new Gson().toJson(error));
                                            String BoxesUrl = "http://api.islamzajel.com/api/FemaleMessages/"+mMessages.get(position).getMessageID();
                                            new MessageAsyncTaskPost(mActivity,BoxesUrl).execute();
                                        }

                                        @Override
                                        public void onFailure(Object data) {
                                            // TODO Auto-generated method stub
                                            if (data != null) {

                                                com.mobily.api.sms.entity.Error error = (com.mobily.api.sms.entity.Error) data;
                                                error.getErrorCode();
                                                error.getMessageAr();
                                                error.getMessageEn();
                                                 showMessage(new Gson().toJson(error));
                                                //showMessage("تم إرسالها بنجاح ، نسأل الله أن ينفع بها ، جزاك الله خير");                                             //   showMessage(new Gson().toJson(error));

                                                materialDialog.setVisibility(View.INVISIBLE);


                                            } else {
                                                // Unexpected Exception !!...
                                               //   showMessage(R.string.service_not_available_en));

                                            }

                                        }
                                    });// end Send Message API
                        }
                        popupWindow.dismiss();

                    }
                });


                popupWindow.showAsDropDown(popupView, Gravity.CENTER, 0, 0);

            };


        };

                viewHolder.Send.setOnClickListener(clickListener);
                break;
            }
            case 1:{


                break;
            }
        }
    }



    private void showMessage(String msg) {

        Toast.makeText(mActivity, msg, Toast.LENGTH_LONG).show();

    }

    @Override
    public int getItemCount() {
        return (null != mMessages ? mMessages.size() : 0);
    }

    @Override
    public int getItemViewType(int position)
    {
        if(mMessages.get(position)==null)
            return AD_TYPE;
        return CONTENT_TYPE;
    }



    public class CustomViewHolderPackages extends RecyclerView.ViewHolder {

        final Button Send;
        protected TextView Message;
        protected TextView sentCountNumber;


        public CustomViewHolderPackages(View view) {
            super(view);

            this.Send = (Button) view.findViewById(R.id.Send);
            this.Message= (TextView) view.findViewById(R.id.Message);
            this.sentCountNumber= (TextView) view.findViewById(R.id.sentCountNumber);
            this.Message.setTypeface(custom_font);
            this.sentCountNumber.setTypeface(custom_font);
        }

    }



    public class CustomViewHolderAdMob extends RecyclerView.ViewHolder {




        public CustomViewHolderAdMob(View view) {
            super(view);
            AdView adView = (AdView) view.findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder().build();
            adView.loadAd(adRequest);

        }

    }

    public class MessageAsyncTaskPost extends AsyncTask<String, Void, String> {

        HttpURLConnection httpcon;
        String url = null;
        String data = null;
        URLConnection urlConn = null;
        DataOutputStream printout;
        DataInputStream input;
        String results = null;
        Activity activity;

        String commentText;


        public MessageAsyncTaskPost(Activity context, String url) {
            this.activity = context;
            this.url = url;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            materialDialog.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... urls) {

            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(url);
            post.setHeader("Content-type", "application/json");
            post.setHeader("Accept", "application/json");

            try {

                HttpResponse response = client.execute(post);
                HttpEntity entity = response.getEntity();
                results = EntityUtils.toString(entity);

            } catch (IOException e) {
                e.printStackTrace();
            }

            return results;
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            materialDialog.setVisibility(View.INVISIBLE);
// like state
            popupWindow.dismiss();

            if (result.contains("201")) {

            }
            // dislike
            else if (result.contains("500")) {
                new makeDialog().makeDialog(activity,activity.getResources().getString(R.string.error_occured));
            } else {
                Log.d("error", result);
                new makeDialog().makeDialog(activity,activity.getResources().getString(R.string.error_occured));
            }

        }
    }
}
