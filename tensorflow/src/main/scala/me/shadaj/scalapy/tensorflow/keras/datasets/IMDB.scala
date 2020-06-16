package me.shadaj.scalapy.tensorflow.keras.datasets

import me.shadaj.scalapy.numpy.NDArray
import me.shadaj.scalapy.py

@py.native trait IMDB extends py.Object {
  def load_data(
                 path: String = "imdb.npz",
                 num_words: py.NoneOr[Int] = py.None,
                 skip_top: Int = 0,
                 maxlen: py.NoneOr[Int] = py.None,
                 seed: Int = 113,
                 start_char: Int = 1,
                 oov_char: Int = 2,
                 index_from: Int =3
               ): ((NDArray[Long], NDArray[Long]), (NDArray[Long], NDArray[Long])) = py.native
}