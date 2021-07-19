# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# 排除所有序列化类混淆
-keep class * implements java.io.Serializable {*;}
-keep class * implements android.os.Parcelable {*;}

# 注意事项：
#
# ① jni方法不可混淆，方法名需与native方法保持一致；
# ② 反射用到的类不混淆，否则反射可能出问题；
# ③ 四大组件、Application子类、Framework层下的类、自定义的View默认不会被混淆，无需另外配置；
# ④ WebView的JS调用接口方法不可混淆；
# ⑤ 注解相关的类不混淆；
# ⑥ GSON、Fastjson等解析的Bean数据类不可混淆；
# ⑦ 枚举enum类中的values和valuesof这两个方法不可混淆(反射调用)；
# ⑧ 继承Parceable和Serializable等可序列化的类不可混淆；
# ⑨ 第三方库或SDK，请参考第三方提供的混淆规则，没提供的话，建议第三方包全部不混淆；
