package com.example.agenda.ui.activity;

import static com.example.agenda.ui.activity.ConstantesActivities.CHAVE_ALUNO;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.agenda.R;
import com.example.agenda.dao.AlunoDAO;
import com.example.agenda.model.Aluno;


public class FormularioAlunoActivity extends AppCompatActivity {

    private EditText inputNome;
    private EditText inputTelefone;
    private EditText inputEmail;
    private final AlunoDAO dao = new AlunoDAO();
    private static final String TITULO_APPBAR_NOVO_ALUNO = "Novo aluno";
    private static final String TITULO_APPBAR_EDITA_ALUNO = "Editar aluno";
    private Aluno aluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);
        inicializacaoDosCampos();
        carregaAluno();

    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.activity_formulario_aluno_menu, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == R.id.actitivy_formulario_aluno_menu_salvar){
            finalizaFormulario();
        }
        return super.onOptionsItemSelected(item);
    }

    private void carregaAluno() {
        Intent dados = getIntent();
        if (dados.hasExtra(CHAVE_ALUNO)) {
            setTitle(TITULO_APPBAR_EDITA_ALUNO);
            aluno = (Aluno) dados.getSerializableExtra(CHAVE_ALUNO);
            preencheCampos();
        } else {
            setTitle(TITULO_APPBAR_NOVO_ALUNO);
            aluno = new Aluno();
        }
    }

    private void preencheCampos() {
        inputNome.setText(aluno.getNome());
        inputTelefone.setText(aluno.getTelefone());
        inputEmail.setText(aluno.getEmail());
    }


    private void finalizaFormulario() {
        preenchaAluno();
        if(aluno.temIdValido()) {
            dao.edita(aluno);
        }else{
            dao.salva(aluno);
        }
        finish();
    }

    private void inicializacaoDosCampos() {
        inputNome = findViewById(R.id.actitivy_formulario_aluno_nome);
        inputTelefone = findViewById(R.id.actitivy_formulario_aluno_telefone);
        inputEmail = findViewById(R.id.actitivy_formulario_aluno_email);
    }

    @NonNull
    private void preenchaAluno() {
        String nome = inputNome.getText().toString();
        String telefone = inputTelefone.getText().toString();
        String email = inputEmail.getText().toString();
        aluno.setNome(nome);
        aluno.setTelefone(telefone);
        aluno.setEmail(email);
    }
}