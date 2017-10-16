package com.liuqian.firstKotlin

/**
 * Created by AndroidStudio on 2017/10/16.
 */
//主函数
fun main(args: Array<String>) {
    //Kotlin的类可以有属性。属性可以用关键字 var声明为可变的，val只读关键字。
    // 一次赋值（只读）的局部变量 val
    val a: Int = 1      //立即赋值
    val b = 2.023           //自动推断出Int类型
    val c: Int          //如果没有初始值类型不能省略
    c = 3               //明确赋值
    println("a=$a ,b=$b ,c=$c")
    //变量的赋值
    var x = 5    //	自动推断出	`Int`	类型
    x += 1
    var y: String
    y = "我是谁"
    println("x=$x")
    println("y=$y")

    //字符串 使用字符串模板
    var a1 = 1                //	模板中的简单名称：
    val s1 = "a	is	$a1"
    a1 = 2//	模板中的任意表达式：
    val s2 = "${s1.replace("is", "was")},	but	now	is	$a1"
    println(s2)
    //函数的定义
    print("sum	of	3	and	$c	is	")
    println(sum1(3, c))
    println("sum	of	19	and	23	is	${sum2(19, 23)}")
    printSum(-1, 8)
    printSum1(-2, 8)
}

//带有两个Int	参数、返回Int的函数
fun sum1(a: Int, b: Int): Int {
    return a + b
}

//将表达式作为函数体、返回值类型自动推断的函数：
fun sum2(a: Int, b: Int) = a + b

//函数返回无意义的值  	Unit返回类型可以省略
fun printSum(a: Int, b: Int): Unit {
    println("sum	of	$a	and	$b	is	${a + b}")
}

fun printSum1(a: Int, b: Int) {
    println("sum	of	$a	and	$b	is	${a + b}")
}
/**
 * kotlin的注释是可以嵌套的（不同于java）
 */

