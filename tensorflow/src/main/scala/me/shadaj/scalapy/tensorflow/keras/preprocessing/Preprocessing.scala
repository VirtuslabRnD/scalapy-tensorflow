package me.shadaj.scalapy.tensorflow.keras.preprocessing

import me.shadaj.scalapy.py

@py.native trait Preprocessing extends py.Object {
  def sequence: Sequence = py.native
}