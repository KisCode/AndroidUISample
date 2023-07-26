package demo.kiscode.navigation.ui.notifications;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import demo.kiscode.navigation.databinding.FragmentNotificationsBinding;
import demo.kiscode.navigation.ui.notifications.adapter.NotificationAdapter;

public class NotificationsFragment extends Fragment {
    private static final String TAG = "NotificationsFragment";

    private FragmentNotificationsBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate:" + hashCode());
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);
        Log.i(TAG, "onCreateView,notificationsViewModel hashCode " + notificationsViewModel.hashCode());
        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext());
        binding.recyclerview.setLayoutManager(linearLayoutManager);

        binding.recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                boolean scrollEnd = newState == RecyclerView.SCROLL_STATE_IDLE; //滚动结束
                if (scrollEnd) {
                    View firstView = linearLayoutManager.getChildAt(0);
                    if (firstView != null) {
                        int firstPosition = linearLayoutManager.getPosition(firstView);
                        int firstTop = firstView.getTop();
                        Log.i("onScrolled", "滚动停止，第一个view Top = " + firstPosition + "\t" + firstTop);
                    }
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
//                Log.i("onScrolled", "dy = " + dy);
            }
        });

       /* binding.recyclerview.getViewTreeObserver()
                .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        View firstView = linearLayoutManager.getChildAt(0);  //获取可视的第一个view

                        if (firstView == null) {
                            return;
                        }
             *//*           rvBaseOffset = firstView.getTop();
                        int lastPosition = linearLayoutManager.getPosition(firstView);
                        if (lastPosition == 0) {
                            SPManager.getInstance().setRvBaseOffset(rvBaseOffset);
                        }
                        recyclerView.getViewTreeObserver().removeOnGlobalLayoutListener(this);*//*
                    }
                });*/


        notificationsViewModel.getMutableLiveData().observe(getViewLifecycleOwner(), new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> dataList) {
                NotificationAdapter adapter = new NotificationAdapter(dataList, requireContext());
                binding.recyclerview.setAdapter(adapter);

                RecyclerView.LayoutManager layoutManager = binding.recyclerview.getLayoutManager();
                if (layoutManager instanceof LinearLayoutManager) {
                    int lastPosition = 89;
                    int lastTop = -44;

                    ((LinearLayoutManager) layoutManager).scrollToPositionWithOffset(lastPosition,lastTop);
                }
            }
        });
        return root;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        Log.d(TAG, "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }
}