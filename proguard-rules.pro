## 保留ocrSdk相关类和接口
#-keep class com.sg.ocr.** { *; }
#
### 保留 OkHttp3 相关类和接口
#-keep class com.squareup.okhttp.** { *; }
#-keep interface com.squareup.okhttp.** { *; }
#-keep class okhttp3.** { *; }
#-keep interface okhttp3.** { *; }
#
## 保留 Okio 相关类和接口，OkHttp 依赖 Okio
#-keep class okio.** { *; }
#-keep interface okio.** { *; }
#
## 保留 Gson 相关类和接口
#-keep class com.google.gson.** { *; }
#-keep interface com.google.gson.** { *; }
#
## 保留 Gson 所需的反射属性
#-keepattributes Signature
#-keepattributes *Annotation*
#-keep class sun.misc.Unsafe { *; }
#-keep class com.google.gson.stream.** { *; }
##
### 保留 com.google.mlkit 相关类和接口
#-keep class com.google.mlkit.** { *; }
#-keep interface com.google.mlkit.** { *; }
