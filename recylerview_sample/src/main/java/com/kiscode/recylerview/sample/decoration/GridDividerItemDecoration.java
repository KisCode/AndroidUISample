package com.kiscode.recylerview.sample.decoration;


import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**** @ProjectName: AndroidUISample
 * @Package: com.kiscode.recylerview.sample
 * @ClassName: GridDividerItemDecoration
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2020/2/13 21:46
 * @UpdateUser: 更新者： 
 * @UpdateDate: 2020/2/13 21:46
 * @UpdateRemark: 更新说明： 
 * @Version: 1.0
 */

public class GridDividerItemDecoration extends RecyclerView.ItemDecoration {
//    onDraw方法先于drawChildren,在Canvas上绘制内容，在绘制Item之前调用
//    onDrawOver在drawChildren之后，一般我们选择复写其中一个即可。
//    getItemOffsets 可以通过outRect.set()为每个Item设置一定的偏移量，主要用于绘制Decorator。

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        //设置分割线四个方向的 距离
        outRect.set(10, 10, 10, 10);
    }
}
