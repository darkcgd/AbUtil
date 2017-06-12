/*
 * Copyright (C) 2012 www.amsoft.cn
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.darkcgd.library;


import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

/**
 * 名称：AbAnimationUtil.java
 * 描述：动画工具类.
 *
 * @author cgd
 * @date 2016-01-11
 */
public class AbAnimationUtil {
	
	/** 定义动画的时间. */
	public final static long aniDurationMillis = 1L;

	/**
	 * 用来改变当前选中区域的放大动画效果
	 * 从1.0f放大1.2f倍数
	 *
	 * @param view the view
	 * @param scale the scale
	 */
	public static void largerView(View view, float scale) {
		if (view == null)
			return;

		// 置于所有view最上层
		view.bringToFront();
		int width = view.getWidth();
		float animationSize = 1 + scale / width;
		scaleView(view, animationSize);
	}

	/**
	 * 用来还原当前选中区域的还原动画效果.
	 *
	 * @param view the view
	 * @param scale the scale
	 */
	public static void restoreLargerView(View view, float scale) {
		if (view == null)
			return;
		int width = view.getWidth();
		float toSize = 1 + scale / width;
		// 从1.2f缩小1.0f倍数
		scaleView(view, -1 * toSize);
	}

	/**
	 * 缩放View的显示.
	 *
	 * @param view 需要改变的View
	 * @param toSize 缩放的大小，其中正值代表放大，负值代表缩小，数值代表缩放的倍数
	 */
	private static void scaleView(final View view, float toSize) {
		ScaleAnimation scale = null;
		if (toSize == 0) {
			return;
		} else if (toSize > 0) {
			scale = new ScaleAnimation(1.0f, toSize, 1.0f, toSize,
					Animation.RELATIVE_TO_SELF, 0.5f,
					Animation.RELATIVE_TO_SELF, 0.5f);
		} else {
			scale = new ScaleAnimation(toSize * (-1), 1.0f, toSize * (-1),
					1.0f, Animation.RELATIVE_TO_SELF, 0.5f,
					Animation.RELATIVE_TO_SELF, 0.5f);
		}
		scale.setDuration(aniDurationMillis);
		scale.setInterpolator(new AccelerateDecelerateInterpolator());
		scale.setFillAfter(true);
		view.startAnimation(scale);
	}
	
	/**
	 * 跳动-跳起动画.
	 *
	 * @param view the view
	 * @param offsetY the offset y
	 */
    private void playJumpAnimation(final View view,final float offsetY) {
        float originalY = 0;
        float finalY = - offsetY;
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(new TranslateAnimation(0, 0, originalY,finalY));
        animationSet.setDuration(300);
        animationSet.setInterpolator(new AccelerateDecelerateInterpolator());
        animationSet.setFillAfter(true);

        animationSet.setAnimationListener(new AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                playLandAnimation(view,offsetY);
            }
        });

        view.startAnimation(animationSet);
    }

    /**
     * 跳动-落下动画.
     *
     * @param view the view
     * @param offsetY the offset y
     */
    private void playLandAnimation(final View view,final float offsetY) {
        float originalY =  - offsetY;
        float finalY = 0;
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(new TranslateAnimation(0, 0, originalY,finalY));
        animationSet.setDuration(200);
        animationSet.setInterpolator(new AccelerateInterpolator());
        animationSet.setFillAfter(true);

        animationSet.setAnimationListener(new AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //两秒后再调
                view.postDelayed(new Runnable(){
                    
                    @Override
                    public void run(){
                        playJumpAnimation(view,offsetY);
                    }
                }, 2000);
            }
        });

        view.startAnimation(animationSet);
    }
    
    /**
     * 旋转动画
     * @param v
     * @param durationMillis
     * @param repeatCount  Animation.INFINITE
     * @param repeatMode  Animation.RESTART
     */
    public static void playRotateAnimation(View v,long durationMillis,int repeatCount,int repeatMode) {
    	
        //创建AnimationSet对象
        AnimationSet animationSet = new AnimationSet(true);
        //创建RotateAnimation对象
        RotateAnimation rotateAnimation = new RotateAnimation(0f,+360f, 
					Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF, 0.5f);
        //设置动画持续
        rotateAnimation.setDuration(durationMillis);
        rotateAnimation.setRepeatCount(repeatCount);
        //Animation.RESTART
        rotateAnimation.setRepeatMode(repeatMode);
        //动画插入器
        rotateAnimation.setInterpolator(v.getContext(), android.R.anim.decelerate_interpolator);
        //添加到AnimationSet
        animationSet.addAnimation(rotateAnimation);
        
        //开始动画 
        v.startAnimation(animationSet);
	}













	/**
	 * 默认动画持续时间
	 */
	public static final long DEFAULT_ANIMATION_DURATION = 400;


	/**
	 * 获取一个旋转动画
	 *
	 * @param fromDegrees       开始角度
	 * @param toDegrees         结束角度
	 * @param pivotXType        旋转中心点X轴坐标相对类型
	 * @param pivotXValue       旋转中心点X轴坐标
	 * @param pivotYType        旋转中心点Y轴坐标相对类型
	 * @param pivotYValue       旋转中心点Y轴坐标
	 * @param durationMillis    持续时间
	 * @param animationListener 动画监听器
	 * @return 一个旋转动画
	 */
	public static RotateAnimation getRotateAnimation(float fromDegrees, float toDegrees, int pivotXType, float pivotXValue, int pivotYType, float pivotYValue, long durationMillis, AnimationListener animationListener) {
		RotateAnimation rotateAnimation = new RotateAnimation(fromDegrees,
				toDegrees, pivotXType, pivotXValue, pivotYType, pivotYValue);
		rotateAnimation.setDuration(durationMillis);
		if (animationListener != null) {
			rotateAnimation.setAnimationListener(animationListener);
		}
		return rotateAnimation;
	}


	/**
	 * 获取一个根据视图自身中心点旋转的动画
	 *
	 * @param durationMillis    动画持续时间
	 * @param animationListener 动画监听器
	 * @return 一个根据中心点旋转的动画
	 */
	public static RotateAnimation getRotateAnimationByCenter(long durationMillis, AnimationListener animationListener) {
		return getRotateAnimation(0f, 359f, Animation.RELATIVE_TO_SELF, 0.5f,
				Animation.RELATIVE_TO_SELF, 0.5f, durationMillis,
				animationListener);
	}


	/**
	 * 获取一个根据中心点旋转的动画
	 *
	 * @param duration 动画持续时间
	 * @return 一个根据中心点旋转的动画
	 */
	public static RotateAnimation getRotateAnimationByCenter(long duration) {
		return getRotateAnimationByCenter(duration, null);
	}


	/**
	 * 获取一个根据视图自身中心点旋转的动画
	 *
	 * @param animationListener 动画监听器
	 * @return 一个根据中心点旋转的动画
	 */
	public static RotateAnimation getRotateAnimationByCenter(AnimationListener animationListener) {
		return getRotateAnimationByCenter(DEFAULT_ANIMATION_DURATION,
				animationListener);
	}


	/**
	 * 获取一个根据中心点旋转的动画
	 *
	 * @return 一个根据中心点旋转的动画，默认持续时间为DEFAULT_ANIMATION_DURATION
	 */
	public static RotateAnimation getRotateAnimationByCenter() {
		return getRotateAnimationByCenter(DEFAULT_ANIMATION_DURATION, null);
	}


	/**
	 * 获取一个透明度渐变动画
	 *
	 * @param fromAlpha         开始时的透明度
	 * @param toAlpha           结束时的透明度都
	 * @param durationMillis    持续时间
	 * @param animationListener 动画监听器
	 * @return 一个透明度渐变动画
	 */
	public static AlphaAnimation getAlphaAnimation(float fromAlpha, float toAlpha, long durationMillis, AnimationListener animationListener) {
		AlphaAnimation alphaAnimation = new AlphaAnimation(fromAlpha, toAlpha);
		alphaAnimation.setDuration(durationMillis);
		if (animationListener != null) {
			alphaAnimation.setAnimationListener(animationListener);
		}
		return alphaAnimation;
	}


	/**
	 * 获取一个透明度渐变动画
	 *
	 * @param fromAlpha      开始时的透明度
	 * @param toAlpha        结束时的透明度都
	 * @param durationMillis 持续时间
	 * @return 一个透明度渐变动画
	 */
	public static AlphaAnimation getAlphaAnimation(float fromAlpha, float toAlpha, long durationMillis) {
		return getAlphaAnimation(fromAlpha, toAlpha, durationMillis, null);
	}


	/**
	 * 获取一个透明度渐变动画
	 *
	 * @param fromAlpha         开始时的透明度
	 * @param toAlpha           结束时的透明度都
	 * @param animationListener 动画监听器
	 * @return 一个透明度渐变动画，默认持续时间为DEFAULT_ANIMATION_DURATION
	 */
	public static AlphaAnimation getAlphaAnimation(float fromAlpha, float toAlpha, AnimationListener animationListener) {
		return getAlphaAnimation(fromAlpha, toAlpha, DEFAULT_ANIMATION_DURATION,
				animationListener);
	}


	/**
	 * 获取一个透明度渐变动画
	 *
	 * @param fromAlpha 开始时的透明度
	 * @param toAlpha   结束时的透明度都
	 * @return 一个透明度渐变动画，默认持续时间为DEFAULT_ANIMATION_DURATION
	 */
	public static AlphaAnimation getAlphaAnimation(float fromAlpha, float toAlpha) {
		return getAlphaAnimation(fromAlpha, toAlpha, DEFAULT_ANIMATION_DURATION,
				null);
	}


	/**
	 * 获取一个由完全显示变为不可见的透明度渐变动画
	 *
	 * @param durationMillis    持续时间
	 * @param animationListener 动画监听器
	 * @return 一个由完全显示变为不可见的透明度渐变动画
	 */
	public static AlphaAnimation getHiddenAlphaAnimation(long durationMillis, AnimationListener animationListener) {
		return getAlphaAnimation(1.0f, 0.0f, durationMillis, animationListener);
	}


	/**
	 * 获取一个由完全显示变为不可见的透明度渐变动画
	 *
	 * @param durationMillis 持续时间
	 * @return 一个由完全显示变为不可见的透明度渐变动画
	 */
	public static AlphaAnimation getHiddenAlphaAnimation(long durationMillis) {
		return getHiddenAlphaAnimation(durationMillis, null);
	}


	/**
	 * 获取一个由完全显示变为不可见的透明度渐变动画
	 *
	 * @param animationListener 动画监听器
	 * @return 一个由完全显示变为不可见的透明度渐变动画，默认持续时间为DEFAULT_ANIMATION_DURATION
	 */
	public static AlphaAnimation getHiddenAlphaAnimation(AnimationListener animationListener) {
		return getHiddenAlphaAnimation(DEFAULT_ANIMATION_DURATION,
				animationListener);
	}


	/**
	 * 获取一个由完全显示变为不可见的透明度渐变动画
	 *
	 * @return 一个由完全显示变为不可见的透明度渐变动画，默认持续时间为DEFAULT_ANIMATION_DURATION
	 */
	public static AlphaAnimation getHiddenAlphaAnimation() {
		return getHiddenAlphaAnimation(DEFAULT_ANIMATION_DURATION, null);
	}


	/**
	 * 获取一个由不可见变为完全显示的透明度渐变动画
	 *
	 * @param durationMillis    持续时间
	 * @param animationListener 动画监听器
	 * @return 一个由不可见变为完全显示的透明度渐变动画
	 */
	public static AlphaAnimation getShowAlphaAnimation(long durationMillis, AnimationListener animationListener) {
		return getAlphaAnimation(0.0f, 1.0f, durationMillis, animationListener);
	}


	/**
	 * 获取一个由不可见变为完全显示的透明度渐变动画
	 *
	 * @param durationMillis 持续时间
	 * @return 一个由不可见变为完全显示的透明度渐变动画
	 */
	public static AlphaAnimation getShowAlphaAnimation(long durationMillis) {
		return getAlphaAnimation(0.0f, 1.0f, durationMillis, null);
	}


	/**
	 * 获取一个由不可见变为完全显示的透明度渐变动画
	 *
	 * @param animationListener 动画监听器
	 * @return 一个由不可见变为完全显示的透明度渐变动画，默认持续时间为DEFAULT_ANIMATION_DURATION
	 */
	public static AlphaAnimation getShowAlphaAnimation(AnimationListener animationListener) {
		return getAlphaAnimation(0.0f, 1.0f, DEFAULT_ANIMATION_DURATION,
				animationListener);
	}


	/**
	 * 获取一个由不可见变为完全显示的透明度渐变动画
	 *
	 * @return 一个由不可见变为完全显示的透明度渐变动画，默认持续时间为DEFAULT_ANIMATION_DURATION
	 */
	public static AlphaAnimation getShowAlphaAnimation() {
		return getAlphaAnimation(0.0f, 1.0f, DEFAULT_ANIMATION_DURATION, null);
	}


	/**
	 * 获取一个缩小动画
	 *
	 * @param durationMillis   时间
	 * @param animationListener  监听
	 * @return 一个缩小动画
	 */
	public static ScaleAnimation getLessenScaleAnimation(long durationMillis, AnimationListener animationListener) {
		ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 0.0f, 1.0f,
				0.0f, ScaleAnimation.RELATIVE_TO_SELF,
				ScaleAnimation.RELATIVE_TO_SELF);
		scaleAnimation.setDuration(durationMillis);
		scaleAnimation.setAnimationListener(animationListener);

		return scaleAnimation;
	}


	/**
	 * 获取一个缩小动画
	 *
	 * @param durationMillis 时间
	 * @return 一个缩小动画
	 */
	public static ScaleAnimation getLessenScaleAnimation(long durationMillis) {
		return getLessenScaleAnimation(durationMillis, null);

	}


	/**
	 * 获取一个缩小动画
	 *
	 * @param animationListener  监听
	 * @return 返回一个缩小的动画
	 */
	public static ScaleAnimation getLessenScaleAnimation(AnimationListener animationListener) {
		return getLessenScaleAnimation(DEFAULT_ANIMATION_DURATION,
				animationListener);

	}


	/**
	 * 获取一个放大动画
	 * @param durationMillis   时间
	 * @param animationListener  监听
	 *
	 * @return 返回一个放大的效果
	 */
	public static ScaleAnimation getAmplificationAnimation(long durationMillis, AnimationListener animationListener) {
		ScaleAnimation scaleAnimation = new ScaleAnimation(0.0f, 1.0f, 0.0f,
				1.0f, ScaleAnimation.RELATIVE_TO_SELF,
				ScaleAnimation.RELATIVE_TO_SELF);
		scaleAnimation.setDuration(durationMillis);
		scaleAnimation.setAnimationListener(animationListener);
		return scaleAnimation;
	}


	/**
	 * 获取一个放大动画
	 *
	 * @param durationMillis   时间
	 *
	 * @return 返回一个放大的效果
	 */
	public static ScaleAnimation getAmplificationAnimation(long durationMillis) {
		return getAmplificationAnimation(durationMillis, null);

	}


	/**
	 * 获取一个放大动画
	 *
	 * @param animationListener  监听
	 * @return 返回一个放大的效果
	 */
	public static ScaleAnimation getAmplificationAnimation(AnimationListener animationListener) {
		return getAmplificationAnimation(DEFAULT_ANIMATION_DURATION,
				animationListener);

	}

}
