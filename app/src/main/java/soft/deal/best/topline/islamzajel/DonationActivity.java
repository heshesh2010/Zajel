package soft.deal.best.topline.islamzajel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.TransactionDetails;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class DonationActivity extends AppCompatActivity implements BillingProcessor.IBillingHandler{
    Button first_do_button;
    Button second_do_button;
    Button third_do_button;
    Button first_sms_button;
    Button second_sms_button;
    ImageView BackKey;
    BillingProcessor bp;
    Activity mActivity ;
String googleIn_appKey="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAsRaVInd/6sRNe3LfpTdjFGPCJDaBbOwlEAqo9hLLphEaS/7nJiqK/JhK6E8QtwABNqN2i3uIlVxuywm2ZcxpiOOvwrshK1OpezqkdKjQO98ZwVD0SdCu88hsS0yfAf6Kyj58H9pVbZIgTcFzLMgttchAarp8fOn3q/V570SofBnPWGbcb5P0vngaEj1OhULMKCIKja23s9CFYsAGh372HA8uJhcR/kBqHoKji9CzxvUA51tJB6jkmMd55nm73EadhYsuslSotwqo4Nc1PCpPS1rlDAV5SM3APpdEu9CkUc3n/nxJGFYJc0ThcGQfUuMDO9DLEn/pBe9gQX4pV/7AqwIDAQAB";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_donation);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/AdobeArabic-Regular.otf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        mActivity=DonationActivity.this;
        first_do_button = (Button) findViewById(R.id.first_do_button);

        second_do_button= (Button) findViewById(R.id.second_do_button);
                third_do_button= (Button) findViewById(R.id.third_do_button);
        first_sms_button= (Button) findViewById(R.id.first_sms_button);
                second_sms_button= (Button) findViewById(R.id.second_sms_button);
        BackKey= (ImageView) findViewById(R.id.BackKey);


        bp = new BillingProcessor(this, googleIn_appKey, this);

      //  first_do_button.setText(bp.getPurchaseListingDetails("support_dev1").priceText);

        first_do_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bp.purchase(mActivity, "support_dev1");

            }
        });

        second_do_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bp.purchase(mActivity, "support_dev2");

            }
        });

        third_do_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bp.purchase(mActivity, "support_dev3");

            }
        });


        first_sms_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bp.purchase(mActivity, "support_sms1");

            }
        });

        second_sms_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bp.purchase(mActivity, "support_sms2");

            }
        });


        BackKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }

        });

    }

    // IBillingHandler implementation

    @Override
    public void onBillingInitialized() {
        /*
         * Called when BillingProcessor was initialized and it's ready to purchase
         */
    }

    @Override
    public void onProductPurchased(String productId, TransactionDetails details) {
    if(productId.equalsIgnoreCase("support_dev1")){
        bp.consumePurchase("support_dev1");

    }
        else if(productId.equalsIgnoreCase("support_dev2")){
        bp.consumePurchase("support_dev2");
    }
    else if(productId.equalsIgnoreCase("support_dev3")){
        bp.consumePurchase("support_dev3");
    }
    else if(productId.equalsIgnoreCase("support_dev4")){
        bp.consumePurchase("support_dev4");
    }
    else if(productId.equalsIgnoreCase("support_dev5")){
        bp.consumePurchase("support_dev5");
    }
    else if(productId.equalsIgnoreCase("support_sms1")){
        bp.consumePurchase("support_sms1");
    }
    else if(productId.equalsIgnoreCase("support_sms2")){
        bp.consumePurchase("support_sms2");
    }
    else if(productId.equalsIgnoreCase("support_sms3")){
        bp.consumePurchase("support_sms3");
    }

    }

    @Override
    public void onBillingError(int errorCode, Throwable error) {
        /*
         * Called when some error occurred. See Constants class for more details
         *
         * Note - this includes handling the case where the user canceled the buy dialog:
         * errorCode = Constants.BILLING_RESPONSE_RESULT_USER_CANCELED
         */
    }

    @Override
    public void onPurchaseHistoryRestored() {
        /*
         * Called when purchase history was restored and the list of all owned PRODUCT ID's
         * was loaded from Google Play
         */
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!bp.handleActivityResult(requestCode, resultCode, data))
            super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onDestroy() {
        if (bp != null)
            bp.release();

        super.onDestroy();
    }

    //Setting custom Font
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
