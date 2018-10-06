package albert.profile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ProfileActivity extends AppCompatActivity {

    private User user;
    private Gson gson;

    private TextView nameview;
    private TextView lastnameview;
    private TextView handleview;
    private TextView followingview;
    private TextView followersview;
    private TextView aboutview;

    private ImageView backgroundview;
    private ImageView pictureview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        gson = new Gson();

        try {
            InputStream stream = getAssets().open("AlbertLlopart.json");
            InputStreamReader reader = new InputStreamReader(stream);
            user = gson.fromJson(reader, User.class);

        } catch (IOException e) {
            Toast.makeText(this, "No he pogut llegir el json", Toast.LENGTH_SHORT).show();
        }

        nameview = findViewById(R.id.nameview);
        lastnameview = findViewById(R.id.lastnameview);
        handleview = findViewById(R.id.handleview);
        followingview = findViewById(R.id.followingcountview);
        followersview = findViewById(R.id.followerscountview);
        aboutview = findViewById(R.id.aboutview);

        backgroundview = findViewById(R.id.backgroundview);
        pictureview = findViewById(R.id.pictureview);

        Glide.with(ProfileActivity.this)
                .load("file:///android_asset/background.jpg")
                .into(backgroundview);

        Glide.with(ProfileActivity.this)
                .load("file:///android_asset/albertllopart.png")
                .apply(RequestOptions.circleCropTransform())
                .into(pictureview);

        updateUser();
    }

    private void updateUser() {
        nameview.setText(user.getName());
        lastnameview.setText(user.getLastname());
        handleview.setText(user.getHandle());
        followingview.setText(user.getFollowing());
        followersview.setText(user.getFollowers());
        aboutview.setText(user.getAbout());
    }
}
