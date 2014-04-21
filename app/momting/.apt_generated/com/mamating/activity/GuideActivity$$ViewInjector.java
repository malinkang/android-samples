// Generated code from Butter Knife. Do not modify!
package com.mamating.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class GuideActivity$$ViewInjector {
  public static void inject(Finder finder, final com.mamating.activity.GuideActivity target, Object source) {
    View view;
    view = finder.findById(source, 2131034178);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131034178' for field 'mGuideIndicator' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.mGuideIndicator = (com.viewpagerindicator.CirclePageIndicator) view;
    view = finder.findById(source, 2131034177);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131034177' for field 'mViewPager' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.mViewPager = (android.support.v4.view.ViewPager) view;
  }

  public static void reset(com.mamating.activity.GuideActivity target) {
    target.mGuideIndicator = null;
    target.mViewPager = null;
  }
}
