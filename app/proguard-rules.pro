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

#忽略 libiary 混淆
#忽略 libiary 混淆
-keep class org.dom4j.**{*;}
-keep class org.apache.**{*;}
-keep class org.openxmlformats.schemas.**{*;}
-keep class schemasMicrosoftComVml.**{*;}
-keep class schemasMicrosoftComOfficeOffice.**{*;}
-keep class schemasMicrosoftComOfficeExcel.**{*;}
-keep class schemaorg_apache_xmlbeans.**{*;}
-keep class javax.xml.**{*;}
-keep class org.w3c.dom.**{*;}
-keep class repackage.**{*;}


#-libraryjars libs/dom4j-1.6.1.jar
#-libraryjars libs/poi-3.9.jar
#-libraryjars libs/poi-ooxml-3.9.jar
#-libraryjars libs/poi-ooxml-schemas-3.9.jar
#-libraryjars libs/stax-api-1.0.1.jar
#-libraryjars libs/xmlbeans-2.3.0.jar