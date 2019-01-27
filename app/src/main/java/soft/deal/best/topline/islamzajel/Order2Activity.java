package soft.deal.best.topline.islamzajel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import soft.deal.best.topline.islamzajel.utils.makeDialog;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Order2Activity extends AppCompatActivity {


    Activity mActivity;
    EditText firstUserNameET;
    EditText phoneNumberET;
    Spinner giftCountET;
    EditText emailET;
    EditText       cityET;
    EditText districitET;
    EditText        streetET;
    EditText buildET;
    ///
    RadioButton outsideeksa;
    RadioButton insideksa;
    Spinner country2ET;
    EditText city2ET;
    EditText         districit2ET;
    EditText moqat3a2ET;
    EditText street2ET;
    EditText        build2ET;
    //
    RadioButton Alsidereh;
    RadioGroup cloak_TypeRG;
    RadioButton cloak_subType1;
    RadioButton cloak_subType2;
    RadioButton cloak_subType3;
    //
    RadioGroup  KomTypeRG;
    RadioButton Kom_subType1;
    RadioButton Kom_subType2;
    EditText        cloakTallET;
    //
    RadioButton Ma5botah;
    RadioGroup        cloak_Type1RG;
    RadioButton cloak_subType11;
    RadioButton        cloak_subType22;
    RadioButton cloak_subType33;
    //
    RadioGroup KomTyp1eRG;
    RadioButton Kom_subType11;
    RadioButton         Kom_subType22;
    EditText cloakTall1ET;
    EditText        noteET;
    Button SendButton;


    ////////////

    String City ;
    String Districit;
    String Street ;
    String Build;
    String cloakType;
    String Cloak_subType;
    String KomType;
    String CloakTall ;

    private static final String DEFAULT_LOCAL = "أختر";
    private static final String DEFAULT_LOCAL1 = "0";

    ImageView BackKey;

    CheckBox gift_tybe1;
    CheckBox       gift_tybe2;
    CheckBox gift_tybe3;
    CheckBox        gift_tybe4;
    CheckBox gift_tybe5;
    CheckBox        gift_tybe6;
    CheckBox gift_tybe7;
    CheckBox       gift_tybe8;
    CheckBox gift_tybe9;
    CheckBox       gift_tybe10;
    CheckBox gift_tybe11;
    CheckBox       gift_tybe12;
    CheckBox gift_tybe13;
    CheckBox        gift_tybe14;
    CheckBox gift_tybe15;
    CheckBox        gift_tybe16;
    CheckBox[] rb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_order2);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/AdobeArabic-Regular.otf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
        mActivity=Order2Activity.this;

     /*   RelativeLayout layout = (RelativeLayout) findViewById(R.id.moreMenu);
        for (int i = 0; i < layout.getChildCount(); i++) {
            View child = layout.getChildAt(i);
            child.setEnabled(false);
        }*/

        BackKey= (ImageView) findViewById(R.id.BackKey);

        firstUserNameET  = (EditText) findViewById(R.id.firstUserNameET);
        phoneNumberET = (EditText) findViewById(R.id.phoneNumberET);
        giftCountET= (Spinner) findViewById(R.id.giftCountET);
        emailET = (EditText) findViewById(R.id.emailET);
        insideksa= (RadioButton) findViewById(R.id.insideksa);

        cityET = (EditText) findViewById(R.id.cityET);
        districitET = (EditText) findViewById(R.id.districitET);
        streetET = (EditText) findViewById(R.id.streetET);
        buildET = (EditText) findViewById(R.id.buildET);
        ///
        outsideeksa= (RadioButton) findViewById(R.id.outsideeksa);
        country2ET= (Spinner) findViewById(R.id.country2ET);
        city2ET = (EditText) findViewById(R.id.city2ET);
        districit2ET = (EditText) findViewById(R.id.districit2ET);
        moqat3a2ET = (EditText) findViewById(R.id.moqat3a2ET);
        street2ET = (EditText) findViewById(R.id.street2ET);
        build2ET = (EditText) findViewById(R.id.build2ET);
        //
        Alsidereh = (RadioButton) findViewById(R.id.Alsidereh);
        cloak_TypeRG = (RadioGroup) findViewById(R.id.cloak_TypeRG);
        cloak_subType1 = (RadioButton) findViewById(R.id.cloak_subType1);
        cloak_subType2 = (RadioButton) findViewById(R.id.cloak_subType2);
        cloak_subType3 = (RadioButton) findViewById(R.id.cloak_subType3);
        //
        KomTypeRG = (RadioGroup) findViewById(R.id.KomTypeRG);
        Kom_subType1 = (RadioButton) findViewById(R.id.Kom_subType1);
        Kom_subType2 = (RadioButton) findViewById(R.id.Kom_subType2);
        cloakTallET = (EditText) findViewById(R.id.cloakTallET);
        //
        Ma5botah = (RadioButton) findViewById(R.id.Ma5botah);
        cloak_Type1RG = (RadioGroup) findViewById(R.id.cloak_Type1RG);
        cloak_subType11 = (RadioButton) findViewById(R.id.cloak_subType11);
        cloak_subType22 = (RadioButton) findViewById(R.id.cloak_subType22);
        cloak_subType33 = (RadioButton) findViewById(R.id.cloak_subType33);
        //
        KomTyp1eRG = (RadioGroup) findViewById(R.id.KomTyp1eRG);
        Kom_subType11 = (RadioButton) findViewById(R.id.Kom_subType11);
        Kom_subType22 = (RadioButton) findViewById(R.id.Kom_subType22);
        cloakTall1ET = (EditText) findViewById(R.id.cloakTall1ET);
        noteET = (EditText) findViewById(R.id.noteET);
        SendButton = (Button) findViewById(R.id.SendButton);


        gift_tybe1 = (CheckBox) findViewById(R.id.gift_tybe1);
                gift_tybe2= (CheckBox) findViewById(R.id.gift_tybe2);
        gift_tybe3= (CheckBox) findViewById(R.id.gift_tybe3);
                gift_tybe4= (CheckBox) findViewById(R.id.gift_tybe4);
        gift_tybe5= (CheckBox) findViewById(R.id.gift_tybe5);
                gift_tybe6= (CheckBox) findViewById(R.id.gift_tybe6);
        gift_tybe7= (CheckBox) findViewById(R.id.gift_tybe7);
                gift_tybe8= (CheckBox) findViewById(R.id.gift_tybe8);
        gift_tybe9= (CheckBox) findViewById(R.id.gift_tybe9);
                gift_tybe10= (CheckBox) findViewById(R.id.gift_tybe10);
        gift_tybe11= (CheckBox) findViewById(R.id.gift_tybe11);
                gift_tybe12= (CheckBox) findViewById(R.id.gift_tybe12);
        gift_tybe13= (CheckBox) findViewById(R.id.gift_tybe13);
                gift_tybe14= (CheckBox) findViewById(R.id.gift_tybe14);
        gift_tybe15= (CheckBox) findViewById(R.id.gift_tybe15);
                gift_tybe16= (CheckBox) findViewById(R.id.gift_tybe16);

        rb = new CheckBox[16];
        rb[0] = gift_tybe1;
        rb[1] = gift_tybe2;
        rb[2] = gift_tybe3;
        rb[3] = gift_tybe4;
        rb[4] = gift_tybe5;
        rb[5] = gift_tybe6;
        rb[6] = gift_tybe7;
        rb[7] = gift_tybe8;
        rb[8] = gift_tybe9;
        rb[9] = gift_tybe10;
        rb[10] = gift_tybe11;
        rb[11] = gift_tybe12;
        rb[12] = gift_tybe13;
        rb[13] = gift_tybe14;
        rb[14] = gift_tybe15;
        rb[15] = gift_tybe16;

        TextView userNametV = (TextView) findViewById(R.id.userNametV);
        TextView phoneNumbertV = (TextView) findViewById(R.id.phoneNumbertV);
        TextView emailTV= (TextView) findViewById(R.id.emailTV);
        TextView        adressTV= (TextView) findViewById(R.id.adressTV);
        TextView cityTV= (TextView) findViewById(R.id.cityTV);
        TextView        districitTV= (TextView) findViewById(R.id.districitTV);
        TextView streetTV= (TextView) findViewById(R.id.streetTV);
        TextView        buildTV= (TextView) findViewById(R.id.buildTV);
        TextView country2TV= (TextView) findViewById(R.id.country2TV);
        TextView        city2TV= (TextView) findViewById(R.id.city2TV);
        TextView districit2TV= (TextView) findViewById(R.id.districit2TV);
        TextView       street2TV= (TextView) findViewById(R.id.street2TV);
        TextView build2TV= (TextView) findViewById(R.id.build2TV);
        TextView        cloakTV= (TextView) findViewById(R.id.cloakTV);
        TextView cloakType1TV= (TextView) findViewById(R.id.cloakType1TV);
        TextView        KomTypeTV= (TextView) findViewById(R.id.KomTypeTV);
        TextView cloakTallTV= (TextView) findViewById(R.id.cloakTallTV);
        TextView        cloakType2TV= (TextView) findViewById(R.id.cloakType2TV);
        TextView KomType1TV= (TextView) findViewById(R.id.KomType1TV);
        TextView        cloakTall1TV= (TextView) findViewById(R.id.cloakTall1TV);
        TextView giftCountTV= (TextView) findViewById(R.id.giftCountTV);
        redAstr(giftCountTV);
        redAstr(userNametV);
        redAstr(phoneNumbertV);
        redAstr(emailTV);
        redAstr(adressTV);
        redAstr(cityTV);
        redAstr(districitTV);
        redAstr(streetTV);
        redAstr(city2TV);
        redAstr(districit2TV);
        redAstr(buildTV);
        redAstr(country2TV);
        redAstr(street2TV);
        redAstr(build2TV);
        redAstr(cloakTV);
        redAstr(cloakType1TV);
        redAstr(KomTypeTV);
        redAstr(cloakTallTV);
        redAstr(cloakType2TV);
        redAstr(KomType1TV);
        redAstr(cloakTall1TV);

        SendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                sendEmail();
            }
        });

        insideksa.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                insideksa.setChecked(true);
                outsideeksa.setChecked(false);
                city2ET.setText("");
                districit2ET.setText("");
                street2ET.setText("");
                build2ET.setText("");
            }
        });

        outsideeksa.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                outsideeksa.setChecked(true);
                insideksa.setChecked(false);
                cityET.setText("");
                districitET.setText("");
                streetET.setText("");
                buildET.setText("");
            }
        });



        /////////////
        Alsidereh.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Alsidereh.setChecked(true);

                Ma5botah.setChecked(false);
                cloak_subType11.setChecked(false);
                cloak_subType22.setChecked(false);
                cloak_subType33 .setChecked(false);
                //
                Kom_subType11.setChecked(false);
                Kom_subType22.setChecked(false);
                cloakTall1ET.setText("");
            }
        });




        Ma5botah.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Ma5botah.setChecked(true);
                Alsidereh.setChecked(false);

                cloak_subType1.setChecked(false);
                cloak_subType2.setChecked(false);
                cloak_subType3 .setChecked(false);
                //
                Kom_subType1.setChecked(false);
                Kom_subType2.setChecked(false);
                cloakTallET.setText("");
            }
        });

        String[] array_spinner = getResources().getStringArray(R.array.country_arrays);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, array_spinner);

        String[] array_spinner2 = getResources().getStringArray(R.array.country_arrays);
        ArrayAdapter adapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, array_spinner2);
        country2ET.setAdapter(adapter2);
        country2ET.setSelection(adapter2.getPosition(DEFAULT_LOCAL));



        String[] array_spinner3 = getResources().getStringArray(R.array.count_arrays);
        ArrayAdapter adapter3 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, array_spinner3);
        giftCountET.setAdapter(adapter3);
        giftCountET.setSelection(adapter3.getPosition(DEFAULT_LOCAL1));

        BackKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }

        });

    }

    protected void sendEmail() {

        if(insideksa.isChecked()){
            City =  cityET.getText().toString();
            Districit = districitET.getText().toString();
            Street =streetET.getText().toString();
            Build=buildET.getText().toString();

            if(Alsidereh.isChecked()){
                cloakType="السيدرية";

                int selectedId = cloak_TypeRG.getCheckedRadioButtonId();
                RadioButton  radioButton = (RadioButton) findViewById(selectedId);
                Cloak_subType = radioButton.getText().toString();

                int selectedId2 = KomTypeRG.getCheckedRadioButtonId();
                RadioButton  radioButton2 = (RadioButton) findViewById(selectedId2);
                KomType= radioButton2.getText().toString();
                CloakTall=cloakTallET.getText().toString();


            }
            else{

                cloakType="المخبوته";

                int selectedId = cloak_Type1RG.getCheckedRadioButtonId();
                RadioButton  radioButton = (RadioButton) findViewById(selectedId);
                Cloak_subType = radioButton.getText().toString();

                int selectedId2 = KomTyp1eRG.getCheckedRadioButtonId();
                RadioButton  radioButton2 = (RadioButton) findViewById(selectedId2);
                KomType= radioButton2.getText().toString();
                CloakTall=cloakTall1ET.getText().toString();


            }

            if(!(!firstUserNameET.getText().toString().isEmpty() && !phoneNumberET.getText().toString().isEmpty()  && !emailET.getText().toString().isEmpty() && !City.isEmpty() && !Districit.isEmpty() && !Street.isEmpty() && !Build.isEmpty() && !cloakType.isEmpty() && !Cloak_subType.isEmpty() && !KomType.isEmpty() && !CloakTall.isEmpty())){
                new makeDialog().makeDialog(mActivity,mActivity.getResources().getString(R.string.no_text_found));
            }
            else if(giftCountET.getSelectedItem().toString().equals("0")){
                new makeDialog().makeDialog(mActivity,mActivity.getResources().getString(R.string.no_text_found));
            }
            else{

                String giftTybe = "\n";
                for(int i=0;i<16;i++){

                    if(rb[i].isChecked()){
                        giftTybe= giftTybe+rb[i].getText().toString()+"\n";
                    }
                }


                String[] recipients = {"islamzajel@outlook.sa"};

                Intent email = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:islamzajel@outlook.sa"));

                // prompts email clients only

        //        email.setType("message/rfc822");


                email.putExtra(Intent.EXTRA_EMAIL, recipients);

                email.putExtra(Intent.EXTRA_SUBJECT, "طلب هدية  جديدة");

                email.putExtra(Intent.EXTRA_TEXT, "الأسم الأول:"+firstUserNameET.getText()+"\n"+"رقم الهاتف:"+phoneNumberET.getText().toString()+"\n"+"البريد الإلكتروني:"+emailET.getText().toString()+"\n"+"داخل السعودية:"+"\n"+"المدينة:"+City+"\n"+"الحي:"+Districit+"\n"+"الشارع:"+Street+"\n"+"رقم المنزل أو البناية:"+Build+"\n"+"عدد الهدايا"+giftCountET.getSelectedItem().toString()+"\n"+"نوع الهدية:"+cloakType+"\n"+"نوعها:"+Cloak_subType+"\n"+"نوع الكم:"+KomType+"\n"+"طول العباءة من الخلف:"+CloakTall+"\n"+"ملاحظات:"+noteET.getText().toString()+"\n"+giftTybe);

                try {

                    // the user can choose the email client
                    startActivity(Intent.createChooser(email, "أختر تطبيق الأيميل المناسب"));

                } catch (android.content.ActivityNotFoundException ex) {

                    Toast.makeText(Order2Activity.this, "لا يوجد اي تطبيق للمراسلة البريدية",

                            Toast.LENGTH_LONG).show();
                }
            }





        }

        else{
            City =  city2ET.getText().toString();
            Districit = districit2ET.getText().toString();
            Street =street2ET.getText().toString();
            Build=build2ET.getText().toString();



            if(Alsidereh.isChecked()){
                cloakType="السيدرية";

                int selectedId = cloak_TypeRG.getCheckedRadioButtonId();
                RadioButton  radioButton = (RadioButton) findViewById(selectedId);
                Cloak_subType = radioButton.getText().toString();

                int selectedId2 = KomTypeRG.getCheckedRadioButtonId();
                RadioButton  radioButton2 = (RadioButton) findViewById(selectedId2);
                KomType= radioButton2.getText().toString();
                CloakTall=cloakTallET.getText().toString();


            }
            else{

                cloakType="المخبوته";

                int selectedId = cloak_Type1RG.getCheckedRadioButtonId();
                RadioButton  radioButton = (RadioButton) findViewById(selectedId);
                Cloak_subType = radioButton.getText().toString();

                int selectedId2 = KomTyp1eRG.getCheckedRadioButtonId();
                RadioButton  radioButton2 = (RadioButton) findViewById(selectedId2);
                KomType= radioButton2.getText().toString();
                CloakTall=cloakTall1ET.getText().toString();


            }

            if(!(!firstUserNameET.getText().toString().isEmpty() && !phoneNumberET.getText().toString().isEmpty()  && !emailET.getText().toString().isEmpty() && !City.isEmpty() && !Districit.isEmpty() && !Street.isEmpty() && !Build.isEmpty() && !cloakType.isEmpty() && !Cloak_subType.isEmpty() && !KomType.isEmpty() && !CloakTall.isEmpty())){
                new makeDialog().makeDialog(mActivity,mActivity.getResources().getString(R.string.no_text_found));
            }
            else if(country2ET.getSelectedItem().toString().equals("أختر")){
                new makeDialog().makeDialog(mActivity,mActivity.getResources().getString(R.string.no_text_found));
            }
            else if(giftCountET.getSelectedItem().toString().equals("0")){
                new makeDialog().makeDialog(mActivity,mActivity.getResources().getString(R.string.no_text_found));
            }
            else{
                String giftTybe = "\n";
                for(int i=0;i<16;i++){

                    if(rb[i].isChecked()){
                        giftTybe= giftTybe+rb[i].getText().toString()+"\n";
                    }
                }

                String[] recipients = {"islamzajel@outlook.sa"};

                Intent email = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:islamzajel@outlook.sa"));

                // prompts email clients only

          //      email.setType("message/rfc822");


                email.putExtra(Intent.EXTRA_EMAIL, recipients);

                email.putExtra(Intent.EXTRA_SUBJECT, "طلب هدية جديدة");

                email.putExtra(Intent.EXTRA_TEXT, "الأسم الأول:"+firstUserNameET.getText()+"\n"+"رقم الهاتف:"+phoneNumberET.getText().toString()+"\n"+"البريد الإلكتروني:"+emailET.getText().toString()+"\n"+"خارج السعودية:"+"\n"+"الدولة:"+country2ET.getSelectedItem().toString()+"\n"+"المدينة:"+City+"\n"+"الحي:"+Districit+"\n"+"المقاطعه:"+moqat3a2ET.getText().toString()+"\n"+"الشارع:"+Street+"\n"+"رقم المنزل أو البناية:"+Build+"\n"+"عدد الهدايا:"+giftCountET.getSelectedItem().toString()+"\n"+"نوع الهدية:"+cloakType+"\n"+"نوعها:"+Cloak_subType+"\n"+"نوع الكم:"+KomType+"\n"+"طول العباءة من الخلف:"+CloakTall+"\n"+"ملاحظات:"+noteET.getText().toString()+"\n" + giftTybe);

                try {

                    // the user can choose the email client
                    startActivity(Intent.createChooser(email, "أختر تطبيق الأيميل المناسب"));

                } catch (android.content.ActivityNotFoundException ex) {

                    Toast.makeText(Order2Activity.this, "لا يوجد اي تطبيق للمراسلة البريدية",

                            Toast.LENGTH_LONG).show();
                }
            }

        }
    }

    public void redAstr(TextView TV){

        String simple = TV.getText().toString();
        String colored = "*";
        SpannableStringBuilder builder = new SpannableStringBuilder();

        builder.append(simple);
        int start = builder.length();
        builder.append(colored);
        int end = builder.length();



        builder.setSpan(new ForegroundColorSpan(Color.RED), start, end,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        TV.setText(builder);

    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
