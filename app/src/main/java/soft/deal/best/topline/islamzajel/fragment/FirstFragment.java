package soft.deal.best.topline.islamzajel.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import soft.deal.best.topline.islamzajel.R;

/**
 * Created by hashoma on 8/27/2016.
 */
public class FirstFragment extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home2, container, false);


        int y[] = {10000,4000,2400,1000};

        CategorySeries series = new CategorySeries("");
        for(int i=0; i < y.length; i++){
            series.add("Bar"+(i+1),y[i]);
        }

        XYMultipleSeriesDataset dataSet = new XYMultipleSeriesDataset();  // collection of series under one object.,there could any
        dataSet.addSeries(series.toXYSeries());                            // number of series

        //customization of the chart

        XYSeriesRenderer renderer = new XYSeriesRenderer();     // one renderer for one series

        renderer.setColor(Color.BLUE);
        renderer.setDisplayChartValues(true);
        renderer.setChartValuesSpacing((float) 50.5d);
      //  renderer.setLineWidth((float) 1.5d);


        XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();   // collection multiple values for one renderer or series
        mRenderer.addSeriesRenderer(renderer);
     //   mRenderer.setChartTitle("عدد الرسائل المرسلة");
//        mRenderer.setXTitle("xValues");
    //    mRenderer.setYTitle("العدد");
       // mRenderer.setZoomButtonsVisible(true);
      //  mRenderer.setShowLegend(true);

       mRenderer.setAntialiasing(true);
        mRenderer.setBarSpacing(.4);   // adding spacing between the line or stacks
    //  mRenderer.setApplyBackgroundColor(true);
      //  mRenderer.setBackgroundColor(R.color.Zajel_Greenbox);
       mRenderer.setMarginsColor(R.color.Zajel_Greenbox);
     //  mRenderer.setGridColor(R.color.Zajel_Greenbox);
        mRenderer.setAxesColor(R.color.Zajel_black);

        mRenderer.setXAxisMin(.1);
        mRenderer.setYAxisMin(.5);
        mRenderer.setXAxisMax(5);
        mRenderer.setYAxisMax(15000);
//
        mRenderer.setYLabels(0);
       mRenderer.setXLabels(0);
        mRenderer.addXTextLabel(1,"الربع الأول");

        mRenderer.addXTextLabel(2,"الربع الثاني");
        mRenderer.addXTextLabel(3,"الربع الثالث");
        mRenderer.addXTextLabel(4,"الربع الرابع");
        mRenderer.setChartTitleTextSize(80);
        mRenderer.setChartValuesTextSize(50);
      //  mRenderer.setLegendTextSize(50);
        mRenderer.setLabelsTextSize(50);
       // mRenderer.setPanEnabled(true, true);    // will fix the chart position
        final GraphicalView cht;
        cht = ChartFactory.getBarChartView(getActivity(), dataSet, mRenderer, BarChart.Type.DEFAULT);
        RelativeLayout layout = (RelativeLayout) view.findViewById(R.id.BarGraph2);
        layout.addView(cht);

        return view;
    }

    public static FirstFragment newInstance(String text) {

        FirstFragment f = new FirstFragment();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){

        }
    }
}
