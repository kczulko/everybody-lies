package idealworldtalks.chapter1

object MainNonTransparent {

  def main(args: Array[String]): Unit = {
    `I am not RT :(`(5)
  }

  def `I am not RT :(`(i: Any): Int = {
    try {
      val x = 42 + 5
      x + ((throw new Exception("Failed!")): Int)
    } catch {
      case _: Exception => 43
    }
  }
}
