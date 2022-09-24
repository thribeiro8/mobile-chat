package com.example.chat;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;

public class ChatTela2Activity extends AppCompatActivity {

    private Button btEnviar = null;
    private TextInputEditText txInput = null;
    private EditText edTxChat = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_tela2);

        btEnviar = findViewById(R.id.btEnviar2);
        txInput = findViewById(R.id.txtMsg2);
        edTxChat = findViewById(R.id.edTxChat2);

        Intent i = getIntent();
        edTxChat.setText(i.getStringExtra("TEXTO"));

        btEnviar.setOnClickListener(b -> {
            edTxChat.getText().append("Atendente: " + txInput.getText().toString());
            txInput.setText("");
            edTxChat.getText().append("\n");

            Intent respostaIntent = new Intent(this, ChatTela1Activity.class);
            respostaIntent.putExtra("RESPOSTA", edTxChat.getText().toString());
            setResult(Activity.RESULT_OK, respostaIntent);
            finish();
        });
    }
}