// Generated code from Butter Knife. Do not modify!
package com.mamating.fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class GuideFragment$$ViewInjector {
  public static void inject(Finder finder, final com.mamating.fragment.GuideFragment target, Object source) {
    View view;
    view = finder.findById(source, 2131034181);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131034181' for field 'guideImageView' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.guideImageView = (android.widget.ImageView) view;
    view = finder.findById(source, 2131034182);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131034182' for field 'enterBtn' and method 'enterLogin' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.enterBtn = (android.widget.Button) view;
    view.setOnClickListener(
      new android.view.View.OnClickListener() {
        @Override public void onClick(
          android.view.View p0
        ) {
          target.enterLogin();
        }
      });
  }

  public static void reset(com.mamating.fragment.GuideFragment target) {
    target.guideImageView = null;
    target.enterBtn = null;
  }
}
