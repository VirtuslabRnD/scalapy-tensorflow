package me.shadaj.scalapy.tensorflow.api.keras.layers

import me.shadaj.scalapy.tensorflow.keras.layers.{Conv2D => PyConv2D}
import me.shadaj.scalapy.tensorflow.api.scalaUtils.PythonUnion._
import me.shadaj.scalapy.tensorflow.api.scalaUtils.PythonOption._

import scala.language.implicitConversions
import me.shadaj.scalapy.tensorflow.api.scalaUtils
import me.shadaj.scalapy.py.|

class Conv2D private[api] (override val underlying: PyConv2D) extends Layer(underlying) {
  def filters: Int = underlying.filters
  def kernelSize: Int | (Int, Int) = underlying.kernel_size
  def strides: Int | (Int, Int) = underlying.strides
  def padding: Padding = Padding.valueOf(underlying.padding)
  def dataFormat: Option[DataFormat] = underlying.data_format.toScalaOption.map(DataFormat.valueOf)
  def dilationRate: Int | (Int, Int) = underlying.dilation_rate
  def activation: Option[String] = underlying.activation
  def useBias: Boolean = underlying.use_bias
  def kernelInitializer: String = underlying.kernel_initializer
  def biasInitializer: String = underlying.bias_initializer
  def kernelRegularizer: Option[String] = underlying.kernel_regularizer
  def biasRegularizer: Option[String] = underlying.bias_regularizer
  def activityRegularizer: Option[String] = underlying.activity_regularizer
  def kernelConstraint: Option[String] = underlying.kernel_constraint
  def biasConstraint: Option[String] = underlying.bias_constraint
  def inputShape: Option[(Int, Int, Int)] = underlying.input_shape
}
