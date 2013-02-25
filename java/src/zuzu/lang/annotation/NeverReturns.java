package zuzu.lang.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation used to denote methods that will never return normally, i.e. methods that always throw
 * an exception, exit the program, or run an infinite loop.
 */
@ExternalAnnotation
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface NeverReturns
{
}
