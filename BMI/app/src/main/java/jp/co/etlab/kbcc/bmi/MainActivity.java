package jp.co.etlab.kbcc.bmi;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    //BMI
    double yourBMI;
    //メッセージ用のテキスト
    String bmiTxt;
    String errorMsg;
    String leanInfo;
    String standardInfo;
    String littleInfo;
    String veryInfo;
    String impInfo;

    //小数点以下2ケタ用のフォーマット
    DecimalFormat myFormat = new DecimalFormat("###.##");
    Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //文字列リソースを取得
        Resources res = getResources();
        bmiTxt = res.getString(R.string.bmiText);
        errorMsg = res.getString(R.string.errorMsg);
        leanInfo = res.getString(R.string.leanInfo);
        standardInfo = res.getString(R.string.standardInfo);
        littleInfo = res.getString(R.string.littleObesity);
        veryInfo = res.getString(R.string.veryObesity);
        impInfo = res.getString(R.string.improveObesity);

        //ボタンを押したらBMIの計算クラスへ
        ((Button)findViewById(R.id.button1)).setOnClickListener(new CalcBMI());
    }

    class CalcBMI implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            //身長が入力されているEditText
            EditText heightText = (EditText)findViewById(R.id.heightText);
            EditText weightText = (EditText)findViewById(R.id.weightText);
            String heightStr = heightText.getText().toString();
            String weightStr = weightText.getText().toString();

            //結果を表示するTextView
            TextView stdWeightTextView =
                    (TextView)findViewById(R.id.bmiText);

            try{
                //BMIを計算する BMI=WEight(Kg)/(Heigh(m)*Height(m))
                double height = Double.parseDouble(heightStr);
                double weight = Double.parseDouble(weightStr);
                yourBMI = weight / Math.pow(height/100,2);
                String bmiStr = myFormat.format(yourBMI);
                String bmiMsg = BmiMsg(yourBMI);
                stdWeightTextView.setText(
                        bmiTxt + bmiStr + "\n" + bmiMsg);
            } catch(NumberFormatException e) {
                Toast.makeText(getApplicationContext(), errorMsg, Toast.LENGTH_LONG);
            }
        }

        private String BmiMsg(double dBmi) {
            String info;
            if (dBmi < 18.5 ) {
                info = leanInfo;
            } else if (dBmi < 25.0 ) {
                info = standardInfo;
            } else if (dBmi < 30.0) {
                info = littleInfo;
            } else if (dBmi < 35.0 ) {
                info = veryInfo;
            } else  {
                info = impInfo;
            }
            return info;
        }
    }
}
