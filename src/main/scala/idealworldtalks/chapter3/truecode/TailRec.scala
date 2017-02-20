package idealworldtalks.chapter3.truecode

import scala.annotation.tailrec

sealed trait TailRec[A] {
  def map[B](f: A => B): TailRec[B] = flatMap(f andThen (Return(_)))
  def flatMap[B](f: A => TailRec[B]): TailRec[B] = FlatMap(this, f)
}

case class Return[A](a: A) extends TailRec[A]
case class Suspend[A](f: () => A) extends TailRec[A]
case class FlatMap[A,B](sub: TailRec[A], f: A => TailRec[B]) extends TailRec[B]

object TailRec {
  @tailrec
  def run[A](tr: TailRec[A]): A = tr match {
    case Return(a) => a
    case Suspend(f) => f()
    case FlatMap(x,f) => x match {
      case Return(a) => run(f(a))
      case Suspend(r) => run(f(r()))
      case FlatMap(y, g) => run(y flatMap(a => g(a) flatMap f))
    }
  }
}