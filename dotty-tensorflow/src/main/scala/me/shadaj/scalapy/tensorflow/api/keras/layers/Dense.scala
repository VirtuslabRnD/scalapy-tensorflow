package me.shadaj.scalapy.tensorflow.api.keras.layers

import me.shadaj.scalapy.tensorflow.keras.layers.{Dense => PyDense}
import me.shadaj.scalapy.tensorflow.api.scalaUtils.PythonUnion._
import me.shadaj.scalapy.tensorflow.api.scalaUtils.PythonOption._
import scala.language.implicitConversions

class Dense private[api] (override val underlying: PyDense) extends Layer(underlying) {
  def units: Int = underlying.units
  def activation: Option[String] = underlying.activation
  def useBias: Boolean = underlying.use_bias
  def kernelInitializer: String = underlying.kernel_initializer
  def biasInitializer: String = underlying.bias_initializer
  def kernelRegularizer: Option[String] = underlying.kernel_regularizer
  def biasRegularizer: Option[String] = underlying.bias_regularizer
  def activityRegularizer: Option[String] = underlying.activity_regularizer
  def kernelConstraint: Option[String] = underlying.kernel_constraint
  def biasConstraint: Option[String] = underlying.bias_constraint
}
