package com.app.mytime2sallon;

import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.app.utility.Utility;

import static com.app.mytime2sallon.SelectGenderActivity.GENDER;

public class SignUpCustomerActivity extends AppCompatActivity {

    class CustomerSignUpDetail {
        String name;
        String username;
        String password;
        String confirmPassword;
        String mobileNumber;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getConfirmPassword() {
            return confirmPassword;
        }

        public void setConfirmPassword(String confirmPassword) {
            this.confirmPassword = confirmPassword;
        }

        public String getMobileNumber() {
            return mobileNumber;
        }

        public void setMobileNumber(String mobileNumber) {
            this.mobileNumber = mobileNumber;
        }
    }

    String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_customer);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        gender = this.getIntent().getStringExtra(GENDER);

    }

    public void signUp(View view) {

        CustomerSignUpDetail customerSignUpDetail = getSignUpDetail();

        if (!validDetails(customerSignUpDetail))
            return;


    }

    private boolean validDetails(CustomerSignUpDetail customerSignUpDetail) {

        if (TextUtils.isEmpty(customerSignUpDetail.getName())) {
            Utility.alerDialog(this,this.getResources().getString(R.string.enter_name));
            return false;
        }

        if (TextUtils.isEmpty(customerSignUpDetail.getUsername())) {
            Utility.alerDialog(this,this.getResources().getString(R.string.enter_username));
            return false;
        }

        if (TextUtils.isEmpty(customerSignUpDetail.getPassword())) {
            Utility.alerDialog(this,this.getResources().getString(R.string.enter_password));
            return false;
        }

        if (TextUtils.isEmpty(customerSignUpDetail.getConfirmPassword())) {
            Utility.alerDialog(this,this.getResources().getString(R.string.confirm_password));
            return false;
        }

        if (TextUtils.isEmpty(customerSignUpDetail.getMobileNumber())) {
            Utility.alerDialog(this,this.getResources().getString(R.string.enter_mobile_number));
            return false;
        }

        if (customerSignUpDetail.getMobileNumber().length()!=10) {
            Utility.alerDialog(this,this.getResources().getString(R.string.enter_valid_number));
            return false;
        }

        return true;
    }

    private CustomerSignUpDetail getSignUpDetail() {

        EditText etName = (EditText) findViewById(R.id.etName);
        EditText etUsername = (EditText) findViewById(R.id.etUsername);
        EditText etPassword = (EditText) findViewById(R.id.etPassword);
        EditText etConfirmPassword = (EditText) findViewById(R.id.etConfirmPassword);
        EditText etMobileNumber = (EditText) findViewById(R.id.etMobileNumber);

        CustomerSignUpDetail customerSignUpDetail = new CustomerSignUpDetail();
        customerSignUpDetail.setName(etName.getText().toString());
        customerSignUpDetail.setUsername(etUsername.getText().toString());
        customerSignUpDetail.setPassword(etPassword.getText().toString());
        customerSignUpDetail.setConfirmPassword(etConfirmPassword.getText().toString());
        customerSignUpDetail.setMobileNumber(etMobileNumber.getText().toString());
        return customerSignUpDetail;

    }
}
