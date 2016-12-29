package com.karyasarma.android.task;

/**
 *
 * @author Daniel Joi Partogi Hutapea
 */
public interface AsyncTaskListener<T>
{
    void onSuccess(T data);
    void onError(Exception ex);
}
