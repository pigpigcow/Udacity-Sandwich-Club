package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        Sandwich result = new Sandwich();
        if(json != null && !json.trim().equals("")) {
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(json);
                String mainName = jsonObject.getJSONObject("name").getString("mainName");
                result.setMainName(mainName);
            } catch (JSONException e) {
                Log.e("Parse given json", e.toString());
                e.printStackTrace();
            }

            if(jsonObject != null) {
                try {
                    String mainName = jsonObject.getJSONObject("name").getString("mainName");
                    result.setMainName(mainName);
                } catch (JSONException e) {
                    Log.e("Parse mainName", e.toString());
                    e.printStackTrace();
                }

                try {
                    JSONArray alsoKnownAs = jsonObject.getJSONObject("name").getJSONArray("alsoKnownAs");
                    List<String> list = new ArrayList<>();
                    if(alsoKnownAs != null && alsoKnownAs.length() > 0) {
                        for(int i = 0; i < alsoKnownAs.length(); i++){
                            list.add(alsoKnownAs.getString(i));
                        }
                    }
                    result.setAlsoKnownAs(list);
                } catch (JSONException e) {
                    Log.e("Parse alsoKnownAs", e.toString());
                    e.printStackTrace();
                }

                try {
                    JSONArray ingredients = jsonObject.getJSONArray("ingredients");
                    List<String> list = new ArrayList<>();
                    for(int i = 0; i < ingredients.length(); i++){
                        list.add(ingredients.getString(i));
                    }
                    result.setIngredients(list);
                } catch (JSONException e) {
                    Log.e("Parse ingredients", e.toString());
                    e.printStackTrace();
                }

                try {
                    String placeOfOrigin = jsonObject.getString("placeOfOrigin");
                    result.setPlaceOfOrigin(placeOfOrigin);
                } catch (JSONException e) {
                    Log.e("Parse placeOfOrigin", e.toString());
                    e.printStackTrace();
                }

                try {
                    String description = jsonObject.getString("description");
                    result.setDescription(description);
                } catch (JSONException e) {
                    Log.e("Parse description", e.toString());
                    e.printStackTrace();
                }

                try {
                    String image = jsonObject.getString("image");
                    result.setImage(image);
                } catch (JSONException e) {
                    Log.e("Parse image", e.toString());
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
