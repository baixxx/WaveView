# WaveLineView
## 波浪,波浪线,分贝效果,贝塞尔曲线实现


## 效果图如下:
![image]](https://github.com/baixxx/WaveView/tree/master/resource/waveview.gif)  



## 自定义属性
| Attribute属性    | Description描述 |
| :----------: | :-----------:  | 
| mWaveTime	 | 动画时间,控制波浪速度 |
| mWaveColor	| 波浪颜色  |
| isWaveLine | 是否为波浪线  |



## xml文件引用
```
   <com.bx.waveview.WaveView
        android:id="@+id/waveview"
        android:layout_width="match_parent"
        android:layout_height="100dp"
        app:mWaveTime="1000"
        app:mWaveColor="@color/colorAccent"
        app:isWaveLine="false"
        />
```

## 集成

### 第 1 步、在工程的 build.gradle 中添加：
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
### 第 2 步、在应用的 build.gradle 中添加：
```
dependencies {
	        implementation 'com.github.baixxx:WaveView:master'
	}
 ```
 
[![](https://jitpack.io/v/baixxx/WaveView.svg)](https://jitpack.io/#baixxx/WaveView)
