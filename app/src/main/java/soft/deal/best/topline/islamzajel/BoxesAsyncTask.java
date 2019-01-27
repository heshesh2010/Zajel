package soft.deal.best.topline.islamzajel;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;

import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import soft.deal.best.topline.islamzajel.adapters.Boxes_Adapter;
import soft.deal.best.topline.islamzajel.bean.Boxes;
import soft.deal.best.topline.islamzajel.json.GetJSONObject;
import soft.deal.best.topline.islamzajel.json.JsonReader;
import soft.deal.best.topline.islamzajel.utils.AsyncTaskResult;
import soft.deal.best.topline.islamzajel.utils.TagName;
import soft.deal.best.topline.islamzajel.utils.makeDialog;

/**
 * Created by hashoma on 6/12/2016.
 */
public class BoxesAsyncTask extends AsyncTask<String, Void, AsyncTaskResult<JSONObject>> {


        String message;
        List<Boxes> mBoxes;
        Activity mActivity;
        RecyclerView mBoxesRecyclerView;
        RecyclerView.Adapter mBoxesAdapter;
        RecyclerView.LayoutManager mBoxesLayoutManager;

    private ProgressBar materialDialog;

        public BoxesAsyncTask(Activity mActivity, RecyclerView mBoxesRecyclerView) {
            this.mActivity = mActivity;
            this.mBoxesRecyclerView=mBoxesRecyclerView;
            materialDialog =(ProgressBar) mActivity.findViewById(R.id.ProgressBar);

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            materialDialog.setVisibility(View.VISIBLE);



        }

        @Override
        protected AsyncTaskResult<JSONObject> doInBackground(String... urls) {

            try {
                JSONObject jsonObject = getJsonObject(urls[0]);
                if (jsonObject != null) {
                    int statusCode = jsonObject.getInt(TagName.TAG_STATUS);

                    if (statusCode == 200) {

                        mBoxes = JsonReader.getHomeBoxes(jsonObject);


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
            else if (mBoxes==null){
                new makeDialog().makeDialog(mActivity,mActivity.getResources().getString(R.string.no_search_results));
            }

            else if(mBoxes.isEmpty()||mBoxes==null) {

                new makeDialog().makeDialog(mActivity,mActivity.getResources().getString(R.string.no_search_results));
            }
            else {
                ////////////////////////***** Setting Default Boxes List to adapter *****//////////////////////
                mBoxesLayoutManager = new LinearLayoutManager(mActivity);
                mBoxesRecyclerView.setHasFixedSize(true);
                mBoxesRecyclerView.setLayoutManager(mBoxesLayoutManager);
                mBoxesAdapter = new Boxes_Adapter(mActivity, new ArrayList<Boxes>(mBoxes), mBoxesRecyclerView);
                mBoxesRecyclerView.setAdapter(mBoxesAdapter);
                mBoxesAdapter.notifyDataSetChanged();
            }
        }
    }

