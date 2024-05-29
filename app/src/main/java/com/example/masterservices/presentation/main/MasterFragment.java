package com.example.masterservices.presentation.main;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.example.masterservices.data.model.local.MasterJob;
import com.example.masterservices.data.model.local.ServiceEntity;
import com.example.masterservices.presentation.MainViewModel;
import com.example.masterservices.presentation.main.recyclerView.master.MasterAdapter;
import com.example.masterservices.presentation.main.recyclerView.master.MasterItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MasterFragment extends Fragment {
    MainViewModel mainViewModel;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private BottomNavigationView bottomNavigationView;


    public MasterFragment(BottomNavigationView bottomNavigationView) {
        this.bottomNavigationView = bottomNavigationView;
    }

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.master_fragment, container, false);
        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        mainViewModel.getAllOrdersByUserId(mainViewModel.getUserId());
        List<MasterItem> itemList = new ArrayList<>();
        mainViewModel.getMasters().observe(getViewLifecycleOwner(), masters -> {
            for (int i = 0; i < masters.size(); i++) {
                MasterJob service = masters.get(i);
                Log.e("TAG", "maste.png->" + masters.get(i).getMaster() + " " + masters.get(i).getJob());
                itemList.add(new MasterItem(service.getMaster(), service.getJob()));
            }

            recyclerView = (RecyclerView) view.findViewById(R.id.masterRecyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            adapter = new MasterAdapter(itemList, new MasterAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(MasterItem item) {
                    List<ServiceEntity> servicesByMaster = new ArrayList<>();
                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            mainViewModel.getServicesByMaster(item.getMaster());
                        }
                    });
                    thread.start();
                    try {
                        thread.join();
                        mainViewModel.getServicesByMaster().observe(getViewLifecycleOwner(), services -> {
                            Log.e("TAG", "servicess->> " + services.get(0).getName());
                            for (int i = 0; i < services.size(); i++) {
                                servicesByMaster.add(services.get(i));
                            }
                            showAlertDialog(item, servicesByMaster);

                        });

                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }
            });
            recyclerView.setAdapter(adapter);
        });
        return view;
    }

    private void showAlertDialog(MasterItem item, List<ServiceEntity> servicesByMaster) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(item.getMaster());
        StringBuilder services = new StringBuilder();
        Log.e("TAG", "services->" + servicesByMaster.toString());
        for (int i = 0; i < servicesByMaster.size(); i++) {
            services.append("       ").append(servicesByMaster.get(i).getName()).append(" ").append(servicesByMaster.get(i).getPrice()).append("р.").append("\n");
        }
        builder.setMessage(item.getMaster() + "\n" + "   Услуги: " + "\n" + services);

        builder.setPositiveButton("Перейти к заявкам", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                bottomNavigationView.setSelectedItemId(R.id.nav_service);
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
