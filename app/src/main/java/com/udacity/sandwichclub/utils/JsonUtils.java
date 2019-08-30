package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    public static final String KEY_NAME = "name";
    public static final String KEY_MAIN_NAME = "mainName";
    public static final String KEY_ALSO_KNOW_AS = "alsoKnownAs";
    public static final String KEY_INGREDIENTS = "ingredients";
    public static final String KEY_PLACE_OF_ORIGIN = "placeOfOrigin";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_IMAGE = "image";

    public static Sandwich parseSandwichJson(String json) {
        Sandwich result = new Sandwich();
        if (json != null && !json.trim().equals("")) {
            try {
                JSONObject jsonObject = new JSONObject(json);
                String mainName = jsonObject.getJSONObject(KEY_NAME).getString(KEY_MAIN_NAME);
                result.setMainName(mainName);

                JSONArray alsoKnownAs = jsonObject.getJSONObject(KEY_NAME).getJSONArray(KEY_ALSO_KNOW_AS);
                List<String> list = new ArrayList<>();
                if (alsoKnownAs != null && alsoKnownAs.length() > 0) {
                    for (int i = 0; i < alsoKnownAs.length(); i++) {
                        list.add(alsoKnownAs.getString(i));
                    }
                }
                result.setAlsoKnownAs(list);

                JSONArray ingredients = jsonObject.getJSONArray(KEY_INGREDIENTS);
                list = new ArrayList<>();
                for (int i = 0; i < ingredients.length(); i++) {
                    list.add(ingredients.getString(i));
                }
                result.setIngredients(list);

                String placeOfOrigin = jsonObject.getString(KEY_PLACE_OF_ORIGIN);
                result.setPlaceOfOrigin(placeOfOrigin);

                String description = jsonObject.getString(KEY_DESCRIPTION);
                result.setDescription(description);

                String image = jsonObject.getString(KEY_IMAGE);
                result.setImage(image);
            } catch (JSONException e) {
                Log.e("Parsing Error", e.toString());
                e.printStackTrace();
            }
        }
        return result;
    }
}
