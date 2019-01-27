package soft.deal.best.topline.islamzajel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import soft.deal.best.topline.islamzajel.utils.makeDialog;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;



public class AddMessage extends AppCompatActivity {
    EditText messageET;
    Button SendButton;
    ImageView BackKey;
Activity mActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_add_message);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/AdobeArabic-Regular.otf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
        mActivity=AddMessage.this;
        messageET = (EditText) findViewById(R.id.messageET);


        SendButton  = (Button) findViewById(R.id.SendButton);
        BackKey= (ImageView) findViewById(R.id.BackKey);





        SendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail() ;
            }
        });

        BackKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }

        });
    }
    //Setting custom Font
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    protected void sendEmail() {



        String[] recipients = {"islamzajel@outlook.sa"};

        Intent email = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:islamzajel@outlook.sa"));

        // prompts email clients only

      //  email.setType("message/rfc822");

        if((messageET.getText().toString().isEmpty())){
            new makeDialog().makeDialog(mActivity,mActivity.getResources().getString(R.string.no_text_found));
        }
        else {

            email.putExtra(Intent.EXTRA_SUBJECT, "رسالة جديدة");

            email.putExtra(Intent.EXTRA_EMAIL, recipients);

            email.putExtra(Intent.EXTRA_TEXT, "الرسالة:    " + messageET.getText().toString() + "\n");


            try {

                // the user can choose the email client

                startActivity(Intent.createChooser(email, "أختر تطبيق الأيميل المناسب"));


            } catch (android.content.ActivityNotFoundException ex) {

                Toast.makeText(AddMessage.this, "لا يوجد اي تطبيق للمراسلة البريدية",

                        Toast.LENGTH_LONG).show();

            }
        }
    }

}
