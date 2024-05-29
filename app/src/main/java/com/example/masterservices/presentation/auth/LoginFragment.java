package com.example.masterservices.presentation.auth;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.masterservices.MainActivity;
import com.example.masterservices.R;
import com.example.masterservices.presentation.MainViewModel;
import com.example.masterservices.presentation.main.ServiceFragment;
import com.example.masterservices.util.Constants;

public class LoginFragment extends Fragment {
    EditText logEmailText;
    EditText logPasswordText;
    Button logButton;
    MainViewModel mainViewModel;
    TextView goToReg;

    private SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment, container, false);
        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        sharedPreferences = requireActivity().getSharedPreferences(new Constants().USER_KEY_PREFERENCE, Context.MODE_PRIVATE);
        logEmailText = view.findViewById(R.id.logEmail);
        goToReg = view.findViewById(R.id.goToRegistration);
        logEmailText.addTextChangedListener(textWatcher("email"));
        logPasswordText = view.findViewById(R.id.logPassword);
        logPasswordText.addTextChangedListener(textWatcher("password"));
        logButton = view.findViewById(R.id.logButton);
        mainViewModel.getUsersFromVm().observe(getViewLifecycleOwner(), user -> {
            for (int i = 0; i < user.size(); i++) {
                Log.e("TAGG", user.get(i).getEmail() + " " + user.get(i).getPassword());
            }
        });
        logListener();
        setGoToReg();
        return view;
    }

    private void logListener() {
        logButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainViewModel.getUsersFromVm().observe(getViewLifecycleOwner(), user -> {

                    boolean state = false;
                    for (int i = 0; i < user.size(); i++) {
                        Log.e("TAG", user.get(i).getEmail());

                        if (user.get(i).getEmail().equals(mainViewModel.getLogEmail()) && user.get(i).getPassword().equals(mainViewModel.getLogPassword())) {
                            state = true;
                            mainViewModel.setUserId(user.get(i).getId());
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString(new Constants().USER_AUTH_KEY_PREFERENCE, new Constants().Authorized);
                            editor.putInt(new Constants().USER_ID_KEY_PREFERENCE, user.get(i).getId());
                            editor.apply();
                            break;
                        }
                    }
                    if (state) {
                        ServiceFragment serviceFragment = new ServiceFragment();
                        getParentFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragment_container, serviceFragment)
                                .commit();
                        ((MainActivity) requireActivity()).onAuthSuccess();
                    } else {
                        Toast.makeText(getContext(), "Почта или пароль неверны", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    private void setGoToReg() {
        goToReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegistrationFragment registrationFragment = new RegistrationFragment();
                getParentFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, registrationFragment)
                        .commit();
            }
        });
    }

    private TextWatcher textWatcher(String title) {
        final String[] state = {""};
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                state[0] = s.toString();
                mainViewModel.setLogByTitle(title, state[0]);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
    }
}
