package bike.pl.bikemap.aspects;

import android.util.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.concurrent.TimeUnit;

/**
 * Created by Kacper on 2017-02-17.
 */
@Aspect
public class ProfilerAspect {

    @Around("execution( * bike.pl.bikemap.network.JsonParser.parseNetwork")
    public void timer(ProceedingJoinPoint joinPoint) throws  Throwable{
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        long startTime;
        long endTime;
        long elapsedTime;
        startTime = System.nanoTime();
        joinPoint.proceed();
        endTime = System.nanoTime();
        elapsedTime = endTime - startTime;
        Log.d("Profiler",methodSignature.getName() + " time: " + elapsedTime +"ns");
    }
}
