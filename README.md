### gson-datetime-adapters

适用于`Gson`的一些处理时间日期的适配器。

#### 安装方法
当前最新版本号：[![](https://jitpack.io/v/cn.numeron/gson-datetime-adapters.svg)](https://jitpack.io/#cn.numeron/gson-datetime-adapters)

1.  在你的android工程的根目录下的build.gradle文件中的适当的位置添加以下代码：
```groovy
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```
2.  在你的android工程中对应的android模块的build.gradle文件中的适当位置添加以下代码：
```groovy
implementation 'cn.numeron:gson-datetime-adapters:latest_version'
```

#### 使用方法
* 在构建`Gson`时候，创建`XXXXTypeAdapter`的实例并传入`GsonBuilder`中即可。
* 默认只支持`ISO标准`的日期、时间格式，如果需要支持多种时间格式，可以自定义并传入构造方法中的`Converter`接口。