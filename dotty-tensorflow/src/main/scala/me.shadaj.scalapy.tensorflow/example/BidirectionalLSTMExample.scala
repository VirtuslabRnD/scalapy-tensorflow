package me.shadaj.scalapy.tensorflow.example

import me.shadaj.scalapy.py
import me.shadaj.scalapy.tensorflow.modules._
import Int.int2long
import scala.language.implicitConversions

/**
 * https://keras.io/examples/imdb_bidirectional_lstm/
 */

object BidirectionalLSTMExample {
  @main
  def bidirectionalLSTM() = {
    val tf = tensorflow
    val np = numpy
    val keras1 = keras
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
    val x_train1 = sequence.pad_sequences(x_train, maxlen=maxlen)
    val x_test1 = sequence.pad_sequences(x_test, maxlen=maxlen)
    println(s"x_train shape: ${x_train1.shape}")
    println(s"x_test shape: ${x_test1.shape}")
    //    y_train = np.array(y_train)
    //    y_test = np.array(y_test)
    val model = keras1.models.Sequential()
    model.add(layers.Embedding(max_features, 128, input_length=maxlen))
    model.add(layers.Bidirectional(layers.LSTM(64)))
    model.add(layers.Dropout(0.5))
    model.add(layers.Dense(1, activation="sigmoid"))
    //
    //    try using different optimizers and different optimizer configs
    model.compile("adam", keras1.backend.binary_crossentropy, metrics=Seq("accuracy"))

    println("Train...")
    model.fit(x_train1, y_train,
      batch_size=batch_size,
      epochs=4,
      validation_data=(x_test1, y_test))
  }
}