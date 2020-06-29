package me.shadaj.scalapy.tensorflow.keras.backend

import me.shadaj.scalapy.py
import me.shadaj.scalapy.py.PyFunction

@py.native
trait Backend extends py.Object {
  def image_data_format(): String = py.native
  def binary_crossentropy: PyFunction = py.native
}
