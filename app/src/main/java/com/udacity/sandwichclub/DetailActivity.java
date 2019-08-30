package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.List;

public class DetailActivity extends AppCompatActivity {
    private TextView mAlsoKnown;
    private TextView mDescription;
    private TextView mIngredients;
    private TextView mOrigin;

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);
        mAlsoKnown = findViewById(R.id.also_known_tv);
        mDescription = findViewById(R.id.description_tv);
        mIngredients = findViewById(R.id.ingredients_tv);
        mOrigin = findViewById(R.id.origin_tv);


        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher_round)
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        mAlsoKnown.setText(formatArrayListAsString(sandwich.getAlsoKnownAs()));
        mDescription.setText(formatSentence(sandwich.getDescription()));
        mIngredients.setText(formatArrayListAsString(sandwich.getIngredients()));
        mOrigin.setText(sandwich.getPlaceOfOrigin());
    }

    public String formatArrayListAsString(final List l) {
        String result = "N/A";
        if(l != null && !l.isEmpty()) {
            String arrayString = l.toString();
            result = arrayString.substring(1, arrayString.length() - 1);
        }
        return result;
    }
    public String formatSentence(final String s) {
        String result = "N/A";
        if(s != null && !s.trim().equals("")) {
           result = s.replace(". ", ".\n\n");
            //result = s;
        }
        return result;
    }
}
