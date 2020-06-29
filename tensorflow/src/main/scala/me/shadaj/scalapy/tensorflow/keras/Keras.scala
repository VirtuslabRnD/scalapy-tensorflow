package me.shadaj.scalapy.tensorflow.keras

import me.shadaj.scalapy.py
import me.shadaj.scalapy.tensorflow.Tensor
import me.shadaj.scalapy.tensorflow.keras.backend.Backend
import me.shadaj.scalapy.tensorflow.keras.datasets.Datasets
import me.shadaj.scalapy.tensorflow.keras.models.Models
import me.shadaj.scalapy.tensorflow.keras.optimizers.Optimizers
import me.shadaj.scalapy.tensorflow.keras.utils.Utils
import me.shadaj.scalapy.tensorflow.keras.losses.Losses
import me.shadaj.scalapy.tensorflow.keras.layers.Layers
import me.shadaj.scalapy.tensorflow.keras.preprocessing.Preprocessing

@py.native
trait Keras extends py.Object {
  def models: Models = py.native
  def datasets: Datasets = py.native
  def backend: Backend = py.native
  def utils: Utils = py.native
  def optimizers: Optimizers = py.native
  def losses: Losses = py.native
  def layers: Layers = py.native
  def preprocessing: Preprocessing = py.native

  def Input(
      shape: Seq[py.NoneOr[Int]] = Seq(),
//     batch_size: py.NoneOr[Int]=py.None,
      name: py.NoneOr[String] = py.None,
      dtype: py.NoneOr[String] = py.None,
      sparse: Boolean = false,
      tensor: py.NoneOr[Tensor] = py.None,
      kwargs: Map[String, py.Any] = Map()
  ): Tensor = py.nativeNamed
}
