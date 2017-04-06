package com.catstudio.acs103117_iot;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {

    private Button btn_scan;
    private Button btn_know;
    private TextView txt_url;
    private TextView txt_txt;
    private TextView txt_no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.btn_scan = (Button)findViewById(R.id.btn_scan);
        this.txt_url = (TextView) findViewById(R.id.txt_url);
        this.btn_know = (Button)findViewById(R.id.btn_know);
        this.txt_txt = (TextView) findViewById(R.id.txt_txt);
        this.txt_no = (TextView) findViewById(R.id.txt_no);

        this.btn_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new IntentIntegrator(MainActivity.this).initiateScan();
            }
        });

        this.btn_know.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri=Uri.parse("http://www.9900.com.tw/A06.htm");
                Intent i=new Intent(Intent.ACTION_VIEW,uri);
                startActivity(i);
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent){
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if(result!=null){
            txt_txt.setText("");
            String scanContent = result.getContents();
            String output = "";
            String no = "";
            if(!Character.isLetter(scanContent.charAt(0)))
                output = "掃描錯誤";
            else {
                if(scanContent.substring(10,15).equals("10601") || scanContent.substring(10,15).equals("10602")) {
                    if(scanContent.substring(7,10).equals("625") ||
                            scanContent.substring(7,10).equals("259") ||
                            scanContent.substring(7,10).equals("366") ||
                            scanContent.substring(7,10).equals("125"))
                        output = "恭喜中獎 200 元";
                    else if(scanContent.substring(6,10).equals("8625") ||
                            scanContent.substring(6,10).equals("3259") ||
                            scanContent.substring(6,10).equals("7366"))
                        output = "恭喜中獎 1,000 元";
                    else if(scanContent.substring(5,10).equals("98625") ||
                            scanContent.substring(5,10).equals("93259") ||
                            scanContent.substring(5,10).equals("27366"))
                        output = "恭喜中獎 4,000 元";
                    else if(scanContent.substring(4,10).equals("598625") ||
                            scanContent.substring(4,10).equals("193259") ||
                            scanContent.substring(4,10).equals("827366"))
                        output = "恭喜中獎 10,000 元";
                    else if(scanContent.substring(3,10).equals("4598625") ||
                            scanContent.substring(3,10).equals("3193259") ||
                            scanContent.substring(3,10).equals("7827366"))
                        output = "恭喜中獎 40,000 元";
                    else if(scanContent.substring(2,10).equals("04598625") ||
                            scanContent.substring(2,10).equals("13193259") ||
                            scanContent.substring(2,10).equals("87827366"))
                        output = "恭喜中獎 200,000 元";
                    else if(scanContent.substring(2,10).equals("59729884"))
                        output = "恭喜中獎 2,000,000 元";
                    else if(scanContent.substring(2,10).equals("82885130"))
                        output = "恭喜中獎 10,000,000 元";
                    else
                        output = "好可惜,跟中獎擦身而過";
                }

                else if(scanContent.substring(10,15).equals("10511") || scanContent.substring(10,15).equals("10512")) {
                    if(scanContent.substring(7,10).equals("760") ||
                            scanContent.substring(7,10).equals("177") ||
                            scanContent.substring(7,10).equals("048") ||
                            scanContent.substring(7,10).equals("000") ||
                            scanContent.substring(7,10).equals("059") ||
                            scanContent.substring(7,10).equals("478") ||
                            scanContent.substring(7,10).equals("569"))
                        output = "恭喜中獎 200 元";
                    else if(scanContent.substring(6,10).equals("6177") ||
                            scanContent.substring(6,10).equals("8760") ||
                            scanContent.substring(6,10).equals("2048"))
                        output = "恭喜中獎 1,000 元";
                    else if(scanContent.substring(5,10).equals("96177") ||
                            scanContent.substring(5,10).equals("68760") ||
                            scanContent.substring(5,10).equals("52048"))
                        output = "恭喜中獎 4,000 元";
                    else if(scanContent.substring(4,10).equals("796177") ||
                            scanContent.substring(4,10).equals("868760") ||
                            scanContent.substring(4,10).equals("952048"))
                        output = "恭喜中獎 10,000 元";
                    else if(scanContent.substring(3,10).equals("9796177") ||
                            scanContent.substring(3,10).equals("6868760") ||
                            scanContent.substring(3,10).equals("4952048"))
                        output = "恭喜中獎 40,000 元";
                    else if(scanContent.substring(2,10).equals("69796177") ||
                            scanContent.substring(2,10).equals("76868760") ||
                            scanContent.substring(2,10).equals("14952048"))
                        output = "恭喜中獎 200,000 元";
                    else if(scanContent.substring(2,10).equals("53077074"))
                        output = "恭喜中獎 2,000,000 元";
                    else if(scanContent.substring(2,10).equals("68789003"))
                        output = "恭喜中獎 10,000,000 元";
                    else
                        no = "好可惜,跟中獎擦身而過";
                }

                else if(scanContent.substring(10,13).equals("106"))
                    no = "這發票日期尚未開獎,無法查詢是否中獎";
                else
                    no = "這發票已太久遠,無法查詢是否中獎";
            }
            txt_url.setText(output);
            txt_no.setText(no);
        }else{
            Toast.makeText(getApplicationContext(), "nothing", Toast.LENGTH_LONG).show();
        }

    }
}
