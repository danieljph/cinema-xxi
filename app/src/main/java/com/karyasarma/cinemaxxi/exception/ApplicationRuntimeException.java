package com.karyasarma.cinemaxxi.exception;

/**
 *
 * @author Daniel Joi Partogi Hutapea
 */
public class ApplicationRuntimeException extends RuntimeException
{
    public ApplicationRuntimeException()
    {
    }

    public ApplicationRuntimeException(String message)
    {
        super(message);
    }

    public ApplicationRuntimeException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public ApplicationRuntimeException(Throwable cause)
    {
        super(cause);
    }
}
