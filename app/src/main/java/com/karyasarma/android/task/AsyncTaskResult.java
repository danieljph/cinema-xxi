package com.karyasarma.android.task;

/**
 *
 * @author Daniel Joi Partogi Hutapea
 */
public class AsyncTaskResult<T>
{
    private T data;
    private Exception exception;

    public AsyncTaskResult(T data)
    {
        this.data = data;
    }

    public AsyncTaskResult(T data, Exception exception)
    {
        this.data = data;
        this.exception = exception;
    }

    public boolean isError()
    {
        return exception!=null;
    }

    public T getData()
    {
        return data;
    }

    public void setData(T data)
    {
        this.data = data;
    }

    public Exception getException()
    {
        return exception;
    }

    public void setException(Exception exception)
    {
        this.exception = exception;
    }
}
