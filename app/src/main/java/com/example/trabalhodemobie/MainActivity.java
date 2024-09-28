package com.example.trabalhodemobie;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText nome;
    private EditText email;
    private EditText idade;
    private EditText disciplina;
    private EditText nota1;
    private EditText nota2;
    private TextView resultado;
    private Button calcular;
    private Button limpar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        nome = findViewById(R.id.nome);
        email = findViewById(R.id.email);
        idade = findViewById(R.id.idade);
        disciplina = findViewById(R.id.disciplina);
        nota1 = findViewById(R.id.nota1);
        nota2 = findViewById(R.id.nota2);
        resultado = findViewById(R.id.resultado);
        calcular = findViewById(R.id.calcular);
        limpar = findViewById(R.id.limpar);

        calcular.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultado.setText(validarCampos());
            }
        });
        limpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limparCampos();
                resultado.setText("");
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }

        private void limparCampos() {
            nome.setText("");
            email.setText("");
            idade.setText("");
            disciplina.setText("");
            nota1.setText("");
            nota2.setText("");
            resultado.setText(""); // Limpa o resumo
    }
        private String validarCampos() {
            String erro = "";
            String dadosAluno = "";
            double media;


            if (TextUtils.isEmpty(nome.getText().toString())) {
                erro = erro +("O campo Nome está vazio.\n");
            }
            if (TextUtils.isEmpty(email.getText().toString())) {
                erro =erro + ("O campo Email está vazio.\n");
            }
            String idadeText = idade.getText().toString();
            if (TextUtils.isEmpty(idadeText)) {
                erro = erro + ("O campo Idade está vazio.\n");
            } else {
                try {
                    Integer.parseInt(idadeText);
                } catch (NumberFormatException e) {
                    erro =erro + ("O campo Idade deve ser um número válido.\n");
                }
            }
            if (TextUtils.isEmpty(disciplina.getText().toString())) {
                erro = erro + ("O campo Disciplina está vazio.\n");
            }
            if (TextUtils.isEmpty(nota1.getText().toString())) {
                erro = erro + ("O campo Nota 1 está vazio.\n");
            }else{
                try {
                    Double.parseDouble(nota1.getText().toString());
                } catch (NumberFormatException e) {
                    erro = erro + ("O campo Nota 1 deve ser um número válido. ");
                    }
            }
            if (TextUtils.isEmpty(nota2.getText().toString())) {
                erro = erro + ("O campo Nota 2 está vazio.\n");
            } else {
                try {
                    Double.parseDouble(nota2.getText().toString());
                } catch (NumberFormatException s) {
                    erro = erro + ("O campo Nota 2 deve ser um número válido. ");
                };

            }
            if (erro != "") {
                return erro;
            } else {
                media = (Double.parseDouble(nota1.getText().toString()) + Double.parseDouble(nota2.getText().toString())) / 2;
                dadosAluno = "Dados do Aluno: " +
                        "\nNome: " + (nome.getText().toString()) +
                        "\nE-mail: " + (email.getText().toString()) +
                        "\nIdade: " + (idade.getText().toString()) +
                        "\nDisciplina: " + (disciplina.getText().toString()) +
                        "\nNota 1: " + (nota1.getText().toString()) +
                        "\nNota 2: " + (nota2.getText().toString()) +
                        "\nMédia: " + (media);
                if (media >= 7) {
                    dadosAluno = dadosAluno + " \nSituação: Aprovado";
                } else {
                    dadosAluno = dadosAluno + " \nSituação: Reprovado";
                }
                return dadosAluno;
            }

    };

}