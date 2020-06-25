package me.shadaj.scalapy.tensorflow.keras.preprocessing

import me.shadaj.scalapy.numpy.NDArray
import me.shadaj.scalapy.py

@py.native trait Sequence extends py.Object {
  def pad_sequences(sequences: NDArray[Long],
  maxlen: py.NoneOr[Int] = py.None,
  dtype: String = "int32",
  padding: String = "pre",
  truncating: String = "pre",
  value: py.|[Double, String] = 0.0): NDArray[Long] = py.native
}
