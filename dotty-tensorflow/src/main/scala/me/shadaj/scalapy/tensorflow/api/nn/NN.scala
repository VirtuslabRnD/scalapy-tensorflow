package me.shadaj.scalapy.tensorflow.api.nn

import me.shadaj.scalapy.tensorflow.nn.{NN => PyNN}
import me.shadaj.scalapy.tensorflow.api.scalaUtils.{PythonModule, PythonType}
import me.shadaj.scalapy.tensorflow.api.scalaUtils.PythonOption._
import me.shadaj.scalapy.tensorflow.api.Tensor
import me.shadaj.scalapy.tensorflow.api.Tensor._
import me.shadaj.scalapy.py.PyFunction
import scala.language.implicitConversions

class NN private[api] (val underlying: PyNN) extends PythonType[PyNN] with PythonModule {
  def relu: PyFunction = underlying.relu
  def relu(features: Tensor): Tensor = underlying.relu(features)

  def softsign: PyFunction = underlying.softsign
  def softsign(features: Tensor): Tensor = underlying.softsign(features)

  def tanh: PyFunction = underlying.tanh
  def tanh(features: Tensor): Tensor = underlying.tanh(features)

  def sigmoid: PyFunction = underlying.sigmoid
  def sigmoid(features: Tensor): Tensor = underlying.sigmoid(features)

  def l2Loss(t: Tensor): Tensor = underlying.l2_loss(t)
}
