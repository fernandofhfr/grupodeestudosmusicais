package br.com.androidpro.grupodeestudosmusicais.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;


import br.com.androidpro.grupodeestudosmusicais.R;

public class IntroActivity extends AppCompatActivity implements View.OnClickListener{

    private static final int RC_SIGN_IN = 9001;

    private FirebaseAuth autenticacao;

    private GoogleSignInClient googleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        findViewById(R.id.buttonCadastrar).setOnClickListener(this);
        findViewById(R.id.buttonGoogle).setOnClickListener(this);
        findViewById(R.id.textViewLogin).setOnClickListener(this);

        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);

        autenticacao = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser usuarioAtual = autenticacao.getCurrentUser();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                try{
                    GoogleSignInAccount account = task.getResult(ApiException.class);
                    firebaseAuthWithGoogle(account);
                }catch (ApiException e){
                    Toast.makeText(IntroActivity.this,
                            "Falha ao realizar login.",
                             Toast.LENGTH_SHORT).show();
                }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct){
        Toast.makeText(this,
                "Login id: " + acct.getId() + ".",
                Toast.LENGTH_SHORT).show();

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        autenticacao.signInWithCredential(credential)
                            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(IntroActivity.this,
                                                "Login realizado com sucesso.",
                                                Toast.LENGTH_SHORT).show();

                                        chamaActivityMain();
                                    }else{
                                        Toast.makeText(IntroActivity.this,
                                                "Falha ao realizar login.",
                                                Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });
    }

    public void chamaActivityMain(){
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }

    public void cadastrar(){
        startActivity(new Intent(getApplicationContext(), CadastroActivity.class));
    }

    public void entrarGoogle(){
        Intent i = googleSignInClient.getSignInIntent();
        startActivityForResult(i, RC_SIGN_IN);
    }

    public void login(){

    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.buttonCadastrar) {
            cadastrar();
        } else if (i == R.id.buttonGoogle) {
            entrarGoogle();
        } else if (i == R.id.textViewLogin) {
            login();
        }
    }
}
