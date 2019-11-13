package com.example.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class PasswordReset extends AppCompatActivity {
    private Button bReset;
    private EditText mailReset;
    private ProgressBar progressBar3;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_reset);
        mailReset=findViewById(R.id.mailReset);
        bReset=findViewById(R.id.bReset);
        progressBar3=findViewById(R.id.progressBar3);

        mAuth=FirebaseAuth.getInstance();

        bReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String mail=mailReset.getText().toString().trim();
                if (TextUtils.isEmpty(mail)){
                    mailReset.setError("Enter the Email");
                    return;

                }
               // if (TextUtils.)
                progressBar3.setVisibility(View.VISIBLE);

                mAuth.sendPasswordResetEmail(mail).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(PasswordReset.this, "Request Have Been Sent", Toast.LENGTH_SHORT).show();
                            finish();
                        }else {
                            Toast.makeText(PasswordReset.this, "Error!"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        progressBar3.setVisibility(View.GONE);
                    }
                });



            }
        });




    }
}
