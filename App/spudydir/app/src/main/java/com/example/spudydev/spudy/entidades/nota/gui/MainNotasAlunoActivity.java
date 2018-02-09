package com.example.spudydev.spudy.entidades.nota.gui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.spudydev.spudy.R;
import com.example.spudydev.spudy.entidades.nota.dominio.Nota;
import com.example.spudydev.spudy.entidades.nota.persistencia.NotaDAO;
import com.example.spudydev.spudy.entidades.usuario.dominio.Usuario;
import com.example.spudydev.spudy.infraestrutura.MpooAppException;
import com.example.spudydev.spudy.infraestrutura.persistencia.AcessoFirebase;
import com.example.spudydev.spudy.infraestrutura.utils.Auxiliar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainNotasAlunoActivity extends AppCompatActivity {

    private ListView lvNotasAluno;
    private ArrayList<HashMap<String, String>> listaNotas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_notas_aluno);
        getSupportActionBar().hide();

        lvNotasAluno = findViewById(R.id.lvNotasAluno);

        //Método que resgata todas as notas do usuário
        carregarNotas();
    }
    //carregando as notas do usuário
    public void carregarNotas() {
        AcessoFirebase.getFirebase().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                montandoArrayListNotas(dataSnapshot);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
    //setando o ListView com o auxílio do ArrayAdapter<>
    public void montandoArrayListNotas(DataSnapshot dataSnapshot){
        Iterable<DataSnapshot> referencia = dataSnapshot.child("nota").child(getIntent().getStringExtra("codigoTurma")).
                child(AcessoFirebase.getUidUsuario()).getChildren();
        for (DataSnapshot dataSnapshotChild: referencia) {
            Nota nota = dataSnapshotChild.getValue(Nota.class);
            if (nota != null) {
                HashMap<String,String> dicionarioNotaUsuario = new HashMap<>();
                dicionarioNotaUsuario.put("avaliacao", nota.getTituloNota().toUpperCase());
                dicionarioNotaUsuario.put("nota", String.valueOf(nota.getValorNota()));
                listaNotas.add(dicionarioNotaUsuario);
            }
        }
        //Chamando método para setar o ListView de usuários da turma
        setListViewNotasAluno();
    }
    //setando ListView com o auxílio de um SimpleAdapter
    private void setListViewNotasAluno() {
        SimpleAdapter adapter = new SimpleAdapter(this, listaNotas,R.layout.modelo_list_view_notas,
                new String[]{"avaliacao","nota"},
                new int[]{R.id.txtAvaliacao,
                        R.id.txtNota});
        lvNotasAluno.setAdapter(adapter);
    }
}
