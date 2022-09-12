package com.example.agenda;

import android.app.Application;

import com.example.agenda.dao.AlunoDAO;
import com.example.agenda.model.Aluno;

public class AgendaApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        criaAlunosDeTeste();
    }

    private void criaAlunosDeTeste() {
        AlunoDAO dao = new AlunoDAO();
        dao.salva(new Aluno("Isaque", "dshds@fdnfid.com", "3949343"));
        dao.salva(new Aluno("Danilo", "dshds@fdnfid.com", "3949343"));
    }
}
