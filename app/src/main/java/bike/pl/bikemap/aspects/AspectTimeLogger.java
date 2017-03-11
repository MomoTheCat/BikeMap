package bike.pl.bikemap.aspects;

import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by szymon on 11.03.2017.
 */


@Aspect
public class AspectTimeLogger {
    private static final String TAG = "Aspect";

    @Pointcut("execution(* bike.pl.bikemap.MainActivity.onCreate(..))")
    public void onCreateMethod() {
    }

    @Before("onCreateMethod()")
    public void doBeforeOnCreate(JoinPoint joinPoint) {
        Log.i(TAG, "AspectJ was here");
    }

    @Around("execution(* *(..)) && @annotation(LogAspect)")
    public Object around(ProceedingJoinPoint point) {
        long start = System.currentTimeMillis();
        Object result = null;
        try {
            result = point.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        Log.d(TAG, "#" + point.getSignature() + "  in: " + (System.currentTimeMillis() - start) + "ms");
        return result;
    }
}
