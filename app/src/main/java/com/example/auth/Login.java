package com.example.auth;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
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

import org.w3c.dom.Text;

public class Login extends AppCompatActivity {
    private Button bLogin,gLogin;
    private EditText emialLogin,passLogin;
    private TextView tLogin;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar2;
  //  private GoogleSignInClient msign;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        bLogin=findViewById(R.id.bLogin);
        emialLogin=findViewById(R.id.emailLogin);
        passLogin=findViewById(R.id.passLogin);
        tLogin=findViewById(R.id.tLogin);
        progressBar2=findViewById(R.id.progressBar2);
        gLogin=findViewById(R.id.gLogin);





         mAuth = FirebaseAuth.getInstance();

         bLogin.setOnClickListener(new View.OnClickListener() {

             @Override
             public void onClick(View v) {
                 final String email=emialLogin.getText().toString().trim();
                 final String password=passLogin.getText().toString().trim();

                 if(TextUtils.isEmpty(email)){
                     emialLogin.setError("Enter the ID");
                     return;

                 }
                 if (TextUtils.isEmpty(password)){
                     passLogin.setError("Enter the Password");
                     return;

                 }
                 if (password.length()<6){
                     passLogin.setError("Password must be 6 >= Characters");
                     return;

                 }

                 progressBar2.setVisibility(View.VISIBLE);

                 mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                     @Override
                     public void onComplete(@NonNull Task<AuthResult> task) {
                         if (task.isSuccessful()){
                              checkEmail();
                             //finish();
                             //startActivity(new Intent(getApplicationContext(),MainActivity.class));

                         }else {
                             Toast.makeText(getApplicationContext(), "Error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                         }
                         progressBar2.setVisibility(View.GONE);

                     }
                 });







             }
         });
         tLogin.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 startActivity(new Intent(getApplicationContext(),Register.class));
                 finish();
             }
         });




    }
    private void checkEmail(){

        FirebaseUser user =mAuth.getInstance().getCurrentUser();
       // boolean mail=false;


        if (user.isEmailVerified()==true){
            // Toast.makeText(this, "Email Verified", Toast.LENGTH_SHORT).show();

            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        }else {
            Toast.makeText(this, "Verify Mail", Toast.LENGTH_SHORT).show();
            mAuth.signOut();

        }





    }




}







