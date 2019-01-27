package soft.deal.best.topline.islamzajel.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hashoma on 6/12/2016.
 */
public class Boxes  implements Parcelable{


    private String Name;


    private int CategoryID;




    public Boxes() {
    }

    public Boxes(Parcel in) {
        Name = in.readString();
        CategoryID = in.readInt();
    }

    public static final Creator<Boxes> CREATOR = new Creator<Boxes>() {
        @Override
        public Boxes createFromParcel(Parcel in) {
            return new Boxes(in);
        }

        @Override
        public Boxes[] newArray(int size) {
            return new Boxes[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Name);
        dest.writeInt(CategoryID);
    }


    public String getName ()
    {
        return Name;
    }

    public void setName (String Name)
    {
        this.Name = Name;
    }

    public int getCategoryID ()
    {
        return CategoryID;
    }

    public void setCategoryID (int CategoryID)
    {
        this.CategoryID = CategoryID;
    }


    @Override
    public String toString()
    {
        return "ClassPojo [Name = "+Name+", CategoryID = "+CategoryID+"]";
    }



}
