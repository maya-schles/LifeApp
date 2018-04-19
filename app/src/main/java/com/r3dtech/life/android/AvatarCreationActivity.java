package com.r3dtech.life.android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.r3dtech.life.R;
import com.r3dtech.life.logic.LifeAppManager;
import com.r3dtech.life.logic.avatar.Avatar;
import com.r3dtech.life.logic.avatar.implementation.GameAvatar;


public class AvatarCreationActivity extends AppCompatActivity {
    private EditText nameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatar_creation);

        findViews();
        getSupportActionBar().setTitle("Avatar Creation");
    }

    private void findViews() {
        nameEditText = findViewById(R.id.name_edit_text);
    }

    public void createAvatarCallback(View v) {
        finish(createAvatar());
    }

    private Avatar createAvatar() {
        return new GameAvatar(nameEditText.getText().toString());
    }

    private void finish(Avatar avatar) {
        ((LifeAppManager) getApplication()).getGame().setAvatar(avatar);
        finish();
    }

    @Override
    public void onBackPressed() {
        this.finishAffinity();
    }
}
