/*
 * Copyright (C) 2009 Teleca Poland Sp. z o.o. <android@teleca.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.malinkang.audiotrack;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Handler;

import java.io.IOException;

/**
 * Player core engine allowing playback, in other words, a wrapper around
 *
 * @author Lukasz Wisniewski
 */
public class PlayerEngineImpl implements PlayerEngine {


    private static final long FAIL_TIME_FRAME = 1000;

    private static final int ACCEPTABLE_FAIL_NUMBER = 2;

    private long mLastFailTime;

    private long mTimesFailed;

    private class InternalMediaPlayer extends MediaPlayer {

        private String audioUrl;

        private boolean preparing = false;

    }

    private InternalMediaPlayer mCurrentMediaPlayer;

    private PlayerEngineListener mPlayerEngineListener;

    private final Handler mHandler;

    private final Runnable mUpdateTimeTask = new Runnable() {
        @Override
        public void run() {
            if (mPlayerEngineListener != null) {
                if (mCurrentMediaPlayer != null)
                    mPlayerEngineListener.onTrackProgress(mCurrentMediaPlayer
                            .getCurrentPosition());
                mHandler.postDelayed(this, 1000);
            }
        }
    };

    public PlayerEngineImpl() {
        mLastFailTime = 0;
        mTimesFailed = 0;
        mHandler = new Handler();
         int mBufferSize = 2 * AudioTrack.getMinBufferSize(44100,
                AudioFormat.CHANNEL_OUT_STEREO, AudioFormat.ENCODING_PCM_16BIT);
        AudioTrack  mAudioTrack = new AudioTrack(AudioManager.STREAM_MUSIC, 44100,
                AudioFormat.CHANNEL_OUT_STEREO, AudioFormat.ENCODING_PCM_16BIT,
                mBufferSize, AudioTrack.MODE_STREAM);
    }

    // 暂停
    @Override
    public void pause() {
        if (mCurrentMediaPlayer != null) {
            // 正在准备
            if (mCurrentMediaPlayer.preparing) {
                return;
            }
            if (mCurrentMediaPlayer.isPlaying()) {
                mCurrentMediaPlayer.pause();
                if (mPlayerEngineListener != null)
                    mPlayerEngineListener.onTrackPause();
                return;
            }
        }
    }

    // 播放
    @Override
    public void play(String url) {

        if (url != null) {
            //
            if (mCurrentMediaPlayer == null) {
                mCurrentMediaPlayer = build(url);
            }
            //
            if (mCurrentMediaPlayer == null)
                return;
            if (!mCurrentMediaPlayer.preparing) {//没有正在准备
                if (!mCurrentMediaPlayer.isPlaying()) {
                    //没有在播放
                    mPlayerEngineListener.onTrackStart();
                    mHandler.removeCallbacks(mUpdateTimeTask);
                    mHandler.postDelayed(mUpdateTimeTask, 10);
                    mCurrentMediaPlayer.start();
                } else {
                    mHandler.removeCallbacks(mUpdateTimeTask);
                    pause();
                }
            }
        }
    }

    @Override
    public void stop() {
        cleanUp();
        if (mPlayerEngineListener != null) {
            // 停止播放
            mPlayerEngineListener.onTrackStop();

        }
    }

    private void cleanUp() {
        if (mCurrentMediaPlayer != null) {
            try {
                mCurrentMediaPlayer.stop();
            } catch (IllegalStateException e) {

            } finally {
                mCurrentMediaPlayer.release();
                mCurrentMediaPlayer = null;
            }
        }
    }

    // 构建MediaPlayer
    private InternalMediaPlayer build(final String url) {
        final InternalMediaPlayer mediaPlayer = new InternalMediaPlayer();

        try {
            mediaPlayer.setDataSource(url);

            mediaPlayer.audioUrl = url;

            mediaPlayer.setOnCompletionListener(new OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    stop();
                }
            });

            mediaPlayer.setOnPreparedListener(new OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mediaPlayer.preparing = false;
                    if (mPlayerEngineListener != null) {
                        // 准备完成
                        mPlayerEngineListener.onTrackPrepareFinish();
                    }
                    play(url);

                }

            });
            mediaPlayer
                    .setOnBufferingUpdateListener(new OnBufferingUpdateListener() {
                        @Override
                        public void onBufferingUpdate(MediaPlayer mp,
                                                      int percent) {
                            if (mPlayerEngineListener != null) {
                                mPlayerEngineListener.onTrackBuffering(percent);
                            }
                        }

                    });

            mediaPlayer.setOnErrorListener(new OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mp, int what, int extra) {

                    if (what == MediaPlayer.MEDIA_ERROR_UNKNOWN) {
                        // we probably lack network
                        if (mPlayerEngineListener != null) {
                            mPlayerEngineListener.onTrackStreamError();
                        }
                        stop();
                        return true;
                    }

                    if (what == -1) {
                        long failTime = System.currentTimeMillis();
                        if (failTime - mLastFailTime > FAIL_TIME_FRAME) {
                            // outside time frame
                            mTimesFailed = 1;
                            mLastFailTime = failTime;

                        } else {
                            // inside time frame
                            mTimesFailed++;
                            if (mTimesFailed > ACCEPTABLE_FAIL_NUMBER) {
                                stop();
                                return true;
                            }
                        }
                    }
                    return false;
                }
            });

            mediaPlayer.preparing = true;
            mediaPlayer.prepareAsync();
            return mediaPlayer;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean isPlaying() {
        if (mCurrentMediaPlayer == null)
            return false;
        if (mCurrentMediaPlayer.preparing)
            return false;
        return mCurrentMediaPlayer.isPlaying();
    }

    @Override
    public void setListener(PlayerEngineListener playerEngineListener) {
        mPlayerEngineListener = playerEngineListener;
    }

    @Override
    public void seekTo(int progress) {
        if (mCurrentMediaPlayer != null)
            mCurrentMediaPlayer.seekTo(progress);
    }

}
