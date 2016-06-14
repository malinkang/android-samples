package com.malinkang.zhihudaily.presenter;

import android.support.annotation.NonNull;

import com.malinkang.zhihudaily.StoryListView;
import com.malinkang.zhihudaily.domain.Story;
import com.malinkang.zhihudaily.domain.exception.DefaultErrorBundle;
import com.malinkang.zhihudaily.domain.exception.ErrorBundle;
import com.malinkang.zhihudaily.domain.interactor.DefaultSubscriber;
import com.malinkang.zhihudaily.domain.interactor.UseCase;
import com.malinkang.zhihudaily.exception.ErrorMessageFactory;
import com.malinkang.zhihudaily.internal.di.PerActivity;
import com.malinkang.zhihudaily.mapper.StoryModelDataMapper;
import com.malinkang.zhihudaily.model.StoryModel;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

@PerActivity
public class StoryListPresenter implements Presenter {

  private StoryListView viewListView;

  private final UseCase<String> getUserListUseCase;
  private final StoryModelDataMapper storyModelDataMapper;

  @Inject
  public StoryListPresenter(@Named("userList") UseCase<String> getUserListUserCase,
                            StoryModelDataMapper storyModelDataMapper) {
    this.getUserListUseCase = getUserListUserCase;
    this.storyModelDataMapper = storyModelDataMapper;
  }

  public void setView(@NonNull StoryListView view) {
    this.viewListView = view;
  }

  @Override public void resume() {}

  @Override public void pause() {}

  @Override public void destroy() {
    this.getUserListUseCase.unsubscribe();
    this.viewListView = null;
  }

  /**
   * Initializes the presenter by start retrieving the user list.
   */
  public void initialize(String date) {
    this.loadUserList(date);
  }

  /**
   * Loads all users.
   */
  private void loadUserList(String date) {
    this.hideViewRetry();
    this.showViewLoading();
    this.getUserList(date);
  }

  public void onUserClicked(StoryModel storyModel) {
    this.viewListView.viewStory(storyModel);
  }

  private void showViewLoading() {
    this.viewListView.showLoading();
  }

  private void hideViewLoading() {
    this.viewListView.hideLoading();
  }

  private void showViewRetry() {
    this.viewListView.showRetry();
  }

  private void hideViewRetry() {
    this.viewListView.hideRetry();
  }

  private void showErrorMessage(ErrorBundle errorBundle) {
    String errorMessage = ErrorMessageFactory.create(this.viewListView.context(),
        errorBundle.getException());
    this.viewListView.showError(errorMessage);
  }

  private void showUsersCollectionInView(List<Story> userList) {
    final List<StoryModel> userModelsCollection =
        this.storyModelDataMapper.transform(userList);
    this.viewListView.renderStoryList(userModelsCollection);
  }

  private void getUserList(String date) {
    this.getUserListUseCase.execute(new UserListSubscriber(),date);
  }

  private final class UserListSubscriber extends DefaultSubscriber<List<Story>> {

    @Override public void onCompleted() {
      StoryListPresenter.this.hideViewLoading();
    }

    @Override public void onError(Throwable e) {
      StoryListPresenter.this.hideViewLoading();
      StoryListPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
      StoryListPresenter.this.showViewRetry();
    }

    @Override public void onNext(List<Story> storyList) {

      StoryListPresenter.this.showUsersCollectionInView(storyList);
    }
  }
}