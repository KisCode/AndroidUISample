package com.kiscode.recylerview.sample.mock;


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
        for (int i = 1; i < 200; i++) {
            datas.add("No. " + i);
        }
        return datas;
    }
}
