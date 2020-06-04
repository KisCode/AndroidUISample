package com.kiscode.recylerview.sample.comman;


import androidx.annotation.LayoutRes;

/****
 * ProjectName: AndroidUISample
 * Package: com.kiscode.recylerview.sample.comman
 * ClassName: MutipleItemSupport
 * Description: MutipleItemSupport interface
 * Author:  Administrator
 * CreateDate: 2020/2/17 10:03
 */

public interface MutipleItemSupport<T> {
    @LayoutRes
    int getItemLayout(int itemType);

    int getItemViewType(int position);
}
