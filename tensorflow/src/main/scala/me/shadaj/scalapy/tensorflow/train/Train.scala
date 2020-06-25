package me.shadaj.scalapy.tensorflow.train

import me.shadaj.scalapy.py

@py.native trait Train extends py.Object{

  def ExponentialMovingAverage(): ExponentialMovingAverage = py.native

}
