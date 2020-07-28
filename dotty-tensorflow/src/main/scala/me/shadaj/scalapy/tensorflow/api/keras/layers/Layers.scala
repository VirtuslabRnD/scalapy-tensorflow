package me.shadaj.scalapy.tensorflow.api.keras.layers
import me.shadaj.scalapy.tensorflow.keras.layers.{Layers => PyLayers}
import me.shadaj.scalapy.tensorflow.api.scalaUtils.{PythonModule, PythonType}
import me.shadaj.scalapy.tensorflow.api.scalaUtils.PythonOption._
import me.shadaj.scalapy.tensorflow.api.scalaUtils.PythonUnion._
import scala.language.implicitConversions
import me.shadaj.scalapy.numpy.NDArray
import me.shadaj.scalapy.tensorflow.api.Tensor
import me.shadaj.scalapy.tensorflow.api.Tensor._
import me.shadaj.scalapy.py

class Layers private[api] (val underlying: PyLayers) extends PythonModule[PyLayers] {
  def Conv2D(
      filters: Int,
      kernelSize: Int | (Int, Int),
      strides: Int | (Int, Int) = (1, 1),
      padding: String = "valid",
      dataFormat: Option[String] = None,
      dilationRate: Int | (Int, Int) = (1, 1),
      activation: Option[String] = None,
      useBias: Boolean = true,
      kernelInitializer: String = "glorot_uniform",
      biasInitializer: String = "zeros",
      kernelRegularizer: Option[String] = None,
      biasRegularizer: Option[String] = None,
      activityRegularizer: Option[String] = None,
      kernelConstraint: Option[String] = None,
      biasConstraint: Option[String] = None,
      kwargs: Map[String, py.Any] = Map()
  ): Conv2D =
    new Conv2D(
      underlying.Conv2D(
        filters,
        kernelSize,
        strides,
        padding,
        dataFormat,
        dilationRate,
        activation,
        useBias,
        kernelInitializer,
        biasInitializer,
        kernelRegularizer,
        biasRegularizer,
        activityRegularizer,
        kernelConstraint,
        biasConstraint,
        kwargs
      )
    )

  def Dropout(rate: Double, noiseShape: Option[Tensor] = None, seed: Option[Int] = None): Dropout =
    new Dropout(underlying.Dropout(rate, noiseShape.map(_.underlying), seed))

  def MaxPooling2D(
      poolSize: (Int, Int),
      strides: Option[Int | (Int, Int)] = None,
      padding: String = "valid",
      dataFormat: Option[String] = None
  ): MaxPooling2D = new MaxPooling2D(underlying.MaxPooling2D(poolSize, option2PyOption(strides.map(fromScalaTypesUnion(_))), padding, dataFormat))

  def Flatten(dataFormat: Option[String] = None): Flatten = new Flatten(underlying.Flatten(dataFormat))

  def Dense(
      units: Int,
      activation: Option[String] = None,
      useBias: Boolean = true,
      kernelInitializer: String = "glorot_uniform",
      biasInitializer: String = "zeros",
      kernelRegularizer: Option[String] = None,
      biasRegularizer: Option[String] = None,
      activityRegularizer: Option[String] = None,
      kernelConstraint: Option[String] = None,
      biasConstraint: Option[String] = None
  ): Dense =
    new Dense(
      underlying.Dense(
        units,
        activation,
        useBias,
        kernelInitializer,
        biasInitializer,
        kernelRegularizer,
        biasRegularizer,
        activityRegularizer,
        kernelConstraint,
        biasConstraint
      )
    )

  def Embedding(
      inputDim: Int,
      outputDim: Int,
      embeddingsInitializer: String = "uniform",
      embeddingsRegularizer: Option[String] = None,
      activityRegularizer: Option[String] = None,
      embeddingsConstraint: Option[String] = None,
      maskZero: Boolean = false,
      inputLength: Option[Int] = None,
      kwargs: Map[String, py.Any] = Map()
  ): Embedding =
    new Embedding(
      underlying.Embedding(
        inputDim,
        outputDim,
        embeddingsInitializer,
        embeddingsRegularizer,
        activityRegularizer,
        embeddingsConstraint,
        maskZero,
        inputLength,
        kwargs
      )
    )

  def Bidirectional(
      layer: Layer,
      mergeMode: String = "concat",
      weights: Option[NDArray[Int]] = None,
      kwargs: Map[String, py.Any] = Map()
  ): Bidirectional = new Bidirectional(underlying.Bidirectional(layer.underlying, mergeMode, weights, kwargs))

  def LSTM(
      units: Int,
      activation: String = "tanh",
      recurrentActivation: String = "sigmoid",
      useBias: Boolean = true,
      kernelInitializer: String = "glorot_uniform",
      recurrentInitializer: String = "orthogonal",
      biasInitializer: String = "zeros",
      unitForget_bias: Boolean = true,
      kernelRegularizer: Option[String] = None,
      recurrentRegularizer: Option[String] = None,
      biasRegularizer: Option[String] = None,
      activityRegularizer: Option[String] = None,
      kernelConstraint: Option[String] = None,
      recurrentConstraint: Option[String] = None,
      biasConstraint: Option[String] = None,
      dropout: Double = 0.0,
      recurrentDropout: Double = 0.0,
      implementation: Int = 2,
      returnSequences: Boolean = false,
      returnState: Boolean = false,
      goBackwards: Boolean = false,
      stateful: Boolean = false,
      unroll: Boolean = false,
      kwargs: Map[String, py.Any] = Map()
  ): LSTM =
    new LSTM(
      underlying.LSTM(
        units,
        activation,
        recurrentActivation,
        useBias,
        kernelInitializer,
        recurrentInitializer,
        biasInitializer,
        unitForget_bias,
        kernelRegularizer,
        recurrentRegularizer,
        biasRegularizer,
        activityRegularizer,
        kernelConstraint,
        recurrentConstraint,
        biasConstraint,
        dropout,
        recurrentDropout,
        implementation,
        returnSequences,
        returnState,
        goBackwards,
        stateful,
        unroll,
        kwargs
      )
    )
}
