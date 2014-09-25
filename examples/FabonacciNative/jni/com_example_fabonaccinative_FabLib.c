/* Include the header file that was created via "javah -jni" command */
#include "com_example_fabonaccinative_FabLib.h"
#include <android/log.h>

/* Recursive implementation of the fibonacci algorithm (in a helper function) */
static jlong fib(jlong n) {
    return n <= 0 ? 0 : n == 1 ? 1 : fib(n - 1) + fib(n - 2);
}

/* Actual implementation of JNI-defined `fibNR` (recursive) function */
JNIEXPORT jlong JNICALL Java_com_example_fabonaccinative_FabLib_fibNR
  (JNIEnv *env, jclass clazz, jlong n) {
	__android_log_print(ANDROID_LOG_DEBUG, "FibLib.c", "fibNR(%lld)", n);
	return fib(n);
}

/* Actual implementation of JNI-defined `fibNI` (iterative) function */
JNIEXPORT jlong JNICALL Java_com_example_fabonaccinative_FabLib_fibNI
  (JNIEnv *env, jclass clazz, jlong n) {
	jlong previous = -1;
	jlong result = 1;
	jlong i;
	__android_log_print(ANDROID_LOG_DEBUG, "FibLib.c", "fibNI(%lld)", n);
	for (i = 0; i <= n; i++) {
		jlong sum = result + previous;
		previous = result;
		result = sum;
	}
	return result;
}
