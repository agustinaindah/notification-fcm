package com.cobafcm.app;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText edtQuestion, edtAnswer;
    Button btnAddFieldSchedule;
    LinearLayout container;
    TextView reList, info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtQuestion = (EditText)findViewById(R.id.edtQuestion);
        edtAnswer = (EditText)findViewById(R.id.edtAnswer);
        btnAddFieldSchedule = (Button)findViewById(R.id.btnAddFieldSchedule);
        container = (LinearLayout)findViewById(R.id.container);
        reList = (TextView)findViewById(R.id.relist);
        info = (TextView)findViewById(R.id.info);
        info.setMovementMethod(new ScrollingMovementMethod());

        btnAddFieldSchedule.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater =
                        (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View addView = layoutInflater.inflate(R.layout.field, null);
                EditText txtQuestion = (EditText) addView.findViewById(R.id.txtQuestion);
                EditText txtAnswer = (EditText) addView.findViewById(R.id.txtAnswer);
                txtQuestion.setText(edtQuestion.getText().toString());
                txtAnswer.setText(edtAnswer.getText().toString());
                Button btnDeleteFaq = (Button)addView.findViewById(R.id.btnDeleteFaq);

                final View.OnClickListener thisListener = new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        info.append("thisListener called:\t" + this + "\n");
                        info.append("Remove addView: " + addView + "\n\n");
                        ((LinearLayout)addView.getParent()).removeView(addView);

                        listAllAddView();
                    }
                };

                btnDeleteFaq.setOnClickListener(thisListener);
                container.addView(addView);

                info.append(
                        "thisListener:\t" + thisListener + "\n"
                                + "addView:\t" + addView + "\n\n"
                );

                listAllAddView();
            }
        });
    }

    private void listAllAddView(){
        reList.setText("");

        int childCount = container.getChildCount();
        for(int i=0; i<childCount; i++){
            Log.d("edit text", String.valueOf(childCount));
            View thisChild = container.getChildAt(i);
            reList.append(thisChild + "\n");
        }
    }

}
