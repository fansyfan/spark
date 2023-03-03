package com.demo

import org.apache.spark.{SparkConf, SparkContext}

object SparkRdd {
  def main(args: Array[String]): Unit = {
    /**
     * 方式一
     */
    // 1. 创建SparkConf对象，设置属性和配置
    //    val sparkConf = new SparkConf().setAppName(this.getClass.getSimpleName).setMaster("local[2]")
    //    // 2. 传递sparkConf对象，构建SparkContext实例对象
    //    val sc: SparkContext = new SparkContext(sparkConf)

    /**
     * 方式二
     */
    val sc: SparkContext = {
      // 其一、构建SparkConf对象，设置应用名称和master
      val sparkConf: SparkConf = new SparkConf().setAppName("SparkWordCount").setMaster("local[2]")
      // 其二、创建SparkContext实例，传递sparkConf对象
      new SparkContext(sparkConf)
    }
    // todo println(sc)  //org.apache.spark.SparkContext@545f80bf
    //本地集合转rdd
    val data = Array(1, 2, 3)
    val inrdd = sc.parallelize(data)
    //rdd转本地集合
    val list = inrdd.collect()
    println(list(0))


    // 当应用运行结束，关闭资源
    //Thread.sleep(50000)
    sc.stop()

  }
}
