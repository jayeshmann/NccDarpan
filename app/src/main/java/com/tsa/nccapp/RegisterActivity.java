package com.tsa.nccapp;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.tsa.nccapp.LocationUtil.PermissionUtils;
import com.tsa.nccapp.custom.CustomActivity;
import com.tsa.nccapp.utils.GLOBAL;
import com.tsa.nccapp.validation.Validation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class RegisterActivity extends CustomActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, ActivityCompat.OnRequestPermissionsResultCallback,
        PermissionUtils.PermissionResultCallback {

    // LogCat tag
    private static final String TAG = MyLocationUsingHelper.class.getSimpleName();

    private final static int PLAY_SERVICES_REQUEST = 1000;
    private final static int REQUEST_CHECK_SETTINGS = 2000;
    private TextView category;

    private Location mLastLocation;

    // Google client to interact with Google API

    private GoogleApiClient mGoogleApiClient;

    double latitude;
    double longitude;

    private String categoryString;

    // list of permissions

    ArrayList<String> permissions = new ArrayList<>();
    PermissionUtils permissionUtils;

    boolean isPermissionGranted;

    private int mYear, mMonth, mDay;

    private EditText userName;
    private EditText fullName;
    private EditText mobile;
    private EditText email;
    private EditText password;
    private EditText directorate;
    private EditText confirmPassword;

    private TextView userValidationTV;
    private TextView dobTv;
    private LinearLayout dobLayout;
    private Button registerButton;
    private RadioButton male;
    private Context context;

    private LinearLayout locationLayout;
    private TextView locationTv;
    private boolean haveMinChar;
    private boolean isUNameChecked;
    private ArrayList<String> existedUNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        context = RegisterActivity.this;

        existedUNames=new ArrayList<>();

        for(int i=0;i<10;i++)
        {
            existedUNames.add("Ramlal"+i);
        }

        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        /////////////////////////////////////////////////////////////////////
        init();
        /////////////////////////////////////////////////////////////////////

        permissionUtils = new PermissionUtils(RegisterActivity.this);

        //////////////////////////////////////////////////////////////////////////////////////////////////////////
        permissions.add(android.Manifest.permission.ACCESS_FINE_LOCATION);
        permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        permissionUtils.check_permission(permissions, "Need GPS permission for getting your location", 1);
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        dobLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(RegisterActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                dobTv.setText(dayOfMonth + "/" + (1 + monthOfYear) + "/" + year);
                            }
                        }, mYear, mMonth, mDay).show();
        ////////////////////////////////////////////////////////////////////////////////////////////
            }
        });


        permissionUtils = new PermissionUtils(RegisterActivity.this);
        permissions.add(android.Manifest.permission.ACCESS_FINE_LOCATION);
        permissions.add(android.Manifest.permission.ACCESS_COARSE_LOCATION);
        permissionUtils.check_permission(permissions, "Need GPS permission for getting your location", 1);


        locationLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getLocation();

                if (mLastLocation != null) {
                    latitude = mLastLocation.getLatitude();
                    longitude = mLastLocation.getLongitude();
                    getAddress();

                } else {
                    showToast("Couldn't get the location. Make sure location is enabled on the device");
                }
            }
        });

        // check availability of play services
        if (checkPlayServices()) {

            // Building the GoogleApi client
            buildGoogleApiClient();
        }

    }

    private void init() {
        /////////////////////////////////////////////////
        dobTv = findViewById(R.id.dob_tv);
        userValidationTV=findViewById(R.id.user_validation_tv);
        /////////////////////////////////////////////////

        //////////////////////////////////////////////////
        userName = findViewById(R.id.user_name);
       /* userName.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if(s.length()==6)
                {
                    //userName.setEnabled(false);
                    checkUserName(s.toString());
                }
                if(s.length()>=6) {
                    if (isUNameExist(s.toString())) {
                        userValidationTV.setText("Allready Exist");
                        userValidationTV.setTextColor(getResources().getColor(R.color.red));
                        isUNameChecked=false;
                    }
                    else {
                        userValidationTV.setText("Available");
                        userValidationTV.setTextColor(getResources().getColor(R.color.Green));
                        isUNameChecked=true;
                    }
                }
                else
                {
                    userValidationTV.setTextColor(getResources().getColor(R.color.red));
                    userValidationTV.setText("Min Lenght 6");
                    isUNameChecked=false;
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub
            }
            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
                //Is_Valid_Person_Name(name); // pass your EditText Obj here.
            }
        });*/
        /////////////////////////////////////////////////
        final ArrayList<String> list = new ArrayList();
        list.add("ANO");
        list.add("GCI");
        list.add("WTLO");
        list.add("PI Staff");
        list.add("Principal");
        list.add("Cadet");
        list.add("Others");

        category=findViewById(R.id.category);

        category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(RegisterActivity.this)
                        .title(R.string.title)
                        .items(R.array.category)
                        .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                categoryString=""+text;
                                category.setText(categoryString);
                                //////////////////////////////////////
                                if(which==6) {
                                    new MaterialDialog.Builder(RegisterActivity.this)
                                            .title(R.string.input)
                                            .inputType(InputType.TYPE_CLASS_TEXT)
                                            .input(R.string.sub_title, R.string.input_hint, new MaterialDialog.InputCallback() {
                                                @Override
                                                public void onInput(MaterialDialog dialog, CharSequence input) {
                                                    categoryString=""+input;
                                                    category.setText(categoryString);
                                                }
                                            }).show();
                                }
                                ////////////////////////////////////////
                                return true;
                            }
                        })
                        .positiveText(R.string.choose)
                        .show();
            }
        });

        ////////////////////////////////////////////////
        fullName = findViewById(R.id.full_name);
        mobile = findViewById(R.id.mobile);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirm);
        directorate = findViewById(R.id.directorate);
//        ////////////////////////////////////////////////
//
//        ////////////////////////////////////////////////
        dobTv = findViewById(R.id.dob_tv);
        dobLayout = findViewById(R.id.dob_layout);
//        ///////////////////////////////////////////////
//
//        //////////////////////////////////////////////////
        locationLayout = findViewById(R.id.location_layout);
        locationTv = findViewById(R.id.location_tv);
        //////////////////////////////////////////////////

        ///////////////////////////////////////////////
        male = findViewById(R.id.male);
//        ///////////////////////////////////////////////
//
        registerButton = findViewById(R.id.register);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String gender;
                if (password.getText().toString().equals(confirmPassword.getText().toString())) {
                    if (male.isChecked())
                        gender = "Male";
                    else
                        gender = "Female";
                    register(view, gender);
                } else {
                    Toast.makeText(RegisterActivity.this, "Passwords does not Matched", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void goLogin(View view) {
        startActivity(new Intent(RegisterActivity.this, LoginActivity2.class));
        finish();
    }

    public void registerCandidate(final String name, final String userName, final String email, final String phone,
                                  final String dob, final String pwd, final String gender, final String directorate) {
        //Showing the progress dialog
        final ProgressDialog progress = new ProgressDialog(RegisterActivity.this);
        progress.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, GLOBAL.baseURL + "candidate_registration_api.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        try {
                            JSONObject json = new JSONObject(s);
                            Log.e("json", json.toString());
                            String status = json.getString("status");
                            String msg = json.getString("msg");

                            if (status.equals("0")) {
                                Toast.makeText(context, "Successfully Registered", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(RegisterActivity.this, LoginActivity2.class));
                            } else
                                Toast.makeText(context, msg, Toast.LENGTH_LONG).show();

                            progress.dismiss();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //Showing toast
                        Toast.makeText(context, "Some issue in loading" + volleyError, Toast.LENGTH_LONG).show();
                        // progress.dismiss();
                    }
                }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                //Creating parameters
                Map<String, String> params = new Hashtable<String, String>();

                //Adding parameters
                params.put("name", name);
                //params.put("user_name", userName);
                params.put("email", email);
                params.put("mobile", phone);
                params.put("dob", dob);
                params.put("password", pwd);
                params.put("gender", gender);
                params.put("status", "Registration");
                params.put("directorate", directorate);
                params.put("location", "" + locationTv.getText());
                params.put("lat", "" + latitude);
                params.put("lng", "" + longitude);
                params.put("category", "" + categoryString);

                //returning parameters
                return params;
            }
        };

        //Creating a Request Queue
        RequestQueue requestQueue = Volley.newRequestQueue(RegisterActivity.this);

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }

    private boolean checkValidation() {
        boolean ret = true;
        if (!Validation.hasText(password)) ret = false;
        if (!Validation.hasText(confirmPassword)) ret = false;
        if (!Validation.isEmailAddress(email, true)) ret = false;
        if (!Validation.isPhoneNumber(mobile, true)) ret = false;
        if (!Validation.hasText(fullName)) ret = false;
        if (!Validation.hasText(directorate)) ret = false;
        //if (!Validation.hasText(userName)) ret = false;
        if (("" + this.dobTv.getText()).equals("")) {
            // dobTv.setFocusable(true);
            ret = false;
        }
        return ret;
    }

    public void register(View view, String gender) {
        if (checkValidation()) {
            String userName = "" + this.userName.getText();
            String fullName = "" + this.fullName.getText();
            String mobile = "" + this.mobile.getText();
             String email = "" + this.email.getText();
            String password = "" + this.password.getText();
            String confirmPassword = "" + this.confirmPassword.getText();
            String dob = "" + this.dobTv.getText();
            String directorate = "" + this.directorate.getText();
            registerCandidate(fullName, userName, email, mobile, dob, password, gender, directorate);
        }
    }


    /**
     * Method to display the location on UI
     */

    private void getLocation() {

        if (isPermissionGranted) {

            try {
                mLastLocation = LocationServices.FusedLocationApi
                        .getLastLocation(mGoogleApiClient);
            } catch (SecurityException e) {
                e.printStackTrace();
            }

        }

    }

    public Address getAddress(double latitude, double longitude) {
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(this, Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            return addresses.get(0);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }


    public void getAddress() {

        Address locationAddress = getAddress(latitude, longitude);

        if (locationAddress != null) {
            String address = locationAddress.getAddressLine(0);
            String address1 = locationAddress.getAddressLine(1);
            String city = locationAddress.getLocality();
            String state = locationAddress.getAdminArea();
            String country = locationAddress.getCountryName();
            String postalCode = locationAddress.getPostalCode();

            String currentLocation;

            if (!TextUtils.isEmpty(address)) {
                currentLocation = address;

                if (!TextUtils.isEmpty(address1))
                    currentLocation += "\n" + address1;

                if (!TextUtils.isEmpty(city)) {
                    currentLocation += "\n" + city;

                    if (!TextUtils.isEmpty(postalCode))
                        currentLocation += " - " + postalCode;
                } else {
                    if (!TextUtils.isEmpty(postalCode))
                        currentLocation += "\n" + postalCode;
                }

                if (!TextUtils.isEmpty(state))
                    currentLocation += "\n" + state;

                if (!TextUtils.isEmpty(country))
                    currentLocation += "\n" + country;

                locationTv.setText(currentLocation);

            }
        }
    }

    /**
     * Creating google api client object
     */

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();

        mGoogleApiClient.connect();

        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(mLocationRequest);

        PendingResult<LocationSettingsResult> result =
                LocationServices.SettingsApi.checkLocationSettings(mGoogleApiClient, builder.build());

        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(LocationSettingsResult locationSettingsResult) {

                final Status status = locationSettingsResult.getStatus();

                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        // All location settings are satisfied. The client can initialize location requests here
                        getLocation();
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        try {
                            // Show the dialog by calling startResolutionForResult(),
                            // and check the result in onActivityResult().
                            status.startResolutionForResult(RegisterActivity.this, REQUEST_CHECK_SETTINGS);

                        } catch (IntentSender.SendIntentException e) {
                            // Ignore the error.
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        break;
                }
            }
        });


    }

    /**
     * Method to verify google play services on the device
     */

    private boolean checkPlayServices() {

        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();

        int resultCode = googleApiAvailability.isGooglePlayServicesAvailable(this);

        if (resultCode != ConnectionResult.SUCCESS) {
            if (googleApiAvailability.isUserResolvableError(resultCode)) {
                googleApiAvailability.getErrorDialog(this, resultCode,
                        PLAY_SERVICES_REQUEST).show();
            } else {
                Toast.makeText(getApplicationContext(),
                        "This device is not supported.", Toast.LENGTH_LONG)
                        .show();
                finish();
            }
            return false;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        final LocationSettingsStates states = LocationSettingsStates.fromIntent(data);
        switch (requestCode) {
            case REQUEST_CHECK_SETTINGS:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        // All required changes were successfully made
                        getLocation();
                        break;
                    case Activity.RESULT_CANCELED:
                        // The user was asked to change settings, but chose not to
                        break;
                        default:
                        break;
                }
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkPlayServices();
    }

    /**
     * Google api callback methods
     */
    @Override
    public void onConnectionFailed(ConnectionResult result) {
        Log.i(TAG, "Connection failed: ConnectionResult.getErrorCode() = "
                + result.getErrorCode());
    }

    @Override
    public void onConnected(Bundle arg0) {

        // Once connected with google api, get the location
        getLocation();
    }

    @Override
    public void onConnectionSuspended(int arg0) {
        mGoogleApiClient.connect();
    }

    // Permission check functions
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        // redirects to utils
        permissionUtils.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

    @Override
    public void PermissionGranted(int request_code) {
        Log.i("PERMISSION", "GRANTED");
        isPermissionGranted = true;
    }

    @Override
    public void PartialPermissionGranted(int request_code, ArrayList<String> granted_permissions) {
        Log.i("PERMISSION PARTIALLY", "GRANTED");
    }

    @Override
    public void PermissionDenied(int request_code) {
        Log.i("PERMISSION", "DENIED");
    }

    @Override
    public void NeverAskAgain(int request_code) {
        Log.i("PERMISSION", "NEVER ASK AGAIN");
    }

    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(RegisterActivity.this, LoginActivity2.class));
        finish();
    }

    private boolean isUNameExist(String enteredUName)
    {
       for(int i=0;i<existedUNames.size();i++)
       {
           if (existedUNames.get(i).equalsIgnoreCase(enteredUName))
           return true;
       }
        return false;
    }

    public void checkUserName(final String userName) {
        //Showing the progress dialog
        final ProgressDialog progress = new ProgressDialog(RegisterActivity.this);
        progress.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, GLOBAL.baseURL + "checkusername_api.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        try {
                            JSONObject json = new JSONObject(s);
                            Log.e("json", json.toString());
                            String status = json.getString("status");
                            String msg = json.getString("msg");

                            if (status.equals("0")) {

                                JSONArray userName=json.getJSONArray("username");

                                existedUNames.clear();
                                for (int i=0;i<userName.length();i++)
                                {
                                    existedUNames.add(userName.getString(i));
                                }
                            } else
                                Toast.makeText(context, msg, Toast.LENGTH_LONG).show();

                            progress.dismiss();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //Showing toast
                        Toast.makeText(context, "Some issue in loading" + volleyError, Toast.LENGTH_LONG).show();
                        progress.dismiss();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                //Creating parameters
                Map<String, String> params = new Hashtable<String, String>();

                //Adding parameters
                params.put("username", userName);

                //returning parameters
                return params;
            }
        };

        //Creating a Request Queue
        RequestQueue requestQueue = Volley.newRequestQueue(RegisterActivity.this);

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }


}

