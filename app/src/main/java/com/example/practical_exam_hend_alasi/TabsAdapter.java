package com.example.practical_exam_hend_alasi;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class TabsAdapter extends FragmentStateAdapter {

    public TabsAdapter(FragmentActivity activity) {
        super(activity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        if (position == 0) {
            return TabFragment.newInstance("First");

        } else if (position == 1) {
            return TabFragment.newInstance("Second");

        } else {
            return TabFragment.newInstance("Third");
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
