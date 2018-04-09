package com.r3dtech.life;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.r3dtech.life.logic.avatar.Avatar;
import com.r3dtech.life.logic.avatar.implementation.GameAvatar;

import static com.r3dtech.life.MainActivity.AVATAR_TAG;

public class AvatarCreationActivity extends AppCompatActivity {
    private EditText nameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatar_creation);

        findViews();
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
        Intent intent = new Intent();
        intent.putExtra(AVATAR_TAG, avatar);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        this.finishAffinity();
    }
}
