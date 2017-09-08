依赖加入：
compile 'com.github.bumptech.glide:okhttp3-integration:1.4.0@aar'
compile 'com.squareup.retrofit2:retrofit:2.0.2'
compile 'com.google.code.gson:gson:2.6'

示例：

compile 'com.google.code.gson:gson:2.6.2'  
compile 'com.squareup.retrofit2:retrofit:2.0.2'  
compile 'com.squareup.retrofit2:converter-gson:2.0.2'    
compile 'com.squareup.okhttp3:okhttp:3.2.0'  
一般使用Retrofit还会使用它和RxJava配套使用，因此还需要添加如下依赖  
compile 'io.reactivex:rxjava:1.1.3'  
compile 'io.reactivex:rxandroid:1.1.0'  
compile 'com.squareup.retrofit2:adapter-rxjava:2.0.2'  
为了使用更多的java注解添加下面的依赖  
provided 'org.glassfish:javax.annotation:10.0-b28' 