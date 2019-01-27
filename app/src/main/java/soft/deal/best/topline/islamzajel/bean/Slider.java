package soft.deal.best.topline.islamzajel.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hashoma on 6/12/2016.
 */
public class Slider implements Parcelable{


    private String Description;

    private int SliderType;

    private String Title;

    private int SliderID;


    public Slider() {
    }

    public Slider(Parcel in) {
        Description = in.readString();
        SliderType = in.readInt();
        Title = in.readString();
        SliderID = in.readInt();
    }

    public static final Creator<Message> CREATOR = new Creator<Message>() {
        @Override
        public Message createFromParcel(Parcel in) {
            return new Message(in);
        }

        @Override
        public Message[] newArray(int size) {
            return new Message[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Description);
        dest.writeInt(SliderType);
        dest.writeString(Title);
        dest.writeInt(SliderID);
    }


    public String getDescription ()
    {
        return Description;
    }

    public void setDescription (String Description)
    {
        this.Description = Description;
    }

    public int getSliderType ()
    {
        return SliderType;
    }

    public void setSliderType (int SliderType)
    {
        this.SliderType = SliderType;
    }

    public String getTitle ()
    {
        return Title;
    }

    public void setTitle (String Title)
    {
        this.Title = Title;
    }

    public int getSliderID ()
    {
        return SliderID;
    }

    public void setSliderID (int SliderID)
    {
        this.SliderID = SliderID;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Description = "+Description+", SliderType = "+SliderType+", Title = "+Title+", SliderID = "+SliderID+"]";
    }

}
