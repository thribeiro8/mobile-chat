package com.example.chat;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;

public class ChatTela1Activity extends AppCompatActivity {

    private Button btEnviar = null;
    private TextInputEditText txInput = null;
    private EditText edTxChat = null;
    private Intent tela2;

    ActivityResultLauncher<Intent> intentResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    String mensagem =  result.getData().getStringExtra("RESPOSTA");
                    if (mensagem != null && !mensagem.equals("")) {

                        edTxChat.getText().append(mensagem);
                        edTxChat.setText(mensagem);
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_tela1);

        btEnviar = findViewById(R.id.btEnviar);
        txInput = findViewById(R.id.txtMsg);
        edTxChat = findViewById(R.id.edTxChat);

        btEnviar.setOnClickListener(b -> {
            edTxChat.getText().append("Cliente: " + txInput.getText().toString());
            edTxChat.getText().append("\n");

            txInput.setText("");

            tela2 = new Intent(this, ChatTela2Activity.class);
            tela2.putExtra("TEXTO", edTxChat.getText().toString());

            intentResult.launch(tela2);
        });
    }
}