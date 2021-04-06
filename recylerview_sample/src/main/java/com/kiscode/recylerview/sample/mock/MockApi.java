package com.kiscode.recylerview.sample.mock;


import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kiscode.recylerview.sample.model.Automobile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/****
 * ProjectName: AndroidUISample
 * Package: com.kiscode.recylerview.sample.mock
 * ClassName: MockApi
 * Description: Mock datas
 * Author:  Administrator
 * CreateDate: 2020/2/17 9:54
 */

public class MockApi {

    public static List<String> getMockDatas() {
        List<String> datas = new ArrayList<>();
        for (int i = 'A'; i < 'z'; i++) {
            datas.add("No." + i);
        }
        return datas;
    }

    public static List<String> getMockNumberDatas() {
        List<String> datas = new ArrayList<>();
        for (int i = 1; i < 500; i++) {
            datas.add("No. " + i);
        }
        return datas;
    }

    public static List<Automobile> getMockAutoMobile(Context context) {
        try {
            InputStream inputStream = context.getAssets().open("automobile.json");
            StringBuilder sb = new StringBuilder();
            String line;

            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            Gson gson = new Gson();
            return gson.fromJson(sb.toString(), new TypeToken<List<Automobile>>() {
            }.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }
}
