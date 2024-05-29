package com.example.masterservices.presentation.main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.masterservices.MainActivity;
import com.example.masterservices.R;
import com.example.masterservices.data.model.local.UserEntity;
import com.example.masterservices.presentation.MainViewModel;
import com.example.masterservices.presentation.auth.LoginFragment;
import com.example.masterservices.util.Constants;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ProfileFragment extends Fragment {

    MainViewModel mainViewModel;


    EditText name;
    EditText email;
    EditText phoneNumber;
    Button saveButton;


    FloatingActionButton logoutButton;

    private SharedPreferences sharedPreferences;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment, container, false);
        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        sharedPreferences = requireActivity().getSharedPreferences(new Constants().USER_KEY_PREFERENCE, Context.MODE_PRIVATE);
        Integer value = sharedPreferences.getInt(new Constants().USER_ID_KEY_PREFERENCE, -1);
        if(value!=-1) {
            mainViewModel.setUserId(value);
            name = view.findViewById(R.id.user_name);
            email = view.findViewById(R.id.user_email);
            logoutButton = view.findViewById(R.id.user_logout);
            phoneNumber = view.findViewById(R.id.user_phone_number);
            saveButton = view.findViewById(R.id.user_save);
            mainViewModel.getUserByIdFromDb(value).observe(getViewLifecycleOwner(), user -> {
                email.setText(user.getEmail());
                email.addTextChangedListener(textWatcher("email"));
                name.setText(user.getName());
                name.addTextChangedListener(textWatcher("username"));
                phoneNumber.setText(user.getPhoneNumber());
                phoneNumber.addTextChangedListener(textWatcher("phoneNumber"));
                saveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mainViewModel.insertUser(new UserEntity(
                                mainViewModel.getUserId(),
                                mainViewModel.getRegUsername(),
                                user.getEmail(),
                                mainViewModel.getRegPhoneNumber(),
                                user.getPassword()
                        ));
                        mainViewModel.clean();
                    }
                });
                logoutButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SharedPreferences.Editor editor  = sharedPreferences.edit();
                        editor.putString(new Constants().USER_AUTH_KEY_PREFERENCE, new Constants().Unauthorized);
                        editor.putInt(new Constants().USER_ID_KEY_PREFERENCE, -1);
                        editor.apply();
                        ((MainActivity) requireActivity()).onAuthGo();
                        LoginFragment loginFragment = new LoginFragment();
                        getParentFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragment_container, loginFragment)
                                .commit();
                    }
                });
            });
        } else {
            SharedPreferences.Editor editor  = sharedPreferences.edit();
            editor.putString(new Constants().USER_AUTH_KEY_PREFERENCE, new Constants().Unauthorized);
            editor.putInt(new Constants().USER_ID_KEY_PREFERENCE, -1);
            editor.apply();
            ((MainActivity) requireActivity()).onAuthGo();
            LoginFragment loginFragment = new LoginFragment();
            getParentFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, loginFragment)
                    .commit();
        }
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
}
