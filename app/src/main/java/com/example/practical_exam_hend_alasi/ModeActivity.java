
package com.example.practical_exam_hend_alasi;

import android.os.Bundle;
import android.widget.Button;
import android.widget.PopupMenu;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ModeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mode);

        // Toolbar and Up Button
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // TabLayout and ViewPager2
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        ViewPager2 viewPager = findViewById(R.id.viewPager);

        TabsAdapter adapter = new TabsAdapter(this);
        viewPager.setAdapter(adapter);

        String[] names = {"First", "Second", "Third"};

        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText(names[position])
        ).attach();

        // Back Button
        getOnBackPressedDispatcher().addCallback(this,
                new OnBackPressedCallback(true) {

                    @Override
                    public void handleOnBackPressed() {

                        if (viewPager.getCurrentItem() != 0) {
                            viewPager.setCurrentItem(0);
                        } else {
                            finish();
                        }
                    }
                });

        // PopupMenu
        Button setMode = findViewById(R.id.btnSetMode);

        setMode.setOnClickListener(v -> {

            PopupMenu popup = new PopupMenu(this, setMode);

            popup.getMenu().add(0, 1, 0, "Light");
            popup.getMenu().add(0, 2, 1, "Night");
            popup.getMenu().add(0, 3, 2, "System");

            popup.setOnMenuItemClickListener(item -> {

                if (item.getItemId() == 1) {
                    AppCompatDelegate.setDefaultNightMode(
                            AppCompatDelegate.MODE_NIGHT_NO);

                } else if (item.getItemId() == 2) {
                    AppCompatDelegate.setDefaultNightMode(
                            AppCompatDelegate.MODE_NIGHT_YES);

                } else if (item.getItemId() == 3) {
                    AppCompatDelegate.setDefaultNightMode(
                            AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                }

                return true;
            });

            popup.show();
        });

        // WindowInsets
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

    // Up Navigation
    @Override
    public boolean onSupportNavigateUp() {
        NavUtils.navigateUpFromSameTask(this);
        return true;
    }
}
