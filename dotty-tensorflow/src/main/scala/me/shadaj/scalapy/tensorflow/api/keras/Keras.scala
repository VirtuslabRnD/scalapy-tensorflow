package me.shadaj.scalapy.tensorflow.api.keras

import me.shadaj.scalapy.tensorflow.api.Tensor
import me.shadaj.scalapy.tensorflow.keras.{Keras => PyKeras}
import me.shadaj.scalapy.tensorflow.api.scalaUtils.PythonModule
import scala.language.implicitConversions
import optimizers.Optimizers
import layers.Layers
import preprocessing.Preprocessing
import models.Models
import datasets.Datasets
import backend.Backend
import utils.Utils
import losses.Losses
class Keras private[api] (val underlying: PyKeras) extends PythonModule[PyKeras] {

  def models: Models = new Models(underlying.models)
  def datasets: Datasets = new Datasets(underlying.datasets)
  def backend: Backend = new Backend(underlying.backend)
  def utils: Utils = new Utils(underlying.utils)
  def optimizers: Optimizers = new Optimizers(underlying.optimizers)
  def losses: Losses = new Losses(underlying.losses)
  def layers: Layers = new Layers(underlying.layers)
  def preprocessing: Preprocessing = new Preprocessing(underlying.preprocessing)

}
