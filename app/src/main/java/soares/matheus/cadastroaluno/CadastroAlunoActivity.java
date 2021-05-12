package soares.matheus.cadastroaluno;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroAlunoActivity extends AppCompatActivity {

    private EditText nome;
    private EditText cpf;
    private EditText email;
    private AlunoDAO dao;
    private Aluno aluno = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_aluno);

        nome = findViewById(R.id.editNome);
        cpf = findViewById(R.id.editCPF);
        email = findViewById(R.id.editEmail);
        dao = new AlunoDAO(this);

        Intent it = getIntent();
        if(it.hasExtra("aluno")){
            aluno =(Aluno) it.getSerializableExtra("aluno");
            nome.setText(aluno.getNome());
            cpf.setText(aluno.getCpf());
            email.setText(aluno.getEmail());
        }
    }

    public void salvar(View view){
        if(aluno == null){
        Aluno aluno = new Aluno();
        aluno.setNome(nome.getText().toString());
        aluno.setCpf(cpf.getText().toString());
        aluno.setEmail(email.getText().toString());
        long id = dao.inserir(aluno);
        Toast.makeText(this, "Aluno inserido com id = " + id, Toast.LENGTH_SHORT).show();
        }else{
            aluno.setNome(nome.getText().toString());
            aluno.setCpf(cpf.getText().toString());
            aluno.setEmail(email.getText().toString());
            dao.atualizar(aluno);
            Toast.makeText(this, "Aluno Atualizado " , Toast.LENGTH_SHORT).show();

        }
    }
}