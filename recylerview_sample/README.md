# Recylerview的使用-满足各种列表需求
- Recylerview的基本使用
- 通用Adapter的封装
- 通过实现顶部悬停StickDecoration





## 1. Recylerview的基本使用
```java
 //1. 设置布局管理器 LinearLayoutManager线性布局管理器，支持横向、纵向
//recyclerView.setLayoutManager(new LinearLayoutManager(this));
//设置布局管理器 GridLayoutManager网格布局管理
recyclerView.setLayoutManager(new GridLayoutManager(this, 1));

//设置布局管理器 GridLayoutManager网格布局管理
//纵向 3列
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
//3行
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(10, StaggeredGridLayoutManager.HORIZONTAL));

//2. 设置adapter
mAdapter = new HomeAdapter();
recyclerView.setAdapter(mAdapter);

//3. 设置分割线 DividerItemDecoration为系统自带分割线
DividerItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
//设置分割线drawable，可自定义样式
itemDecoration.setDrawable(getDrawable(R.drawable.shape_divider));
recyclerView.addItemDecoration(itemDecoration);

//4. 设置动画
recyclerView.setItemAnimator(new DefaultItemAnimator());
```
