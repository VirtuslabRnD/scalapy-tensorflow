package me.shadaj.scalapy.tensorflow.example

import me.shadaj.scalapy.readwrite.Reader._
import me.shadaj.scalapy.py
import me.shadaj.scalapy.py.{Any, Dynamic, PyQuote, SeqConverters, local}


object LambdaExample extends Runnable {
  def run(): Unit = {
    println("Hello, World")
    val listLengthPython = py.Dynamic.global.len(List(1, 2, 3).toPythonProxy)

    local {
      var count = 0
      val testLambda = Any.from(() => {
        count += 1
        s"count: $count"
      })

      val testLambda2 = Any.from((x: Seq[Int]) => x.sum)

      assert(py"$testLambda()".as[String] == "count: 1")
      assert(py"$testLambda()".as[String] == "count: 2")
      assert(py"$testLambda2([1, 2, 3])".as[Int] == 6)
    }

    val lambdaToScala = Dynamic.global.len.as[Any => Int]
    assert(lambdaToScala(Seq[Any]().toPythonProxy) == 0)
    assert(lambdaToScala(Seq(1, 2, 3).toPythonProxy) == 3)

    @py.native trait PyString extends py.Object {
      def count(subsequence: String): Int = py.native
    }

    /*{
      @py.native trait PythonRandomModule extends py.Object {
        def Random(a: String, s: Seq[Int]): py.Dynamic = py.native
      }

      val random = py.module("random").as[PythonRandomModule]
      //println(random.Random("123", 4))

      val string1 = py.module("string").digits.as[PyString]
      val string2 = py.module("string").digits.as[PyString]
      // string: PyString = 0123456789
      println(string1.count("123"))
    }*/
  }
}
