package soft.deal.best.topline.islamzajel.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import soft.deal.best.topline.islamzajel.MessageActivity;
import soft.deal.best.topline.islamzajel.R;
import soft.deal.best.topline.islamzajel.bean.Message;
import soft.deal.best.topline.islamzajel.bean.Slider;
import soft.deal.best.topline.islamzajel.fragment.SlidShowFragment;


import java.util.List;


public class ImageSlideAdapter extends PagerAdapter {

    FragmentActivity activity;
    List<Slider> m_Message;
    SlidShowFragment SlidShowFragment;
    public ImageSlideAdapter(FragmentActivity activity, List<Slider> m_Message,
                             SlidShowFragment SlidShowFragment) {
        this.activity = activity;
        this.SlidShowFragment = SlidShowFragment;
        this.m_Message = m_Message;


    }

    @Override
    public int getCount() {
        return m_Message.size();
    }

    @Override
    public View instantiateItem(ViewGroup container, final int position) {
        LayoutInflater inflater = (LayoutInflater) activity
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.vp_image, container, false);
        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


}