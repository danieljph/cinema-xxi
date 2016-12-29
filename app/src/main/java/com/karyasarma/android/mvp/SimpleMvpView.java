package com.karyasarma.android.mvp;

import com.hannesdorfmann.mosby.mvp.MvpView;
import com.karyasarma.cinemaxxi.model.MovieSetting;

import java.util.List;

/**
 *
 * @author Daniel Joi Partogi Hutapea
 */
public interface SimpleMvpView<M> extends MvpView
{
    public void loadData();
    public void showLoading(boolean isLoading);
    public void showContent(M data);
    public void showError(Throwable ex);
}
