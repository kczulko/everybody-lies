package idealworldtalks.chapter3

import idealworldtalks.chapter3.truecode.{FlatMap, Return, Suspend, TailRec}

object Main {

  def quicksort(l: List[Int])(implicit f: (Int, Int) => Boolean): TailRec[List[Int]] = {
    val stackDepth =
      Thread.currentThread().getStackTrace.count(_.getMethodName.matches("quicksort$"))
    assert(stackDepth == 1)
    l match {
      case h :: t =>
        val (left, right) = t.partition(f.curried(h))
        for {
          _ <- Suspend(() => ())
          sortedLeft <- quicksort(left)
          sortedRight <- quicksort(right)
        } yield sortedLeft ::: h :: sortedRight
      case _ => Return(List())
    }
  }

  def PrintLine(s: String) = Suspend(() => println(s))
  def ReadLine() = Suspend(() => scala.io.StdIn.readLine())

  def main(args: Array[String]): Unit = {
    TailRec run pureMain(args)
//    implicit val order: (Int, Int) => Boolean = _ < _
//    val `sorted ?` = quicksort(1 to 5000 toList)
//    println{
//      TailRec run `sorted ?`
//    }
  }

  private def pureMain(args: Array[String] = Array()): TailRec[Unit] = {
    for {
      _ <- PrintLine("gimme first number")
      first <- ReadLine()
      _ <- PrintLine("gimme second number")
      second <- ReadLine()
      _ <- PrintLine("first + second is:" + (first + second))
    } yield ()
  }
}
