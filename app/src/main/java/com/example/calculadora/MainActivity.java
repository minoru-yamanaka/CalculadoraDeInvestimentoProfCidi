package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.calculadora.R;


public class MainActivity extends AppCompatActivity {

    private EditText etValorPresente;
    private EditText etInvestimentoMensal;
    private EditText etTaxaJuros;
    private EditText etPrazoAplicacao;
    private Button btnCalcular;
    private Button btnLimpar;
    private TextView tvValorFuturo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etValorPresente = findViewById(R.id.etValorPresente);
        etInvestimentoMensal = findViewById(R.id.etInvestimentoMensal);
        etTaxaJuros = findViewById(R.id.etTaxaJuros);
        etPrazoAplicacao = findViewById(R.id.etPrazoAplicacao);
        btnCalcular = findViewById(R.id.btnCalcular);
        btnLimpar = findViewById(R.id.btnLimpar);
        tvValorFuturo = findViewById(R.id.tvValorFuturo);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularValorFuturo();
            }
        });

        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limparCampos();
            }
        });
    }

    private void calcularValorFuturo() {
        // valores dos campos de entrada
        String valorPresenteStr = etValorPresente.getText().toString();
        String investimentoMensalStr = etInvestimentoMensal.getText().toString();
        String taxaJurosStr = etTaxaJuros.getText().toString();
        String prazoAplicacaoStr = etPrazoAplicacao.getText().toString();

        // Verifique se tem campo vazio
        if (valorPresenteStr.isEmpty() || investimentoMensalStr.isEmpty() || taxaJurosStr.isEmpty() || prazoAplicacaoStr.isEmpty()) {
            tvValorFuturo.setText("Preencha todos os campos.");
            return;
        }

        try {
            // Converta os valores para tipos numéricos
            double valorPresente = Double.parseDouble(valorPresenteStr);
            double investimentoMensal = Double.parseDouble(investimentoMensalStr);
            double taxaJuros = Double.parseDouble(taxaJurosStr);
            int prazoAplicacao = Integer.parseInt(prazoAplicacaoStr);

            // Faça os cálculos financeiros
            double valorFuturo = calcularValorFuturo(valorPresente, investimentoMensal, taxaJuros, prazoAplicacao);

            // Exiba o resultado
            tvValorFuturo.setText(String.format("Valor Futuro: %.2f", valorFuturo));
        } catch (NumberFormatException e) {
            tvValorFuturo.setText("Entradas inválidas. Verifique os valores.");
        }
    }

    private void limparCampos() {
        etValorPresente.setText("");
        etInvestimentoMensal.setText("");
        etTaxaJuros.setText("");
        etPrazoAplicacao.setText("");
        tvValorFuturo.setText("");
    }

    private double calcularValorFuturo(double valorPresente, double investimentoMensal, double taxaJuros, int prazoAplicacao) {
        taxaJuros = taxaJuros / 100; // Converter a taxa de porcentagem para decimal
        double valorFuturo = valorPresente;

        for (int i = 0; i < prazoAplicacao; i++) {
            valorFuturo += investimentoMensal;
            valorFuturo *= (1 + taxaJuros);
        }

        return valorFuturo;
    }
}
