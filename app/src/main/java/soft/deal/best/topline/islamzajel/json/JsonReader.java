package soft.deal.best.topline.islamzajel.json;



import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import soft.deal.best.topline.islamzajel.bean.Boxes;
import soft.deal.best.topline.islamzajel.bean.Message;
import soft.deal.best.topline.islamzajel.bean.Slider;
import soft.deal.best.topline.islamzajel.utils.TagName;

public class JsonReader {



    public static ArrayList<Slider> getHomeSliders(JSONObject jsonObject)
            throws JSONException {
        ArrayList<Slider> mSlider = new ArrayList<Slider>();
        JSONArray jsonArray = jsonObject.getJSONArray(TagName.TAG_PRODUCTS);
        Slider mSliderClass;
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject SliderObj = jsonArray.getJSONObject(i);

            mSliderClass = new Slider();


            if (!JSONObject.NULL.equals(SliderObj.get(TagName.KEY_Description))) {
                mSliderClass.setDescription(SliderObj.getString(TagName.KEY_Description));
            }

            if (!JSONObject.NULL.equals(SliderObj.get(TagName.KEY_Title))) {
                mSliderClass.setTitle(SliderObj.getString(TagName.KEY_Title));
            }

            mSlider.add(mSliderClass);
        }



        return mSlider;
    }



    public static ArrayList<Slider> getHomeSliders2(JSONObject jsonObject)
            throws JSONException {
        ArrayList<Slider> mSlider = new ArrayList<Slider>();

        Slider mSliderClass;

            mSliderClass = new Slider();


            if (!JSONObject.NULL.equals(jsonObject.getJSONObject(TagName.TAG_PRODUCTS).getString(TagName.KEY_Description))) {
                mSliderClass.setDescription(jsonObject.getJSONObject(TagName.TAG_PRODUCTS).getString(TagName.KEY_Description));
            }

            if (!JSONObject.NULL.equals(jsonObject.getJSONObject(TagName.TAG_PRODUCTS).getString(TagName.KEY_Title))) {
                mSliderClass.setTitle(jsonObject.getJSONObject(TagName.TAG_PRODUCTS).getString(TagName.KEY_Title));
            }

            mSlider.add(mSliderClass);




        return mSlider;
    }

    public static ArrayList<Message> getHomeMessages(JSONObject jsonObject)
            throws JSONException {
        ArrayList<Message> mMessage = new ArrayList<Message>();
        JSONArray jsonArray = jsonObject.getJSONArray(TagName.TAG_PRODUCTS);
        Message mMessageClass;
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject MessageObj = jsonArray.getJSONObject(i);

            mMessageClass = new Message();

            if (!JSONObject.NULL.equals(MessageObj.get(TagName.KEY_MessageID))) {
                mMessageClass.setMessageID(MessageObj.getInt(TagName.KEY_MessageID));
            }

            if (!JSONObject.NULL.equals(MessageObj.get(TagName.KEY_CategoryID))) {
                mMessageClass.setCategoryID(MessageObj.getInt(TagName.KEY_CategoryID));
            }

            if (!JSONObject.NULL.equals(MessageObj.get(TagName.KEY_Name))) {
                mMessageClass.setName(MessageObj.getString(TagName.KEY_Name));
            }

            if (!JSONObject.NULL.equals(MessageObj.get(TagName.KEY_Description))) {
                mMessageClass.setDescription(MessageObj.getString(TagName.KEY_Description));
            }

            if (!JSONObject.NULL.equals(MessageObj.get(TagName.KEY_DescriptionFemale))) {
                mMessageClass.setDescriptionFemale(MessageObj.getString(TagName.KEY_DescriptionFemale));
            }

            if (!JSONObject.NULL.equals(MessageObj.get(TagName.KEY_SendCount))) {
                mMessageClass.setSendCount(MessageObj.getInt(TagName.KEY_SendCount));
            }

            if (!JSONObject.NULL.equals(MessageObj.get(TagName.KEY_FemaleSendCount))) {
                mMessageClass.setFemaleSendCount(MessageObj.getInt(TagName.KEY_FemaleSendCount));
            }
            mMessage.add(mMessageClass);
        }



        return mMessage;
    }




    public static ArrayList<Boxes> getHomeBoxes(JSONObject jsonObject)
            throws JSONException {
        ArrayList<Boxes> mBoxes = new ArrayList<Boxes>();
        JSONArray jsonArray = jsonObject.getJSONArray(TagName.TAG_PRODUCTS);
        Boxes mBoxesClass;
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject BoxesObj = jsonArray.getJSONObject(i);

            mBoxesClass = new Boxes();


            if (!JSONObject.NULL.equals(BoxesObj.get(TagName.KEY_CategoryID))) {
                mBoxesClass.setCategoryID(BoxesObj.getInt(TagName.KEY_CategoryID));
            }

            if (!JSONObject.NULL.equals(BoxesObj.get(TagName.KEY_Name))) {
                mBoxesClass.setName(BoxesObj.getString(TagName.KEY_Name));
            }

            mBoxes.add(mBoxesClass);
        }



        return mBoxes;
    }


}
