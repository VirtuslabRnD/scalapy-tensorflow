package me.shadaj.scalapy.tensorflow

import me.shadaj.scalapy.py
import me.shadaj.scalapy.py.{PyFunction, Writer, |}
import me.shadaj.scalapy.tensorflow.compat.v1.Compat
import me.shadaj.scalapy.tensorflow.keras.Keras
import me.shadaj.scalapy.tensorflow.nn.NN
import me.shadaj.scalapy.tensorflow.random.Random
import me.shadaj.scalapy.tensorflow.train.Train

// some TensorFlow operations require a LIST list, not just something iterable
@py.native
trait PythonList[T] extends py.Object
object PythonList {
  implicit def seqToPythonList[T](seq: Seq[T])(implicit writer: Writer[Seq[T]]): PythonList[T] = {
    py.global.list(py.Any.from(seq)(writer)).as[PythonList[T]]
  }
}

@py.native
trait TensorFlow extends py.Object {

  // modules

  def keras: Keras = py.native

  def nn: NN = py.native

  def random: Random = py.native

  def compat: Compat = py.native

  def train: Train = py.native

  // classes

  def Variable(initialValue: Tensor): Variable = py.native

  def GradientTape(): GradientTape = py.native

  // functions

  def matmul(a: Tensor, b: Tensor): Tensor = py.native

  def identity: PyFunction = py.native

  def identity(t: Tensor): Tensor = py.native

  def zeros(shape: Seq[Int]): Tensor = py.native

  def reshape(tensor: Tensor, shape: PythonList[Int]): Tensor = py.native

  def add_n(ts: Seq[Tensor]): Tensor = py.native

  def square(t: Tensor): Tensor = py.native

  def tanh(t: Tensor): Tensor = py.native

  def reduce_mean(t: Tensor): Tensor = py.native

  def gradients(ys: Tensor | Seq[Tensor], xs: Tensor | Seq[Tensor]): Seq[Tensor] = py.native

  def gradients(ys: Tensor, xs: Seq[Tensor], grad_ys: Tensor): Seq[Tensor] = py.native

  def cond(c: Tensor, ifTrue: py.Object, ifFalse: py.Object): Tensor = py.native

}
