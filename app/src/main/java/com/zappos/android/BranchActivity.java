package com.zappos.android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONObject;

import io.branch.referral.Branch;
import io.branch.referral.BranchError;

public class BranchActivity extends AppCompatActivity {

    private static final String TAG = BranchActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch);
    }


    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");

        Branch branch = Branch.getInstance();

// ONLY use the line below IF you ARE NOT using automatic session management.
// Branch branch = Branch.getInstance(getApplicationContext());

        branch.initSession(new Branch.BranchReferralInitListener(){
            @Override
            public void onInitFinished(JSONObject referringParams, BranchError error) {
                if (error == null) {
                    // params are the deep linked params associated with the link that the user clicked -> was re-directed to this app
                    // params will be empty if no data found
                    // ... insert custom logic here ...

                    Log.d(TAG, "Parameters are passed in from branch metrics");


                    String property = referringParams.optString("monster_name");
                    Log.d(TAG, "property received:" + property);

                } else {
                    Log.i("MyApp", error.getMessage());
                }
            }
        }, this.getIntent().getData(), this);
    }

    @Override
    public void onNewIntent(Intent intent) {
        Log.d(TAG, "onNewIntent");
        this.setIntent(intent);
    }

}
