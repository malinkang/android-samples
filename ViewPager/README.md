
## FragmentStatePagerAdapter
> This version of the pager is more useful when there are a large number of pages, working more like a list view. When pages are not visible to the user, their entire fragment may be destroyed, only keeping the saved state of that fragment. This allows the pager to hold on to much less memory associated with each visited page as compared to FragmentPagerAdapter at the cost of potentially more overhead when switching between pages.

当存在大量页面时，此版本的分页器更有用。当页面对用户不可见时，它们的整个`fragment`可能会被销毁，仅保留该`fragment`的保存状态。与FragmentPagerAdapter相比，这使pager可以保留与每个访问的页面关联的更少的内存，但在页面之间进行切换时可能会产生更多开销。


## FragmentStatePagerAdapter

> this version of the pager is best for use when there are a handful of typically more static fragments to be paged through, such as a set of tabs. The fragment of each page the user visits will be kept in memory, though its view hierarchy may be destroyed when not visible.

This can result in using a significant amount of memory since fragment instances can hold on to an arbitrary amount of state.  For larger sets of pages, consider FragmentStatePagerAdapter.


此版本的分页器最适合用于少数几个通常要通过页面分页的静态fragment，例如一组选项卡。用户访问的每个页面的fragment将保留在内存中，尽管其视图层次结构在不可见时可能会被销毁。由于fragment实例可以保持任意数量的状态，因此这可能导致使用大量内存。对于较大的页面集，请考虑FragmentStatePagerAdapter。
