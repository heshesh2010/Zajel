package soft.deal.best.topline.islamzajel.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import soft.deal.best.topline.islamzajel.MessageActivity;
import soft.deal.best.topline.islamzajel.R;
import soft.deal.best.topline.islamzajel.bean.Boxes;

/**
 * Created by hashoma on 6/12/2016.
 */
public class Boxes_Adapter extends RecyclerView.Adapter<Boxes_Adapter.CustomViewHolderPackages>{

    private ArrayList<Boxes> mBoxes;
    private Context mActivity;
    private RecyclerView mRecyclerView;
    Typeface custom_font;
    public Boxes_Adapter(Context context, ArrayList<Boxes> mBoxes, RecyclerView rv) {
        this.mBoxes = mBoxes;
        this.mActivity = context;
        mRecyclerView = rv;
        custom_font = Typeface.createFromAsset(mActivity.getAssets(), "fonts/AdobeArabic-Bold.otf");
    }

    @Override
    public CustomViewHolderPackages onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.box_row_item, null);
        return new CustomViewHolderPackages(view);
    }

    @Override
    public void onBindViewHolder(final Boxes_Adapter.CustomViewHolderPackages holder, int position) {


           holder.BoxTitle.setText(mBoxes.get(position).getName());
        final Boxes CurrentBox = mBoxes.get(position);

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mActivity, MessageActivity.class);
                i.putExtra("CurrentBox", CurrentBox.getCategoryID());
                mActivity.startActivity(i);
            }
        };
        holder.toolbar.setOnClickListener(clickListener);
    }



    @Override
    public int getItemCount() {
        return (null != mBoxes ? mBoxes.size() : 0);
    }


    public class CustomViewHolderPackages extends RecyclerView.ViewHolder{

        protected ImageView BoxButton;
        protected TextView BoxTitle;
        protected RelativeLayout toolbar;
        public CustomViewHolderPackages(View view) {
            super(view);

            this.BoxButton = (ImageView) view.findViewById(R.id.BoxButton);
            this.BoxTitle= (TextView) view.findViewById(R.id.BoxTitle);
            this.toolbar= (RelativeLayout) view.findViewById(R.id.toolbar);
        }
    }
}
