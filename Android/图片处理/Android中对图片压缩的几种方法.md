* 作者：Tom on 2017/8/9 17:00
* 邮箱：820159571@qq.com
 
 
开发中对图片处理的几种方法：

# 一、质量压缩
         
 * 质量压缩方法：在保持像素的前提下改变图片的位深及透明度等，来达到压缩图片的目的
 * 1、bitmap图片的大小不会改变
 * 2、bytes.length是随着quality变小而变小的。
 * 这样适合去传递二进制的图片数据，比如分享图片，要传入二进制数据过去，限制500kb之内。
 
## 代码：

    public static Bitmap compressImage(Bitmap image) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		// 把ByteArrayInputStream数据生成图片
		Bitmap bitmap = null;
		image.compress(Bitmap.CompressFormat.JPEG, 5, baos);
		byte[] bytes = baos.toByteArray();
		// 把压缩后的数据baos存放到bytes中
		bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
		if (bitmap != null) {
			loga(bitmap, baos.toByteArray());
		}
		return bitmap;
	}
   
    // 质量压缩方法，options的值是0-100，这里100表示原来图片的质量，不压缩，把压缩后的数据存放到baos中
	image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
	int options = 90;
	// 循环判断如果压缩后图片是否大于500kb,大于继续压缩
	while (baos.toByteArray().length / 1024 > 100) {
		// 重置baos即清空baos
		baos.reset();
		// 这里压缩options%，把压缩后的数据存放到baos中
		image.compress(Bitmap.CompressFormat.JPEG, options, baos);
		// 每次都减少10
		if(options == 1){
			break;
		}else if (options <= 10) {
			options -= 1;
		} else {
			options -= 10;
		}
	}
      
              
# 二、采样率压缩

##   采样率压缩：
  设置inSampleSize的值(int类型)后，假如设为n，则宽和高都为原来的1/n，宽高都减少，内存降低。上面的代码没用过options.inJustDecodeBounds = true; 因为我是固定来取样的数据，为什么这个压缩方法叫采样率压缩？
  是因为配合inJustDecodeBounds，先获取图片的宽、高【这个过程就是取样】，
  然后通过获取的宽高，动态的设置inSampleSize的值。
  当inJustDecodeBounds设置为true的时候，
  BitmapFactory通过decodeResource或者decodeFile解码图片时，
 将会返回空(null)的Bitmap对象，这样可以避免Bitmap的内存分配，
 但是它可以返回Bitmap的宽度、高度以及MimeType。


## 用法：

 int inSampleSize = getScaling(bitmap);
 bitmap = samplingRateCompression(path,inSampleSize);

## 代码：

    private Bitmap samplingRateCompression(String path, int scaling) {

	BitmapFactory.Options options = new BitmapFactory.Options();
	options.inSampleSize = scaling;

	Bitmap bitmap = BitmapFactory.decodeFile(path, options);

	int size = (bitmap.getByteCount() / 1024 / 1024);

	Log.i("wechat", "压缩后图片的大小" + (bitmap.getByteCount() / 1024 / 1024)
			+ "M宽度为" + bitmap.getWidth() + "高度为" + bitmap.getHeight());
	return bitmap;
	}

	/**
 	* 获取缩放比例
	* @param bitmap
 	* @return
 	*/
	private int getScaling(Bitmap bitmap) {
		//设置目标尺寸（以像素的宽度为标准）
		int Targetsize = 1500;
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		//选择最大值作为比较值（保证图片的压缩大小）
		int handleValue = width > height ? width : height;
		//循环计算压缩比
		int i = 1;
		while (handleValue / i > Targetsize) {
			i++;
		}
	}

# 三、缩放法压缩, 效果和方法2一样

##  缩放法压缩：
 Android中使用Matrix对图像进行缩放、旋转、平移、斜切等变换的。
 Matrix是一个3*3的矩阵，其值对应如下：
 
 下面给出具体坐标对应变形的属性：
- 
* |scaleX, skewX, translateX|
* |skewY, scaleY, translateY|
* |0     ,0     , scale     |
* Matrix提供了一些方法来控制图片变换：
* setTranslate(float dx,float dy)：控制Matrix进行位移。
* setSkew(float kx,float ky)：控制Matrix进行倾斜，kx、ky为X、Y方向上的比例。
* setSkew(float kx,float ky,float px,float py)：控制Matrix以px、py为轴心进行倾斜，kx、ky为X、Y方向上的倾斜比例。
* setRotate(float degrees)：控制Matrix进行depress角度的旋转，轴心为（0,0）。
* setRotate(float degrees,float px,float py)：控制Matrix进行depress角度的旋转，轴心为(px,py)。
* setScale(float sx,float sy)：设置Matrix进行缩放，sx、sy为X、Y方向上的缩放比例。
* setScale(float sx,float sy,float px,float py)：设置Matrix以(px,py)为轴心进行缩放，sx、sy为X、Y方向上的缩放比例。

* 注意：以上的set方法，均有对应的post和pre方法，
  Matrix调用一系列set,pre,post方法时,可视为将这些方法插入到一个队列.
 当然,按照队列中从头至尾的顺序调用执行.
  其中pre表示在队头插入一个方法,post表示在队尾插入一个方法.
 而set表示把当前队列清空,并且总是位于队列的最中间位置.
  当执行了一次set后:
  pre方法总是插入到set前部的队列的最前面,post方法总是插入到set后部的队列的最后面
 

## 代码：
    private Bitmap ScalingCompression(Bitmap bitmap) {
		Matrix matrix = new Matrix();
		matrix.setScale(0.25f, 0.25f);//缩放效果类似于方法2
		Bitmap bm = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
			bitmap.getHeight(), matrix, true);
		Log.i("wechat", "压缩后图片的大小" + (bm.getByteCount() / 1024 / 1024)
			+ "M宽度为" + bm.getWidth() + "高度为" + bm.getHeight());
		return bm;
	}


# 四、Bitmap.Config
 
## 对比：
 
* 原图尺寸：4M----转化为File---Bitmap大小
* ALPHA_8----------6.77M -------45M
* ARGB_4444-------9.37M -------22M
* ARGB_8888-------6.77M -------45M
* RGB_565-----------8.13M -------22M

* Bitmap.Config ARGB_4444：每个像素占四位，即A=4，R=4，G=4，B=4，那么一个像素点占4+4+4+4=16位
* Bitmap.Config ARGB_8888：每个像素占八位，即A=8，R=8，G=8，B=8，那么一个像素点占8+8+8+8=32位
* Bitmap.Config RGB_565：每个像素占位R=5，G=6，B=5，没有透明度，那么一个像素点占5+6+5=16位
* Bitmap.Config ALPHA_8：每个像素占八位，只有透明度，没有颜色。

一般情况下默认使用的是ARGB_8888，由此可知它是最占内存的，因为一个像素占32位，8位=1字节，所以一个像素占4字节的内存。假设有一张480x800的图片，如果格式为ARGB_8888，那么将会占用1500KB的内存。


## 代码：
   	
	private Bitmap bitmapConfig(String path) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inPreferredConfig = Bitmap.Config.RGB_565;

		Bitmap bm = BitmapFactory.decodeFile(path, options);
		Log.i("wechat", "压缩后图片的大小" + (bm.getByteCount() / 1024f / 1024f)
		+ "M宽度为" + bm.getWidth() + "高度为" + bm.getHeight());
		return bm;
	} 




# 五、Bitmap提供的：createScaledBitmap

## 方法：
Bitmap.createScaledBitmap(src, dstWidth, dstHeight, filter);

## 参数：
	src        用来构建子集的源位图
	dstWidth   新位图期望的宽度
	dstHeight  新位图期望的高度
	filter     为true则选择抗锯齿
	
## 补充抗锯齿的知识点：
在Android中，目前，我知道有两种出现锯齿的情况。

* 1、当我们用Canvas绘制位图的时候，如果对位图进行了选择，则位图会出现锯齿。
* 2、在用View的RotateAnimation做动画时候，
如果View当中包含有大量的图形，也会出现锯齿。

我们分别以这两种情况加以考虑。
用Canvas绘制位图的的情况。
在用Canvas绘制位图时，一般地，我们使用drawBitmap函数家族，
在这些函数中，都有一个Paint参数，
要做到防止锯齿，我们就要使用到这个参数。如下：

首先在你的构造函数中，需要创建一个Paint。 Paint mPaint = new Paint（）；

然后，您需要设置两个参数:

* 1)mPaint.setAntiAlias(Boolean aa);
* 2)mPaint.setBitmapFilter(true)。

第一个函数是用来防止边缘的锯齿，
(true时图像边缘相对清晰一点，锯齿痕迹不那么明显，
false时，写上去的字不饱满，不美观，看地不太清楚)。

第二个函数是用来对位图进行滤波处理。

最后，在画图的时候，调用drawBitmap函数，只需要将整个Paint传入即可。

有时候，当你做RotateAnimation时，
你会发现，讨厌的锯齿又出现了。
这个时候，由于你不能控制位图的绘制，
只能用其他方法来实现防止锯齿。
另外，如果你画的位图很多。
不想每个位图的绘制都传入一个Paint。
还有的时候，你不可能控制每个窗口的绘制的时候，
您就需要用下面的方法来处理——对整个Canvas进行处理。

* 1）在您的构造函数中，创建一个Paint滤波器。
PaintFlagsDrawFilter mSetfil = new PaintFlagsDrawFilter(0, Paint.FILTER_BITMAP_FLAG);
第一个参数是你要清除的标志位，
第二个参数是你要设置的标志位。此处设置为对位图进行滤波。
* 2）当你在画图的时候，
如果是View则在onDraw当中，如果是ViewGroup则在dispatchDraw中调用如下函数。
canvas.setDrawFilter( mSetfil );

 另外，在Drawable类及其子类中，
也有函数setFilterBitmap可以用来对Bitmap进行滤波处理，
这样，当你选择Drawable时，会有抗锯齿的效果。

## 代码：
    private Bitmap createScaledBitmap(Bitmap bitmap) {
		Bitmap bm = Bitmap.createScaledBitmap(bitmap, 200, 200, true);
		Log.i("wechat", "压缩后图片的大小" + (bm.getByteCount() / 1024) + "KB宽度为"
			+ bm.getWidth() + "高度为" + bm.getHeight());
		return bm;
	}


##  附辅助方法：

##  通过路径获取bitmap

* 1、利用BitmapFactory解析文件，转换为Bitmap
* bitmap = BitmapFactory.decodeFile(path);
* 2、自己写解码，转换为Bitmap过程，
同样需使用BitmapFactory.decodeByteArray(buf, 0, len);
###代码：
    private Bitmap getBitmapByPath(String path) {
	if (!new File(path).exists()) {
		System.err.println("getBitmapByPath: 文件不存在");
		return null;
	}
	byte[] buf = new byte[1024 * 1024];// 1M
	Bitmap bitmap = null;
	try {
		FileInputStream fis = new FileInputStream(path);
		int len = fis.read(buf, 0, buf.length);
		bitmap = BitmapFactory.decodeByteArray(buf, 0, len);
		//当bitmap为空的时候，说明解析失败
		if (bitmap == null) {
			System.out.println("文件长度:" + len);
			System.err.println("path: " + path + "  无法解析!!!");
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return bitmap;
	}
   

## 保存图片：
###代码：
    private void savaPictrue(Bitmap bitmap) {
		File file = new File("storage/emulated/0/DCIM/Camera/test.jpg");
		FileOutputStream stream = null;
		try {
			stream = new FileOutputStream(file);
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
			Log.e("图片大小：", file.length() / 1024 / 1024 + "M");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

## 剪切图片
###代码：
    private void crop(Uri uri) {
		// 裁剪图片意图
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		intent.putExtra("crop", "true");
		// 裁剪框的比例，1：1
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		// 裁剪后输出图片的尺寸大小
		intent.putExtra("outputX", 300);
		intent.putExtra("outputY", 300);
	
		intent.putExtra("outputFormat", "JPEG");// 图片格式
		intent.putExtra("noFaceDetection", true);// 取消人脸识别
		intent.putExtra("return-data", true);
		// 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CUT
		startActivityForResult(intent, 200);
	}

	private void logp(Bitmap bitmap) {
	Log.i("wechat", "压缩前图片的大小" + (bitmap.getByteCount() / 1024 / 1024)
			+ "M宽度为" + bitmap.getWidth() + "高度为" + bitmap.getHeight());
	}

	private static void loga(Bitmap bitmap, byte[] bytes) {
		Log.i("wechat", "压缩后图片的大小" + (bitmap.getByteCount() / 1024 / 1024)
			+ "M宽度为" + bitmap.getWidth() + "高度为" + bitmap.getHeight()
			+ "bytes.length=  " + (bytes.length / 1024) + "KB"
		);
	}
