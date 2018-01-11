package com.example.spudydev.spudy.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.example.spudydev.spudy.Activity.Alteracao.AlterarDataNascimentoActivity;
import com.example.spudydev.spudy.Activity.Alteracao.AlterarInstituicaoActivity;
import com.example.spudydev.spudy.Activity.Alteracao.AlterarNomeActivity;
import com.example.spudydev.spudy.Activity.Alteracao.AlterarSenhaActivity;
import com.example.spudydev.spudy.DAO.DadosMenuDAO;
import com.example.spudydev.spudy.DAO.PerfilDAO;
import com.example.spudydev.spudy.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MeuPerfilProfessorActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private EditText nomeConsulta;
    private TextView emailConsulta;
    private TextView dataNascimentoConsulta;
    private TextView instituicaoConsulta;
    private Button btn_informacao;
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private DadosMenuDAO dadosMenuDAO = new DadosMenuDAO();
    private PerfilDAO infoPerfil = new PerfilDAO(this);
    private AlertDialog alerta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meu_perfil_professor);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //instanciando os TextViews/Button
        nomeConsulta = (EditText) findViewById(R.id.edt_conta_professor_nomeAlterar);
        emailConsulta = (TextView) findViewById(R.id.edt_conta_professor_emailAlterar);
        dataNascimentoConsulta = (TextView) findViewById(R.id.edt_conta_professor_dataNascimentoAlterar);
        instituicaoConsulta = (TextView) findViewById(R.id.edt_conta_professor_instituicaoAlterar);
        btn_informacao = (Button) findViewById(R.id.btn_informacao_perfil_professor);
        //fim instancias

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.sp_navigation_drawer_open, R.string.sp_navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //setando email e nome do header
        dadosMenuDAO.resgatarUsuario(navigationView, user);

        //setando os TXT'S do meu perfil (Classe PerfilDAO)
        infoPerfil.setTxtPerfil(nomeConsulta, emailConsulta, dataNascimentoConsulta, instituicaoConsulta, user);

        //Informações sobre como trocar dados perfil
        btn_informacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MeuPerfilProfessorActivity.this, R.string.sp_click_informacao, Toast.LENGTH_SHORT).show();
            }
        });

        //Alterar Nome
        nomeConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirTelaAlteraNomeActivity();
            }
        });

        //Alterar Email
        emailConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MeuPerfilProfessorActivity.this, "Em construção...", Toast.LENGTH_SHORT).show();
            }
        });

        //Alterar datanascimento
        dataNascimentoConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirTelaAlteraDataNascimentoActivity();
            }
        });

        //Alterar Instituição
        instituicaoConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirTelaAlteraInstituicaoActivity();
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_meu_perfil:
                        //Activity atual
                        abrirTelaAtualActivity();
                        return true;
                    case R.id.nav_turmas:
                        //Activity de turmas
                        Toast.makeText(MeuPerfilProfessorActivity.this, "Em construção", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.nav_disciplinas:
                        //Activity de turmas
                        Toast.makeText(MeuPerfilProfessorActivity.this, "Em construção", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.nav_sair:
                        //sair
                        sair();
                        return true;
                    default:
                        return false;
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.meu_perfil_professor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_meu_perfil) {
            // Handle the camera action
        } else if (id == R.id.nav_turmas) {

        } else if (id == R.id.nav_disciplinas) {

        } else if (id == R.id.nav_sair) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void sair() {
        //Cria o gerador do AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //define o titulo
        builder.setTitle("Sair");
        //define a mensagem
        builder.setMessage("Tem certeza que desejas sair?");
        //define um botão como positivo
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface arg0, int arg1) {
                FirebaseAuth.getInstance().signOut();
                abrirTelaLoginActivity();
            }
        });
        //define um botão como negativo.
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                alerta.dismiss();
            }
        });
        //cria o AlertDialog
        alerta = builder.create();
        //Exibe
        alerta.show();
    }

    //Intent Alterar Nome
    public void abrirTelaAlteraNomeActivity(){
        Intent intentAbrirTelaAlteraNomeActivity = new Intent(MeuPerfilProfessorActivity.this, AlterarNomeActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("email", emailConsulta.getText().toString());
        intentAbrirTelaAlteraNomeActivity.putExtras(bundle);
        startActivity(intentAbrirTelaAlteraNomeActivity);
    }

    //Intent Alterar Data
    public void abrirTelaAlteraDataNascimentoActivity(){
        Intent intentAbrirTelaAlteraDataNascimentoActivity = new Intent(MeuPerfilProfessorActivity.this, AlterarDataNascimentoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("email", emailConsulta.getText().toString());
        intentAbrirTelaAlteraDataNascimentoActivity.putExtras(bundle);
        startActivity(intentAbrirTelaAlteraDataNascimentoActivity);
    }
    //Intent Alterar Instituicao
    public void abrirTelaAlteraInstituicaoActivity(){
        Intent intentAbrirTelaAlteraInstituicaoActivity = new Intent(MeuPerfilProfessorActivity.this, AlterarInstituicaoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("email", emailConsulta.getText().toString());
        intentAbrirTelaAlteraInstituicaoActivity.putExtras(bundle);
        startActivity(intentAbrirTelaAlteraInstituicaoActivity);
    }
    //Intent Alterar Senha
    public void abrirTelaAlterarSenhaActivity(){
        Intent intentAbrirTelaAlteraSenhaActivity = new Intent(MeuPerfilProfessorActivity.this, AlterarSenhaActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("email", emailConsulta.getText().toString());
        intentAbrirTelaAlteraSenhaActivity.putExtras(bundle);
        startActivity(intentAbrirTelaAlteraSenhaActivity);
    }
    //Intent SingOut
    public void abrirTelaLoginActivity(){
        Intent intentAbrirTelaLoginAcitivty = new Intent(MeuPerfilProfessorActivity.this, LoginActivity.class);
        startActivity(intentAbrirTelaLoginAcitivty);
        finish();
    }
    //Intent Atual
    public void abrirTelaAtualActivity(){
        Intent intentAbrirTelaAtualAcitivty = new Intent(MeuPerfilProfessorActivity.this, MeuPerfilProfessorActivity.class);
        startActivity(intentAbrirTelaAtualAcitivty);
        finish();
    }
}