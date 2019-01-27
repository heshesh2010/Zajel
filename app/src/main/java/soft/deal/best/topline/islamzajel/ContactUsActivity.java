package soft.deal.best.topline.islamzajel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import soft.deal.best.topline.islamzajel.utils.makeDialog;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ContactUsActivity extends AppCompatActivity {
    Activity mActivity;
    EditText userNameET;
    EditText PhonrNumberET;
    EditText emailET;
    EditText messageET;
    Button SendButton;

    TextView userNameTV;
    TextView emailTV;
    TextView subjectTV;
    TextView messageTV;

    ImageView BackKey;
    Spinner subjectET;

    private boolean mailClientOpened = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        mActivity = ContactUsActivity.this;
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/AdobeArabic-Regular.otf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        userNameET  = (EditText) findViewById(R.id.userNameET);
        PhonrNumberET = (EditText) findViewById(R.id.PhonrNumberET);
        emailET = (EditText) findViewById(R.id.emailET);
        subjectET = (Spinner) findViewById(R.id.subjectET);
        messageET = (EditText) findViewById(R.id.messageET);
        SendButton  = (Button) findViewById(R.id.SendButton);
        userNameTV = (TextView) findViewById(R.id.userNameTV);
        emailTV= (TextView) findViewById(R.id.emailTV);
        BackKey= (ImageView) findViewById(R.id.BackKey);
        subjectTV= (TextView) findViewById(R.id.subjectTV);
        messageTV= (TextView) findViewById(R.id.messageTV);

        String[] array_spinner = getResources().getStringArray(R.array.contact_us);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, array_spinner);
        subjectET.setAdapter(adapter);
        subjectET.setSelection(adapter.getPosition("أختر العنوان"));


        String simple0= subjectTV.getText().toString();
        String colored0 = "*";
        SpannableStringBuilder builder0 = new SpannableStringBuilder();
        builder0.append(simple0);
        int start0 = builder0.length();
        builder0.append(colored0);
        int end0 = builder0.length();
        builder0.setSpan(new ForegroundColorSpan(Color.RED), start0, end0,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        subjectTV.setText(builder0);





        String simple =userNameTV.getText().toString();
        String colored = "*";
        SpannableStringBuilder builder = new SpannableStringBuilder();
        builder.append(simple);
        int start = builder.length();
        builder.append(colored);
        int end = builder.length();
        builder.setSpan(new ForegroundColorSpan(Color.RED), start, end,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        userNameTV.setText(builder);



        String simple2 =emailTV.getText().toString();
        SpannableStringBuilder builder2 = new SpannableStringBuilder();
        builder2.append(simple2);
        int start2 = builder2.length();
        builder2.append(colored);
        int end2 = builder2.length();
        builder2.setSpan(new ForegroundColorSpan(Color.RED), start2, end2,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        emailTV.setText(builder2);





        String simple3 =messageTV.getText().toString();
        SpannableStringBuilder builder3 = new SpannableStringBuilder();
        builder3.append(simple3);
        int start3 = builder3.length();
        builder3.append(colored);
        int end3 = builder3.length();
        builder3.setSpan(new ForegroundColorSpan(Color.RED), start3, end3,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        messageTV.setText(builder3);

        SendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(userNameET.getText().toString().isEmpty()||emailET.getText().toString().isEmpty()||subjectET.getSelectedItem().toString().equalsIgnoreCase("أختر العنوان")||messageET.getText().toString().isEmpty()){

                    new makeDialog().makeDialog(mActivity,mActivity.getResources().getString(R.string.no_text_found));

                }
else {


                    sendEmail();
                }
            }
        });

        BackKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }

        });
    }


    protected void sendEmail() {



        String[] recipients = {"islamzajel@outlook.sa"};

        Intent email = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:islamzajel@outlook.sa"));

        // prompts email clients only

  //      email.setType("message/rfc822");

        email.putExtra(Intent.EXTRA_EMAIL, recipients);

        email.putExtra(Intent.EXTRA_SUBJECT, subjectET.getSelectedItem().toString());

        email.putExtra(Intent.EXTRA_TEXT, "الأسم:   "+userNameET.getText()+"\n" +"رقم الجوال:   " +PhonrNumberET.getText()+"\n"+"الرسالة:    "+messageET.getText().toString()+"\n");


        try {

            // the user can choose the email client

            startActivity(Intent.createChooser(email, "أختر تطبيق الأيميل المناسب"));
            startActivityForResult(email, 1000);


        } catch (android.content.ActivityNotFoundException ex) {

            Toast.makeText(ContactUsActivity.this, "لا يوجد اي تطبيق للمراسلة البريدية",

                    Toast.LENGTH_LONG).show();

        }

    }
    //Setting custom Font
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onResume() {
        super.onResume();
        mailClientOpened = false;
    }

    @Override
    protected void onStop() {
        super.onStop();
        mailClientOpened = true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1000 && mailClientOpened){
            Toast.makeText(ContactUsActivity.this, "this is my Toast message!!! =)",
                    Toast.LENGTH_LONG).show();
            finish(); // Or do something else that you need to do when you know that user at least opened the mail client app
        }
    }

}
