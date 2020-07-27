package me.shadaj.scalapy.tensorflow.example

import me.shadaj.scalapy.py
import me.shadaj.scalapy.tensorflow.scala.utils.Modules
import me.shadaj.scalapy.tensorflow.nd2Tensor
import Int.int2long
import scala.language.implicitConversions

/**
  * https://keras.io/examples/nlp/bidirectional_lstm_imdb/
  */

object BidirectionalLSTMExample extends Runnable {

  def run() = {
    val tf = Modules.tensorflow
    val np = Modules.numpy
    val keras1 = tf.keras
    val layers = keras1.layers
    val imdb = keras1.datasets.imdb
    val sequence = keras1.preprocessing.sequence
    val max_features = 20000
    // cut texts after this number of words
    // (among top max_features most common words)
    val maxlen = 100
    val batch_size = 32

    println("Loading data...")
    val ((x_train, y_train), (x_test, y_test)) = imdb.load_data(num_words = max_features)
    println(s"${x_train.length} train sequences")
    println(s"${x_test.length} test sequences")

    println("Pad sequences (samples x time)")
    val x_train1 = sequence.pad_sequences(x_train, maxlen = maxlen).astype(np.float32)
    val x_test1 = sequence.pad_sequences(x_test, maxlen = maxlen).astype(np.float32)
    val y_train1 = y_train.astype(np.float32)
    val y_test1 = y_test.astype(np.float32)
    println(s"x_train shape: ${x_train1.shape}")
    println(s"x_test shape: ${x_test1.shape}")

    val model = keras1.models.Sequential()
    model.add(layers.Embedding(max_features, 128, input_length = maxlen))
    model.add(layers.Bidirectional(layers.LSTM(64)))
    model.add(layers.Dropout(0.5))
    model.add(layers.Dense(1, activation = "sigmoid"))
    //
    //    try using different optimizers and different optimizer configs
    model.compile("adam", keras1.backend.binary_crossentropy, metrics = Seq("accuracy"))

    println("Train...")
    val epochs = Option(System.getenv("EPOCH_COUNT")).map(_.toInt).getOrElse(4)
    model.fit(x_train1, y_train1, batch_size = batch_size, epochs = epochs, validation_data = (x_test1, y_test1))
  }
}
