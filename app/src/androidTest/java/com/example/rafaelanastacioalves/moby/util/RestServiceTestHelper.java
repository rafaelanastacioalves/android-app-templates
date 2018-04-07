package com.example.rafaelanastacioalves.moby.util;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by rafaelanastacioalves on 28/03/18.
 */

public class RestServiceTestHelper {
    public static String getStringFromFile(Context context, String filpePath) throws IOException {
        final InputStream stream = context.getResources().getAssets().open(filpePath);
        String ret = convertStreamToString(stream);
        stream.close();
        return ret;
    }

    private static String convertStreamToString(InputStream is) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        reader.close();
        return sb.toString();    }
}
