package keno.android.ui.sample.constraint;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import keno.android.ui.sample.R;

/***
 * 3个TextView相互约束，两端两个TextView分别与parent约束，成为一条链，效果如下：
 *
 *
 * 一条链的第一个控件是这条链的链头，我们可以在链头中设置 layout_constraintHorizontal_chainStyle来改变整条链的样式。chains提供了3种样式，分别是：
 * CHAIN_SPREAD —— 展开元素 (默认)；
 * CHAIN_SPREAD_INSIDE —— 展开元素，但链的两端贴近parent；
 * CHAIN_PACKED —— 链的元素将被打包在一起。
 *
 */
public class ChainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chain);
    }
}
