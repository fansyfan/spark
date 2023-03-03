package com.demo

import breeze.linalg._
import breeze.numerics._

object VectorDemo {

   def createv(): Unit = {

    /**
     * def zeros  函数名
     *        [@spec(Double, Int, Float, Long) V:ClassTag:Zero] 泛型
     *        (rows: Int, cols: Int) 入参
     *        : DenseMatrix[V] 返回值类型
     *        ={} 函数体
     */
    //todo 矩阵
    println(DenseMatrix.zeros[Double](2, 3))
    //单位矩阵
    println(DenseMatrix.eye[Double](3))
    //对角矩阵
    println(diag(DenseVector(1.0, 2.0, 3.0)))
    //从函数创建矩阵
    println(DenseMatrix.tabulate(3, 2){case (i, j) => i+j})
    println(new DenseMatrix(2, 3, Array(11, 12, 13, 21, 22, 23)))


    //todo 向量
    println(DenseVector.zeros[Double](3))
    println(DenseVector.ones[Double](3))
    println(DenseVector(1, 2, 3))
    println(new DenseVector(Array(1, 2, 3, 4)))
    /**
     * 从函数创建向量
     *
     * def tabulate[@spec(Double, Int, Float, Long) V:ClassTag]
     *          (range: Range)(f: Int=>V)
     *          :Vec[V]= {}
     * def tabulate[@spec(Double, Int, Float, Long) V:ClassTag]
     *             (size: Int)(f: Int=>V)
     *             : Vec[V] = {apply(Array.tabulate(size)(f))}
     */
    println(DenseVector.tabulate(3){i=>2*i})

    /**
     * 方式一：
     * def fill [@spec(Double, Int, Float, Long) V:ClassTag]
     *          (size: Int)(v: =>V)
     *          : Vec[V] = {}
     * 方式二：
     * def fill[@spec(Double, Int, Float, Long) V: ClassTag:Semiring]
     *        (size: Int, v: V)
     *        : DenseVector[V] = {}
     */
    println(DenseVector.fill[Double](3, 5))
    println(DenseVector.fill(3) {5.0})

    /**
     *  def range(start:Int, end: Int, step: Int)
     *            : Vec[Int] = apply[Int](Array.range(start,end,step))
     *  def rangeF(start: Float, end: Float, step: Float = 1.0f)
     *                  : Vec[Float] = {}
     *  def rangeD(start: Double, end: Double, step: Double = 1.0)
     *            : Vec[Double] = {}
     */
    println(DenseVector.range(1, 10, 2))

    //向量转置
    println(DenseVector(1, 2, 3, 4).t)
  }

  def main(args: Array[String]): Unit = {
    //向量
    val v1 = DenseVector(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    println(v1(0)) // 1
    println(v1(1 to 4)) //DenseVector(2, 3, 4, 5)
    println(v1(5 to 0 by -1)) //DenseVector(6, 5, 4, 3, 2, 1)
    println(v1(0 to -1))// DenseVector(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    //矩阵
    val m = DenseMatrix((1.0,2.0,3.0), (3.0,4.0,5.0))
    println(m)
    /**
     * 1.0  2.0  3.0
     * 3.0  4.0  5.0
     */
    println(m(0, 1)) //1.0
    println(m(::, 1)) //DenseVector(2.0, 4.0)
  }
}
