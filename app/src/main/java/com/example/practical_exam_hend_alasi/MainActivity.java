
package com.example.practical_exam_hend_alasi;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        EditText email = findViewById(R.id.etEmail);
        EditText password = findViewById(R.id.etPassword);
        Button login = findViewById(R.id.btnLogin);

        // 1. TextWatcher
        TextWatcher watcher = new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                boolean validEmail = Patterns.EMAIL_ADDRESS
                        .matcher(email.getText().toString().trim()).matches();

                boolean validPass = password.getText().length() >= 6;

                login.setEnabled(validEmail && validPass);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        };

        email.addTextChangedListener(watcher);
        password.addTextChangedListener(watcher);

        // 2. Open ModeActivity
        login.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ModeActivity.class);
            startActivity(intent);
        });

        // 3. Snackbar - Press Back twice to exit
        getOnBackPressedDispatcher().addCallback(this,
                new OnBackPressedCallback(true) {

                    long lastBack = 0;

                    @Override
                    public void handleOnBackPressed() {

                        long now = SystemClock.elapsedRealtime();

                        if (lastBack != 0 && now - lastBack < 2000) {
                            finishAffinity();
                        } else {
                            lastBack = now;

                            Snackbar.make(
                                    findViewById(R.id.main),
                                    "Press back again to exit",
                                    Snackbar.LENGTH_LONG
                            ).show();
                        }
                    }
                });

        // 4. WindowInsets
        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.main), (v, insets) -> {

                    Insets systemBars = insets.getInsets(
                            WindowInsetsCompat.Type.systemBars()
                    );

                    v.setPadding(
                            systemBars.left,
                            systemBars.top,
                            systemBars.right,
                            systemBars.bottom
                    );

                    return insets;
                });
    }
}
