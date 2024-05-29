package com.example.masterservices.presentation.main;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
import com.example.masterservices.presentation.main.recyclerView.service.ServiceAdapter;
import com.example.masterservices.presentation.main.recyclerView.service.ServiceItem;
import com.example.masterservices.util.Constants;

import java.util.ArrayList;
import java.util.List;

public class ServiceFragment extends Fragment {
    MainViewModel mainViewModel;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private SharedPreferences sharedPreferences;


    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.service_fragment, container, false);
        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
         List<ServiceItem> itemList = new ArrayList<>();
        sharedPreferences = requireActivity().getSharedPreferences(new Constants().USER_KEY_PREFERENCE, Context.MODE_PRIVATE);
        Integer value = sharedPreferences.getInt(new Constants().USER_ID_KEY_PREFERENCE, -1);
        mainViewModel.setUserId(value);
        mainViewModel.getServicesFromVm().observe(getViewLifecycleOwner(), services -> {
            for (int i = 0; i < services.size(); i++) {
                ServiceEntity service = services.get(i);
                itemList.add(new ServiceItem(service.getId(), service.getName(), service.getMaster(), service.getDescription(), service.getPrice(),service.getImage()));
            }

            recyclerView = (RecyclerView) view.findViewById(R.id.serviceRecyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            adapter = new ServiceAdapter(itemList, new ServiceAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(ServiceItem item) {
                    showAlertDialog(item);
                }
            });
            recyclerView.setAdapter(adapter);
        });
        return view;
    }

    private void showAlertDialog(ServiceItem item) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(R.string.ask_a_application);
        builder.setMessage(item.getName() + "\n" + item.getMaster() + "\n" + item.getPrice() + "\n" + item.getDescription());
        builder.setPositiveButton("ОК", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mainViewModel.insertOrderOfUser(new OrderOfUserEntity(mainViewModel.getUserId(), item.getServiceId(), item.getName(), System.currentTimeMillis()));
                Toast.makeText(getContext(), "Ваша заявка успешно подана", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
