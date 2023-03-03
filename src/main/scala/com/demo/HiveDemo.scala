package com.demo

import org.apache.spark.sql.SparkSession

object HiveDemo {
  def main(args: Array[String]): Unit = {
    val sparkSession = SparkSession.builder().master("local[1]")
                                   .enableHiveSupport().getOrCreate();
    import sparkSession.implicits //便于rdd与Df隐式转化

    val inrdd = sparkSession.sparkContext.textFile("src/main/resources/*.sql")
    val DF = sparkSession.read.textFile("src/main/resources/*.sql")
    //val log = new ArrayBuffer[String]()
    val log1 = new StringBuilder
    val preprocessRDD = inrdd
      .map(line => line.replaceAll("(--.*)", " ").trim()) //|(\r\n)|(\n)
      .filter(line => null != line && line.length > 0)
      .map(line => {
        // log += line
        log1.append(line + " ")
      }
      )
    //.map(line =>line.split(";"))

    //    val sqlrdd = inrdd
    //      .filter(line => null!=line && line.trim.length>0)
    //      .map(line => line.replaceAll("(--.*)|(\\r\\n)|(\\n)", "").trim())
    preprocessRDD.foreach(item => println(item))
    //println(preprocessRDD)
    // val data = sparkSession.sql("")
    sparkSession.stop()
  }
}
