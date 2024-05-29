package com.example.masterservices.presentation.auth;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.masterservices.R;
import com.example.masterservices.data.model.local.UserEntity;
import com.example.masterservices.presentation.MainViewModel;

public class RegistrationFragment extends Fragment {

    EditText regEmailText;
    EditText regPasswordText;
    EditText regUsernameText;
    EditText regPhoneNumberText;
    Button regButton;
    TextView goToLogin;

    MainViewModel mainViewModel;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.registration_fragment, container, false);
        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        ;
        regEmailText = view.findViewById(R.id.regEmail);
        regEmailText.addTextChangedListener(textWatcher("email"));
        goToLogin = view.findViewById(R.id.goToLogin);
        regPasswordText = view.findViewById(R.id.regPassword);
        regPasswordText.addTextChangedListener(textWatcher("password"));
        regUsernameText = view.findViewById(R.id.regUsername);
        regUsernameText.addTextChangedListener(textWatcher("username"));
        regPhoneNumberText = view.findViewById(R.id.regPhoneNumber);
        regPhoneNumberText.addTextChangedListener(textWatcher("phoneNumber"));
        regButton = view.findViewById(R.id.regButton);


        addListeners();
        addGoToLoginListener();
        return view;
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
                mainViewModel.setRegByTitle(title, state[0]);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
    }

    private void addGoToLoginListener() {
        goToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginFragment loginFragment = new LoginFragment();
                getParentFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, loginFragment)
                        .commit();
            }
        });
    }

    private void addListeners() {
        regButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                if (
                        !mainViewModel.getRegEmail().isEmpty() &&
                                !mainViewModel.getRegUsername().isEmpty() &&
                                !mainViewModel.getRegPhoneNumber().isEmpty() &&
                                !mainViewModel.getRegPassword().isEmpty()
                ) {
                    UserEntity user = new UserEntity(null, mainViewModel.getRegUsername(), mainViewModel.getRegEmail(), mainViewModel.getRegPhoneNumber(), mainViewModel.getRegPassword());
                    mainViewModel.insertUser(user);
                    LoginFragment loginFragment = new LoginFragment();
                    getParentFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container, loginFragment)
                            .commit();
                } else {
                    Toast.makeText(getContext(), "Заполните все поля", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
