package soft.deal.best.topline.islamzajel.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hashoma on 6/12/2016.
 */
public class Message implements Parcelable{


    private String Name;

    private int SendCount;

    private String Description;

    private int CategoryID;

    private int MessageID;

    private int FemaleSendCount;

    private String DescriptionFemale;

    public Message() {
    }

    public Message(Parcel in) {
        Name = in.readString();
        SendCount = in.readInt();
        Description = in.readString();
        CategoryID = in.readInt();
        MessageID = in.readInt();
        FemaleSendCount = in.readInt();
        DescriptionFemale = in.readString();
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
        dest.writeString(Name);
        dest.writeInt(SendCount);
        dest.writeString(Description);
        dest.writeInt(CategoryID);
        dest.writeInt(MessageID);
        dest.writeInt(FemaleSendCount);
        dest.writeString(DescriptionFemale);
    }


    public String getName ()
    {
        return Name;
    }

    public void setName (String Name)
    {
        this.Name = Name;
    }

    public int getSendCount ()
    {
        return SendCount;
    }

    public void setSendCount (int SendCount)
    {
        this.SendCount = SendCount;
    }


    public String getDescription ()
    {
        return Description;
    }

    public void setDescription (String Description)
    {
        this.Description = Description;
    }

    public int getCategoryID ()
    {
        return CategoryID;
    }

    public void setCategoryID (int CategoryID)
    {
        this.CategoryID = CategoryID;
    }

    public int getMessageID ()
    {
        return MessageID;
    }

    public void setMessageID (int MessageID)
    {
        this.MessageID = MessageID;
    }

    public int getFemaleSendCount ()
    {
        return FemaleSendCount;
    }

    public void setFemaleSendCount (int FemaleSendCount)
    {
        this.FemaleSendCount = FemaleSendCount;
    }

    public String getDescriptionFemale ()
    {
        return DescriptionFemale;
    }

    public void setDescriptionFemale (String DescriptionFemale)
    {
        this.DescriptionFemale = DescriptionFemale;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Name = "+Name+", SendCount = "+SendCount+", Description = "+Description+", CategoryID = "+CategoryID+", MessageID = "+MessageID+", FemaleSendCount = "+FemaleSendCount+", DescriptionFemale = "+DescriptionFemale+"]";
    }



}
