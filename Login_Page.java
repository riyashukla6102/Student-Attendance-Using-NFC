package allcom.example.attensanceapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login_Page extends AppCompatActivity {
    Button LoginTwo;
    TextView Or, SignUpTwo, ForgotpasswordTwo;
    EditText EmailTwo, PasswordTwo;
    ImageView Logo;
    private FirebaseAuth mAuth;

    @SuppressWarnings("unchecked")

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__page);

        LoginTwo = findViewById(R.id.LoginTwo);
        Or = findViewById(R.id.Or);
        SignUpTwo = findViewById(R.id.SignUpTwo);
        ForgotpasswordTwo = findViewById(R.id.ForgotpasswordTwo);
        EmailTwo = findViewById(R.id.EmailTwo);
        PasswordTwo = findViewById(R.id.PasswordTwo);
        Logo = findViewById(R.id.Logo);


        mAuth = FirebaseAuth.getInstance();




        LoginTwo.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick (View view){
            String email = EmailTwo.getText().toString().trim();
            String password = PasswordTwo.getText().toString().trim();

            if (TextUtils.isEmpty(email)) {
                EmailTwo.setError("Email is Required.");
                return;
            }

            if (TextUtils.isEmpty(password)) {
                PasswordTwo.setError("Password is required");
                return;
            }

            if (password.length() < 6) {
                PasswordTwo.setError("Password Must be >=6 Characters");
                return;
            }

            //Authenticate the user

            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(Login_Page.this, "Loggged in succesfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), Login_Page.class));
                    }else{
                        Toast.makeText(Login_Page.this," Error! " +task.getException().getMessage(), Toast.LENGTH_SHORT);
                    }
                }
            });
        }
    });
}
}




