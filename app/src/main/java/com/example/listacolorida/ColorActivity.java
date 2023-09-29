package com.example.listacolorida;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;  // Adicionando importação necessária

public class ColorActivity extends AppCompatActivity {

    private EditText txtTexto;
    private RadioGroup radioGroup;
    private RadioButton radVermelho;
    private RadioButton radVerde;
    private RadioButton radAzul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_lista);

        txtTexto = findViewById(R.id.txtTexto);
        radioGroup = findViewById(R.id.radGroup);
        radVermelho = findViewById(R.id.radVermelho);
        radVerde = findViewById(R.id.radVerde);
        radAzul = findViewById(R.id.radAzul);
        Button btnInsere = findViewById(R.id.btnInsere);
        Button btnCancela = findViewById(R.id.btnCancela);

        btnInsere.setOnClickListener(new EscutadorBotaoAdicionar());
        btnCancela.setOnClickListener(new EscutadorBotaoCancelar());
    }

    private class EscutadorBotaoAdicionar implements View.OnClickListener {

        public void onClick(View view) {
            String texto;
            int cor;

            texto = txtTexto.getText().toString();

            if (texto.isEmpty()) {
                Toast.makeText(ColorActivity.this, "Digite um texto!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (radVermelho.isChecked()) {
                cor = Color.RED;
            } else if (radVerde.isChecked()) {
                cor = Color.GREEN;
            } else if (radAzul.isChecked()) {
                cor = Color.BLUE;
            } else {
                Toast.makeText(ColorActivity.this, "Escolha uma cor!", Toast.LENGTH_SHORT).show();
                return;
            }

            txtTexto.setText("");
            radioGroup.clearCheck();

            Intent i = new Intent(ColorActivity.this, MainActivity.class);
            i.putExtra("text", texto);
            i.putExtra("color", cor);
            setResult(RESULT_OK, i);
            finish();
        }
    }

    private class EscutadorBotaoCancelar implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            txtTexto.setText("");
            radioGroup.clearCheck();
            finish();
        }
    }
}

