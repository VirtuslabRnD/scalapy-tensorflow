package me.shadaj.scalapy.tensorflow.api

import me.shadaj.scalapy.tensorflow.scala.utils.Modules.{tensorflow => pyTensorflow}
import me.shadaj.scalapy.tensorflow.{TensorFlow => PyTensorFlow, Tensor => PyTensor}
import random.Random
import keras.Keras
import compat.Compat
import scala.language.implicitConversions
import scalaUtils._

import me.shadaj.scalapy.py.PyFunction
import me.shadaj.scalapy.py

class TensorFlow {
  private val tf: PyTensorFlow = pyTensorflow
  // modules
//
  def keras: Keras = new Keras(tf.keras)
//
//  def nn: NN = py.native
//
  def random: Random = new Random(tf.random)
//
  def compat: Compat = new Compat(tf.compat)
//
//  def train: Train = py.native

  // classes

  def Variable(initialValue: Tensor): Variable = new Variable(tf.Variable(initialValue))

  def GradientTape(): GradientTape = new GradientTape(tf.GradientTape())

  // functions

  def matMul(a: Tensor, b: Tensor): Tensor = new Tensor(tf.matmul(a, b))

  def identity: PyFunction = tf.identity

  def identity(t: Tensor): Tensor = tf.identity(t)

  def zeros(shape: Seq[Int]): Tensor = tf.zeros(shape)

  def reshape(tensor: Tensor, shape: Tensor): Tensor = tf.reshape(tensor, shape)

  def addN(ts: Seq[Tensor]): Tensor = tf.add_n(ts.map(_.underlying))

  def square(t: Tensor): Tensor = tf.square(t)

  def tanh(t: Tensor): Tensor = tf.tanh(t)

  def reduceMean(t: Tensor): Tensor = tf.reduce_mean(t)

  // def gradients(ys: Tensor | Seq[Tensor], xs: Tensor | Seq[Tensor]): Seq[Tensor] = (ys, xs) match {
  //   case (ys: Tensor, xs: Tensor) => tf.gradients(ys.underlying, xs.underlying).map(new Tensor(_))
  //   case (ys: Seq[Tensor], xs: Tensor) => tf.gradients(ys.map(_.underlying), xs.underlying).map(new Tensor(_))
  //   case (ys: Tensor, xs: Seq[Tensor]) => tf.gradients(ys.underlying, xs.map(_.underlying)).map(new Tensor(_))
  //   case (ys: Seq[Tensor], xs: Seq[Tensor]) => tf.gradients(ys.map(_.underlying), xs.map(_.underlying)).map(new Tensor(_))
  // }

  def gradients(ys: Tensor | Seq[Tensor], xs: Tensor | Seq[Tensor]): Seq[Tensor] = {
    tf.gradients(PythonUnion.convertSeq(ys), PythonUnion.convertSeq(xs)).map(new Tensor(_))
  }

  // def gradients(ys: Tensor | Seq[Tensor], xs: Tensor | Seq[Tensor]): Unit = {
  //   val x: py.|[PyTensor, Seq[PyTensor]] = PythonUnion.convert2Seq(ys)
  //   println("VALUE:\t" + x.value)

  // }

  def gradients(ys: Tensor, xs: Seq[Tensor], gradYs: Tensor): Seq[Tensor] = tf.gradients(ys, xs.map(_.underlying), gradYs).map(new Tensor(_))

}
