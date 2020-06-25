package me.shadaj.scalapy.tensorflow.keras.layers

import me.shadaj.scalapy.numpy.NDArray
import me.shadaj.scalapy.py
import me.shadaj.scalapy.tensorflow.Tensor

@py.native trait Layers extends py.Object {
  def Conv2D(filters: Int,
             kernel_size: py.|[Int, (Int, Int)],
             strides: py.|[Int, (Int, Int)] = (1, 1),
             padding: String = "valid",
             data_format: py.NoneOr[String] = py.None,
             dilation_rate: py.|[Int, (Int, Int)] = (1, 1),
             activation: py.NoneOr[String] = py.None,
             use_bias: Boolean = true,
             kernel_initializer: String = "glorot_uniform",
             bias_initializer: String = "zeros",
             kernel_regularizer: py.NoneOr[String] = py.None,
             bias_regularizer: py.NoneOr[String] = py.None,
             activity_regularizer: py.NoneOr[String] = py.None,
             kernel_constraint: py.NoneOr[String] = py.None,
             bias_constraint: py.NoneOr[String] = py.None,
             kwargs: Map[String, py.Any] = Map()
            ): Conv2D = py.nativeNamed

  def Dropout(rate: Double,
              noise_shape: py.NoneOr[Tensor] = py.None,
              seed: py.NoneOr[Int] = py.None
             ): Dropout = py.nativeNamed

  def MaxPooling2D(pool_size: (Int, Int),
                   strides: py.NoneOr[py.|[Int, (Int, Int)]] = py.None,
                   padding: String = "valid",
                   data_format: py.NoneOr[String] = py.None
                  ): MaxPooling2D = py.nativeNamed

  def Flatten(data_format: py.NoneOr[String] = py.None): Flatten = py.nativeNamed

  def Dense(units: Int,
            activation: py.NoneOr[String] = py.None,
            use_bias: Boolean = true,
            kernel_initializer: String = "glorot_uniform",
            bias_initializer: String = "zeros",
            kernel_regularizer: py.NoneOr[String] = py.None,
            bias_regularizer: py.NoneOr[String] = py.None,
            activity_regularizer: py.NoneOr[String] = py.None,
            kernel_constraint: py.NoneOr[String] = py.None,
            bias_constraint: py.NoneOr[String] = py.None
           ): Dense = py.nativeNamed

  def Embedding(
                 input_dim: Int,
                 output_dim: Int,
                 embeddings_initializer: String="uniform",
                 embeddings_regularizer: py.NoneOr[String]=py.None,
                 activity_regularizer: py.NoneOr[String]=py.None,
                 embeddings_constraint: py.NoneOr[String]=py.None,
                 mask_zero: Boolean=false,
                 input_length: py.NoneOr[Int]=py.None,
                 kwargs: Map[String, py.Any] = Map()
               ): Embedding = py.nativeNamed

  def Bidirectional(
                     layer: Layer,
                     merge_mode: String="concat",
                     weights: py.NoneOr[NDArray[Int]]=py.None,
//                     backward_layer: py.NoneOr[Layer]=py.None,
                     kwargs: Map[String, py.Any]=Map()
                   ): Bidirectional = py.nativeNamed

  def LSTM(
            units: Int,
            activation: String="tanh",
            recurrent_activation: String="sigmoid",
            use_bias: Boolean=true,
            kernel_initializer: String="glorot_uniform",
            recurrent_initializer: String="orthogonal",
            bias_initializer: String="zeros",
            unit_forget_bias: Boolean=true,
            kernel_regularizer: py.NoneOr[String]=py.None,
            recurrent_regularizer: py.NoneOr[String]=py.None,
            bias_regularizer: py.NoneOr[String]=py.None,
            activity_regularizer: py.NoneOr[String]=py.None,
            kernel_constraint: py.NoneOr[String]=py.None,
            recurrent_constraint: py.NoneOr[String]=py.None,
            bias_constraint: py.NoneOr[String]=py.None,
            dropout: Double=0.0,
            recurrent_dropout: Double=0.0,
            implementation: Int=2,
            return_sequences: Boolean=false,
            return_state: Boolean=false,
            go_backwards: Boolean=false,
            stateful: Boolean=false,
//            time_major: Boolean=false,
            unroll: Boolean=false,
            kwargs: Map[String, py.Any]=Map()
          ): LSTM = py.nativeNamed

  def BatchNormalization(
            axis: Int= -1,
            momentum: Double =0.99,
            epsilon: Double =0.001,
            center: Boolean=true,
            scale: Boolean=true,
            beta_initializer: String="zeros",
            gamma_initializer: String="ones",
            moving_mean_initializer: String="zeros",
            moving_variance_initializer: String="ones",
            beta_regularizer: py.NoneOr[String]=py.None,
            gamma_regularizer: py.NoneOr[String]=py.None,
            beta_constraint: py.NoneOr[String]=py.None,
            gamma_constraint: py.NoneOr[String]=py.None,
            renorm: Boolean=false,
            renorm_clipping: py.NoneOr[String]=py.None,
            renorm_momentum: Double=0.99,
            kwargs: Map[String, py.Any]=Map()
          ): BatchNormalization = py.nativeNamed
}
