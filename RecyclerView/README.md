#### 知识点

1.基本使用
2.Adapter抽象
3.分页
4.ExpandableRecyclerView
5.拖拽滑动删除
6.StickyHeader
7.自定义LayoutManager
8.DataBinding
9.Nest

#### 笔记 草稿

##### 自定义LayoutManager

开始自定义LayoutManager之前先了解LayoutManager是什么作用。

一个LayoutManager负责测量和定位RecyclerView中的item views以及检测何时回收不再对用户显示的item views.

实现自定义LayoutManager，只需要继承LayoutManager并实现抽象方法generateDefaultLayoutParams即可。generateDefaultLayoutParams方法为每个item view提供参数，
在RecyclerView的getViewForPosition方法中被调用。
```java
 View getViewForPosition(int position, boolean dryRun) {
 //...
    final ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
    final LayoutParams rvLayoutParams;
    if (lp == null) {
        rvLayoutParams = (LayoutParams) generateDefaultLayoutParams();
        holder.itemView.setLayoutParams(rvLayoutParams);
    } else if (!checkLayoutParams(lp)) {
        rvLayoutParams = (LayoutParams) generateLayoutParams(lp);
        holder.itemView.setLayoutParams(rvLayoutParams);
    } else {
        rvLayoutParams = (LayoutParams) lp;
    }
    rvLayoutParams.mViewHolder = holder;
    rvLayoutParams.mPendingInvalidate = fromScrap && bound;
    return holder.itemView;
}
```
Recycler 有两级视图缓存系统： scrap heap 和 recycle pool (垃圾堆和回收池)，
Scrap heap 是一个轻量的集合，视图可以不经过适配器直接返回给 LayoutManager 。
通常被 detach 但会在同一布局重新使用的视图会临时储存在这里。Recycle pool 存放的
是那些假定并没有得到正确数据(相应位置的数据)的视图， 因此它们都要经过适配器重新绑定后才能返回给 LayoutManager。

当要给 LayoutManager 提供一个新 view 时，Recycler 首先会 检查 scrap heap 有没有对应的 position/id；如果有对应的内容，
就直接返回数据不需要通过适配器重新绑定。如果没有的话， Recycler 就会从 recycle pool 里弄一个合适的视图出来，
然后用 adapter 给它绑定必要的数据 (就是调用 RecyclerView.Adapter.bindViewHolder()) 再返回。
如果 recycle pool 中也不存在有效 view ，就会在绑定数据前 创建新的 view (就是 RecyclerView.Adapter.createViewHolder())， 最后返回数据。



detachAndScrapAttachedViews
使用指定recycler临时分离和回收所有当前所有attach的子view。
#### 文章
* [Drag and Swipe with RecyclerView](https://medium.com/@ipaulpro/drag-and-swipe-with-recyclerview-b9456d2b1aaf#.hr36a0wfy)
* [深入理解Android 自定义attr Style styleable以及其应用](http://www.jianshu.com/p/61b79e7f88fc)
* [深入理解 RecyclerView 系列之一：ItemDecoration](https://blog.piasy.com/2016/03/26/Insight-Android-RecyclerView-ItemDecoration/)
* [创建一个 RecyclerView LayoutManager – Part 1](https://github.com/hehonghui/android-tech-frontier/blob/master/issue-9/%E5%88%9B%E5%BB%BA-RecyclerView-LayoutManager-Part-1.md)

#### 开源库

* [HiveLayoutManager](https://github.com/Chacojack/HiveLayoutManager)
* [ZLayoutManager](https://github.com/mcxtzhang/ZLayoutManager)
* [twoway-view](https://github.com/lucasr/twoway-view)
* [greedo-layout-for-android](https://github.com/500px/greedo-layout-for-android)
* [RecyclerViewSnap](https://github.com/rubensousa/RecyclerViewSnap)