package com.example.masterservices.presentation.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.masterservices.R;
import com.example.masterservices.data.model.local.OrderOfUserEntity;
import com.example.masterservices.data.model.local.ServiceEntity;
import com.example.masterservices.presentation.MainViewModel;
import com.example.masterservices.presentation.main.recyclerView.application.ApplicationAdapter;
import com.example.masterservices.presentation.main.recyclerView.application.ApplicationItem;
import com.example.masterservices.util.DateUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class ApplicationFragment extends Fragment {

    MainViewModel mainViewModel;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.application_fragment, container, false);
        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        Log.e("TAG", "UserId->" + mainViewModel.getUserId().toString());
        mainViewModel.getAllOrdersByUserId(mainViewModel.getUserId());
        List<ApplicationItem> itemList = new ArrayList<>();
        mainViewModel.getOrdersByUserIdFromVm().observe(getViewLifecycleOwner(), orderOfUserEntities -> {
            mainViewModel.getServicesFromVm().observe(getViewLifecycleOwner(), serviceEntities -> {
                for (int i = 0; i < orderOfUserEntities.size(); i++) {
                    OrderOfUserEntity order = orderOfUserEntities.get(i);
                    ApplicationItem applicationItem;
                    for (int j = 0; j < serviceEntities.size(); j++) {
                        ServiceEntity service = serviceEntities.get(j);
                        if (Objects.equals(service.getId(), order.getServiceId())) {
                            DateUtils date = new DateUtils();
                            applicationItem = new ApplicationItem(order.getName(), service.getMaster(), date.toMMDDYYYY(order.getDate()), service.getPrice().toString());
                            Log.e("TAG", "Item->" + applicationItem.getName() + "|" + service.getId());
                            itemList.add(applicationItem);
                            break;
                        }
                    }
                }
                try {
                    recyclerView = (RecyclerView) view.findViewById(R.id.applicationRecyclerView);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    adapter = new ApplicationAdapter(itemList, new ApplicationAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(ApplicationItem item) {

                        }
                    });
                    recyclerView.setAdapter(adapter);
                } catch (Exception e) {

                }
            });
            for (int j = 0; j < itemList.size(); j++) {
                Log.e("TAG", "Item->" + itemList.get(j).getName());
            }

        });

        return view;
    }
}
