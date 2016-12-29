package com.karyasarma.android.exception;

/**
 *
 * @author Daniel Joi Partogi Hutapea
 */
public class ApplicationException extends Exception
{
    public ApplicationException()
    {
    }

    public ApplicationException(String message)
    {
        super(message);
    }

    public ApplicationException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public ApplicationException(Throwable cause)
    {
        super(cause);
    }
}
